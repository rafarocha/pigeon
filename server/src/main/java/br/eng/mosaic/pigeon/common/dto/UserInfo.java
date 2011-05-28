package br.eng.mosaic.pigeon.common.dto;

public class UserInfo {
	
	public String id;
	public String name;
	public String token;
	
	public UserInfo(String id, String name, String token) {
		this.id = id;
		this.name = name;
		this.token = token;
	}
	
}