package br.eng.mosaic.pigeon.server.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.eng.mosaic.pigeon.common.dto.UserInfo;

@Controller
public class ScorePointController extends AbstractController {
	
	public interface uri_score {
		String topfive = "/topfive";
	}
	
	/**
	 * @param session
	 * @param response
	 */
	@RequestMapping( uri_score.topfive )
	public void update( HttpSession session, HttpServletResponse response ) {
		
		Topfive topfive = null;//getTopFive(session);
		if ( topfive == null )
			topfive = new Topfive();
		
		topfive = createTopfive();
		
		/*
		  { 'topfive' : 
				{ '1' :
				 	'point' : 90,
				 	'photo' : url.nosso.server
				},
				{ '2' :
		  }
		*/
	
		int i = 1;
		StringBuilder builder = new StringBuilder();
		for (Entry<Integer, UserInfo> set : topfive.players.entrySet()) {
			String content = getJSONScorePoint( set.getKey(), set.getValue() );
			content += ( i < 5 ) ? ", " : "";
			builder.append( content );
			++i;
		}
		
		String json = "{ 'friends' : " + builder.toString() + " }";
		System.out.println( json );
		ack_ok(response, json);
	}
	
	public static void main(String[] args) {
		ScorePointController x = new ScorePointController();
		x.update(null, null);
	}
	
	private Topfive createTopfive() {
		Topfive topfive = new Topfive();
		
		UserInfo userA = new UserInfo("123", "Rafael Rocha", "http://a0.twimg.com/profile_images/702642138/foto-rafa-1_normal.png");
		userA.score = 123;
		topfive.players.put(1, userA);

		UserInfo userB = new UserInfo("456", "Lenin Abadié", "http://a2.twimg.com/profile_images/1229427670/euformal_normal.jpg");
		userB.score = 73;
		topfive.players.put(2, userB);
		
		UserInfo userC = new UserInfo("789", "Paulo Fernando", "http://a1.twimg.com/profile_images/1346270651/eu_normal.png");
		userC.score = 95;
		topfive.players.put(3, userC);
		
		UserInfo userD = new UserInfo("987", "Jamilson Batista", "http://a0.twimg.com/profile_images/1114139120/jamilson_normal.png");
		userD.score = 65;
		topfive.players.put(4, userD);
		
		UserInfo userE = new UserInfo("765", "Ivan Samuel", "http://a3.twimg.com/profile_images/1272843192/dany_euuuu_normal.jpg");
		userE.score = 71;
		topfive.players.put(5, userE);
		
		return topfive;
	}

	private String getJSONScorePoint(int pos, UserInfo user) {
		String json = "{ 'point' : '" + user.score + "', "
				+ "'photo' : '" + user.token + "' " // por enquanto!
//				+ " 'photo' : '" + "http://10.0.0.3:8080/" 
//						+ user.id + "/oauth/facebook/photo.do" + "' "
				
				+ " } ";
		return json;
	}
	
	private Topfive getTopFive(HttpSession session) {
		return (Topfive) session.getAttribute("topfive");
	}
	
	
	
	class Topfive {
				
		public Map<Integer, UserInfo> players;
		
		public Topfive() {
			this.players = new HashMap<Integer, UserInfo>();
		}
		
	}
	
}