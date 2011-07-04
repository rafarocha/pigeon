package br.eng.mosaic.pigeon.common.dto;

public class UserInfo {
	
	public String id;
	public String name;
	public String token;
	public String email;
	public int score;
	
	public UserInfo(String id, String name) {
		this.id = id;
		this.name = name;

	}
	
	public UserInfo(String id, String name, String token) {
		this.id = id;
		this.name = name;
		this.token = token;
	}
	
	public UserInfo(String id, String name, String token, String email) {
		this.id = id;
		this.name = name;
		this.token = token;
		this.email = email;
	}
	
}