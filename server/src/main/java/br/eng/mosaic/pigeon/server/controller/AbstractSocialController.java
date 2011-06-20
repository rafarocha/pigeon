package br.eng.mosaic.pigeon.server.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;

import br.eng.mosaic.pigeon.common.dto.UserInfo;
import br.eng.mosaic.pigeon.server.helper.IOFetchContent;
import br.eng.mosaic.pigeon.server.helper.MimeType;

public abstract class AbstractSocialController {
	
	private static final Logger logger = 
		Logger.getLogger(IOFetchContent.class.getName());
	
	private static String defaultContentType = MimeType.text_html.type;
	
	protected void download(HttpServletResponse response, MimeType mime, byte[] bytes) throws IOException {
		response.setContentType( mime.type );
		OutputStream out = response.getOutputStream();
		out.write( bytes );
		out.flush();
	}
	
	protected UserInfo getUser(HttpSession session, String user_id) {
		return (UserInfo) session.getAttribute( user_id );
	}
	
	private void ack(HttpServletResponse response, String signal, String ack) {
		try {
			response.setContentType( defaultContentType );
			response.getWriter().write( getAckJSON(signal, ack) );
			response.getWriter().flush();	
		} catch (Exception e) {
			logger.log(Level.SEVERE, "server.error.ack.message > " + e.getMessage());
		}
	}
	
	protected void ack_error(HttpServletResponse response, String ack) {
		ack(response, "fail", ack);
	}

	protected void ack_ok(HttpServletResponse response, String ack) {
		ack(response, "sucess", ack);
	}
	
	private String getAckJSON(String signal, String message) {
		return "{ '" + signal + "' : '" + message + "' }";
	}
	
	@ExceptionHandler(RuntimeException.class) public void handleException(
			RuntimeException e, HttpServletResponse response) throws IOException {
		String message = getAckJSON("fail", e.getMessage());
		response.getWriter().print( message ); 
		response.getWriter().flush();
	}
	
}