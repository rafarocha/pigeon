package br.eng.mosaic.pigeon.common.domain;

import java.io.Serializable;

public class Credential implements Serializable{

	private int id;
	private String socialId;
	private User user;
	private SocialNetwork socialNetwork;
	private Boolean allowPublish;

	
	public Credential(){
		this.id = 0;
		this.socialId = "";
		this.user = new User();
		this.socialNetwork = new SocialNetwork();
		this.allowPublish = true;
	}

	public Credential(int id, String socialId, User user, SocialNetwork socialNetwork, Boolean allowPublish){
		this.id = id;
		this.socialId = socialId;
		this.user = user;
		this.socialNetwork = socialNetwork;
		this.allowPublish = allowPublish;
	}
	
	/**
	 * 
	 * @return Returns the parameter identifier.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id Set's the parameter identifier.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return Returns the unique user identifier used by social network. 
	 */
	public String getSocialId() {
		return socialId;
	}
	
	/**
	 * 
	 * @return Set the unique user identifier used by social network. 
	 */
	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public SocialNetwork getSocialNetwork() {
		return socialNetwork;
	}
	
	public void setSocialNetwork(SocialNetwork socialNetwork) {
		this.socialNetwork = socialNetwork;
	}
	
	public Boolean getAllowPublish() {
		return allowPublish;
	}
	
	public void setAllowPublish(Boolean allowPublish) {
		this.allowPublish = allowPublish;
	}

}
