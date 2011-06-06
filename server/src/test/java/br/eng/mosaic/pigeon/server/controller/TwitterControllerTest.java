package br.eng.mosaic.pigeon.server.controller;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.web.servlet.HandlerAdapter;

import br.eng.mosaic.pigeon.server.integration.PigeonConfiguration;
import br.eng.mosaic.pigeon.server.socialnetwork.FacebookConfiguration;
import br.eng.mosaic.pigeon.server.socialnetwork.TwitterController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:br/eng/mosaic/pigeon/cfg/spring/spring-test-beans.xml" })
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
public class TwitterControllerTest extends TestCase {
	
	@Autowired private TwitterController twitterController;
	
	@Autowired private ApplicationContext applicationContext;
	@Autowired private PigeonConfiguration pigeonConfig;
	@Autowired private FacebookConfiguration fbConfig;

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private HandlerAdapter handlerAdapter;

	private static String bar = "/";
	
	@Before public void setUp() {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		handlerAdapter = applicationContext.getBean(HandlerAdapter.class);
		twitterController = new TwitterController();
	}
	
	@Test public void testSignInSuccessfully() throws Exception {
		
	}

}