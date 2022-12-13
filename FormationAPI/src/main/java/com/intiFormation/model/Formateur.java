package com.intiFormation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("idFormateur")
public class Formateur extends Utilisateur {

	@JsonIgnore
	@OneToMany(mappedBy = "formateur")
	private List<Formation> formations = new ArrayList<>();

	
	public Formateur() {super();}
	
	
	public Formateur(int id, String prenom, String nom, int age, String email, String tel, Role role) {
		super(id, prenom, nom, age, email, tel, role);
		// TODO Auto-generated constructor stub
	}


	public Formateur(String prenom, String nom, int age, String email, String tel, Role role) {
		super(prenom, nom, age, email, tel, role);
		// TODO Auto-generated constructor stub
	}
	
	public Formateur(String prenom, String nom, int age, String email, String tel, Role role, List<Formation> formations) {
		super(prenom, nom, age, email, tel, role);
		this.formations = formations;
	}


	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}


	@Override
	public String toString() {
		return "Formateur [getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getRole()="
				+ getRole() + ", toString()=" + super.toString() + ", getId()=" + getId() + ", getPrenom()="
				+ getPrenom() + ", getNom()=" + getNom() + ", getAge()=" + getAge() + ", getEmail()=" + getEmail()
				+ ", getTel()=" + getTel() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
	
	
	
	
	
}
