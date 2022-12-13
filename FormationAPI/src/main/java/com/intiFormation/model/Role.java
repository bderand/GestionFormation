package com.intiFormation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private List<Utilisateur> utilisateurs = new ArrayList<>();

	
	public Role() {}
	
	public Role(int id, String nom, List<Utilisateur> utilisateurs) {
		this.id = id;
		this.nom = nom;
		this.utilisateurs = utilisateurs;
	}
	
	public Role(int id, String role) {
		this.id = id;
		this.nom = role;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
	
}
