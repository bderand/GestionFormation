package com.intiFormation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String prenom;
	private String nom;
	private int age;
	private String email;
	private String tel;
	
	public Personne(){}
	
	public Personne(int id, String prenom, String nom, int age, String email, String tel) {
		
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.age = age;
		this.email = email;
		this.tel = tel;
	}
	
	public Personne(String prenom, String nom, int age, String email, String tel) {
		
		this.prenom = prenom;
		this.nom = nom;
		this.age = age;
		this.email = email;
		this.tel = tel;
	}
	
	
	public int getId(){
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}


	@Override
	public String toString() {
		return "Personne [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", age=" + age + ", email=" + email
				+ ", tel=" + tel + "]";
	}
	
	
	
}
