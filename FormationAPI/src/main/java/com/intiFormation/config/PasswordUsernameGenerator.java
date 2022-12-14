package com.intiFormation.config;


import java.util.UUID;


import net.bytebuddy.utility.RandomString;

public class PasswordUsernameGenerator {

	
	private String password;
	private String username;
	
	{
		generatesPassword();
	}
	
	public PasswordUsernameGenerator(String nom, String prenom){
		
		this.generatesUsername(nom, prenom);
	}
	
	
	private void generatesPassword() {
		
		this.password = RandomString.make(10);
		
	}

	private void generatesUsername(String nom, String prenom) {
		String uuid = UUID.randomUUID().toString().substring(0, 4);
		String username = String.valueOf(prenom.charAt(0)) + nom;
		this.username = username.toLowerCase();
		this.username += uuid;
	}

	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	
}
