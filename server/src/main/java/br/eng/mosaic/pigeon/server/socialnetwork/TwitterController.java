package br.eng.mosaic.pigeon.server.socialnetwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TwitterController {
	
	@Autowired private TwitterClient twitterClient;
	
	@RequestMapping( "oauth/facebook/signIn.do" )
	public void signIn() {
	
		
		
	}
	
}