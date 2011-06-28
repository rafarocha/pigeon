package br.eng.mosaic.pigeon.server.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.eng.mosaic.pigeon.common.dto.UserInfo;
import br.eng.mosaic.pigeon.server.exception.ServerCrashException;
import br.eng.mosaic.pigeon.server.helper.MimeType;
import br.eng.mosaic.pigeon.server.service.UserService;
import br.eng.mosaic.pigeon.server.socialnetwork.FacebookClient;

@Controller
public class FacebookController extends AbstractController {
	
	protected interface uri_fb {
		String redir = "redirect:";
		String sign_in = "oauth/facebook/signIn.do";
		String sign_callback = "oauth/facebook/callback.do";
		String photo = "{user_id}/oauth/facebook/photo.do";
		String publish = "{user_id}/oauth/facebook/publish.do";
	}

	@Autowired private FacebookClient facebookClient;
	@Autowired private UserService userService;

	@RequestMapping( uri_fb.sign_in )
	public String sign_in(HttpSession session) throws MalformedURLException {
		return uri_fb.redir + facebookClient.getUrlCodeKnowUser(uri_fb.sign_callback);
	}
	
	@RequestMapping( uri_fb.sign_callback )
	public String callback( @RequestParam(value = "code") String hash,
			HttpSession session, HttpServletResponse response ) {

		if ( hash == null || hash.isEmpty() )
			ack_error(response, "erro ao autenticar com server facebook");
		
		UserInfo user = facebookClient.getUser(uri_fb.sign_callback, hash);
		userService.connect(user); 
		session.setAttribute(user.id, user);
		
		System.out.println( "session.id:" + session.getAttribute(user.id) );
		System.out.println( "session.id:" + user.id );
		
		return "redirect:/" + user.id + "/welcome.do";
	}

	@RequestMapping( uri_fb.photo )
	public void photo( @PathVariable String user_id, HttpSession session, HttpServletResponse response) 
			throws ClientProtocolException, URISyntaxException, IOException {

		UserInfo user = getUser(session, user_id);
		byte[] photo = facebookClient.getPicture( user.token );
		download(response, MimeType.image_png, photo );
	}
	
	@RequestMapping( uri_fb.publish )
	public void publish( @PathVariable String user_id, HttpSession session, HttpServletResponse response,
			@RequestParam(value = "score") String score, @RequestParam(value = "message") String message ) 
				throws IOException, URISyntaxException, ServerCrashException {
		
		// refatorar regras constarem no service
		UserInfo user = getUser(session, user_id);
		System.out.println( "session.test1:" + session.getAttribute(user_id) );
		System.out.println( "session.test2:" + user );
		System.out.println( "session.test3:" + session.getId() );
		
//		userService.update(user);
		String doc_id = "";//facebookClient.publish(user, message);
		ack_ok(response, doc_id);
	}
	
	public void setFacebookClient(FacebookClient facebookClient) {
		this.facebookClient = facebookClient;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}