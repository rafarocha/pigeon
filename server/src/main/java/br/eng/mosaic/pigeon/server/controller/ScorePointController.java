package br.eng.mosaic.pigeon.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.eng.mosaic.pigeon.common.domain.User;
import br.eng.mosaic.pigeon.common.dto.UserInfo;
import br.eng.mosaic.pigeon.server.persistence.dao.DaoUser;

@Controller
public class ScorePointController extends AbstractController {
	
	private DaoUser daoUser = new DaoUser();

	public interface uri_score {
		String topfive = "/topfive.do";
	}

	/**
	 * @param session
	 * @param response
	 */
	@RequestMapping( uri_score.topfive )
	public void update( HttpSession session, HttpServletResponse response ) {
		
		/*
		  { 'topfive' : 
				{ '1' :
				 	'point' : 90,
				 	'photo' : url.nosso.server
				},
				{ '2' :
		  }
		 */		

		List<User> topfive = null;//getTopFive(session);
		if ( topfive == null )
			topfive = new ArrayList<User>();

		topfive = createTopfive();
	
		try {
				
			JSONArray array = new  JSONArray();
			
			for (int i = 0; i < topfive.size(); i++) {
				
				JSONObject object = new JSONObject();	
				
				object.put("score", topfive.get(i).getScore());
				object.put("url", "https://graph.facebook.com/"+ topfive.get(i).getFacebook_id() +"/picture");
				
				array.put(object);

			}
			
			JSONObject obj2 = new JSONObject();
			
			obj2.put("topfive", array);
			
			ack_ok(response, obj2.toString());
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ScorePointController x = new ScorePointController();
		x.update(null, null);
	}

	private List<User> createTopfive() {

		
		
		List<User> topFive = new ArrayList<User>();
		
		try {
			topFive = daoUser.getTopFive();
		} catch (Exception e) {

			e.printStackTrace();
			
		}
		
		/*
		List<UserInfo> topFive = new ArrayList<UserInfo>();

		UserInfo userA = new UserInfo("123", "Rafael Rocha", "http://a0.twimg.com/profile_images/702642138/foto-rafa-1_normal.png");
		userA.score = 123;
		topFive.add(userA);

		UserInfo userB = new UserInfo("456", "Lenin Abadie", "http://a2.twimg.com/profile_images/1229427670/euformal_normal.jpg");
		userB.score = 73;
		topFive.add(userB);

		UserInfo userC = new UserInfo("789", "Paulo Fernando", "http://a1.twimg.com/profile_images/1346270651/eu_normal.png");
		userC.score = 95;
		topFive.add(userC);

		UserInfo userD = new UserInfo("987", "Jamilson Batista", "http://a0.twimg.com/profile_images/1114139120/jamilson_normal.png");
		userD.score = 65;
		topFive.add(userD);

		UserInfo userE = new UserInfo("765", "Ivan Samuel", "http://a3.twimg.com/profile_images/1272843192/dany_euuuu_normal.jpg");
		userE.score = 71;
		topFive.add(userE);
		*/ 

		return topFive;
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