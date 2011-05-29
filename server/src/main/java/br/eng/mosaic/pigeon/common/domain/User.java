package br.eng.mosaic.pigeon.common.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class User implements Serializable {
	
	private String name;
	private String email;
	private List<Credential> credentials;
	private double score;

	public User(){
		this.name = "";
		this.email = "";
		this.credentials = new LinkedList<Credential>();
		this.score = 0;
	}
	
	public User(String name, String email, List<Credential> credentials, double score){
		this.name = name;
		this.email = email;
		this.credentials = credentials;
		this.score = score;
	}
	
	/**
	 * 
	 * @return Returns the User's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name User's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return Returns the User's email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email User's email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return Returns the set of social networks of user.
	 */
	public List<Credential> getSocialNetworks() {
		return credentials;
	}

	/**
	 * 
	 * @param socialNetworks List of social networks associated to user.
	 */
	public void setSocialNetworks(List<Credential> socialNetworks) {
		this.credentials = socialNetworks;
	}

	/**
	 * 
	 * @return Returns the User's game score.
	 */
	public double getScore() {
		return score;
	}

	/**
	 * Sum the points gained to user game score.
	 * @param points Points gained in last play game.
	 */
	public void setScore(double points) {
		this.score = points;
	}


	@Override
	public boolean equals(Object obj) {
		try {
			User user = (User)obj;
			if( ( this.name.equals(user.name) ) && ( this.email.equals(user.email) ) )
				return true;
			return false;	
		} catch (Exception e) {
			return false;
		}
		
	}
}