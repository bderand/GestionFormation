package com.intiFormation.config;


public class AuthentificationResponse {
	
	private final String jwt;

	public AuthentificationResponse() {
		
		jwt = null;
	}
	public String getJwt() {
		return jwt;
	}

	public AuthentificationResponse(String jwt) {
		this.jwt = jwt;
	}
	
	
	
	
	
	

}
