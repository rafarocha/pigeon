package br.eng.mosaic.pigeon.common.domain;

import java.io.Serializable;

public class Credential implements Serializable{

	private int id;
	private String socialId;
	private SocialNetwork socialNetwork;
	private Boolean allowPublishing;

	
	public Credential(){
		this.id = 0;
		this.socialId = "";
		this.socialNetwork = new SocialNetwork();
		this.allowPublishing = true;
	}

	public Credential(int id, String socialId, SocialNetwork socialNetwork, Boolean allowPublishing){
		this.id = id;
		this.socialId = socialId;
		this.socialNetwork = socialNetwork;
		this.allowPublishing = allowPublishing;
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
	public String getUserId() {
		return socialId;
	}
	
	/**
	 * 
	 * @return Set the unique user identifier used by social network. 
	 */
	public void setUserId(String userId) {
		this.socialId = userId;
	}
	
	public SocialNetwork getSocialNetwork() {
		return socialNetwork;
	}
	
	public void setSocialNetwork(SocialNetwork socialNetwork) {
		this.socialNetwork = socialNetwork;
	}
	
	public Boolean getAllowPublishing() {
		return allowPublishing;
	}
	
	public void setAllowPublishing(Boolean allowPublishing) {
		this.allowPublishing = allowPublishing;
	}

}
