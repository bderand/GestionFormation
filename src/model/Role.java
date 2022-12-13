package com.intiFormation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String role;
	
	@OneToMany(mappedBy = "role")
	private List<Utilisateur> utilisateurs = new ArrayList<>();

	
	public Role() {}
	
	public Role(int id, String role, List<Utilisateur> utilisateurs) {
		this.id = id;
		this.role = role;
		this.utilisateurs = utilisateurs;
	}
	
	public Role(int id, String role) {
		this.id = id;
		this.role = role;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
	
}
