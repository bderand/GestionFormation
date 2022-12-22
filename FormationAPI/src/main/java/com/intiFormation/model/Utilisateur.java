package com.intiFormation.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Utilisateur extends Personne {

	private String username;
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "idRole")
	private Role role;

	
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utilisateur(int id, String prenom, String nom, int age, String email, String tel, Role role) {
		super(id, prenom, nom, age, email, tel);
		this.role = role;
	}

	public Utilisateur(String prenom, String nom, int age, String email, String tel, Role role) {
		super(prenom, nom, age, email, tel);
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Utilisateur [username=" + username + ", password=" + password + ", getId()=" + getId()
				+ ", getPrenom()=" + getPrenom() + ", getNom()=" + getNom() + ", getAge()=" + getAge() + ", getEmail()="
				+ getEmail() + ", getTel()=" + getTel() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
	
}
