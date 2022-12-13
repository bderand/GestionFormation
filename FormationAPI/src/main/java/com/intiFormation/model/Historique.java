package com.intiFormation.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Historique {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date appel;
	private String commentaire;
	@ManyToOne
	@JoinColumn(name="idPersonne")
	private Personne personne;
	@ManyToOne
	@JoinColumn(name = "idCommercial")
	private Commercial commercial;
	
	public Historique() {}
	
	public Historique(int id, Date appel, String commentaire, Personne personne, Commercial commercial) {
		super();
		this.id = id;
		this.appel = appel;
		this.commentaire = commentaire;
		this.personne = personne;
		this.commercial = commercial;
	}
	
	public Historique(Date appel, String commentaire, Personne personne, Commercial commercial) {
		super();
		this.appel = appel;
		this.commentaire = commentaire;
		this.personne = personne;
		this.commercial = commercial;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAppel() {
		return appel;
	}

	public void setAppel(Date appel) {
		this.appel = appel;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Commercial getCommercial() {
		return commercial;
	}

	public void setCommercial(Commercial commercial) {
		this.commercial = commercial;
	}

	@Override
	public String toString() {
		return "Historique [id=" + id + ", appel=" + appel + ", commentaire=" + commentaire + ", personne=" + personne
				+ ", commercial=" + commercial + "]";
	}
	
	
	
	
}
