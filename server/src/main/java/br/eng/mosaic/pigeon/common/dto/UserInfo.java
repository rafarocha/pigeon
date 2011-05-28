package br.eng.mosaic.pigeon.common.dto;

public class UserInfo {
	
	public String id;
	public String name;
	public String token;
	
	public UserInfo(String name, String id, String token) {
		this.id = id;
		this.name = name;
		this.token = token;
	}
	
}