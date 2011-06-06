package br.eng.mosaic.pigeon.common.domain;

import java.io.Serializable;

public class SocialNetwork implements Serializable{
	
	private int id;
	private String name;

	public SocialNetwork(){
		this.id = 0;
		this.name = "";
	}
	
	public SocialNetwork(int id, String name){
		this.id = id;
		this.name = name;
		User u = new User();
	}
	
	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		try {
			SocialNetwork sn = (SocialNetwork)obj;
			if((this.id == sn.id) && (this.name == sn.name))
				return true;
			return false;	
		} catch (Exception e) {
			return false;
		}
		
	}
}