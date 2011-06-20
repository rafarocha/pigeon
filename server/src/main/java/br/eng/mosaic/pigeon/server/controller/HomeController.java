package br.eng.mosaic.pigeon.server.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends AbstractSocialController{
	
	@RequestMapping("{user_id}/welcome.do")
	public void welcome( @PathVariable String user_id, 
			HttpSession session, HttpServletResponse response ) {
 		
		ack_ok(response, user_id);
	}
	
}