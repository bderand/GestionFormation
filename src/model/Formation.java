package com.intiFormation.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Formation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private float prix;
	@Temporal(TemporalType.DATE)
	private Date debut;
	@Temporal(TemporalType.DATE)
	private Date fin;
	@ManyToOne
	@JoinColumn(name = "id_formateur")
	private Formateur formateur;
	
	@ManyToMany(mappedBy = "formations")
	private List<Participant> participants = new ArrayList<>();
	
	@OneToMany(mappedBy = "formation")
	private List<Paiement> paiements = new ArrayList<>();
	
	public Formation() {}

	public Formation(int id, String nom, float prix, Date debut, Date fin, Formateur formateur) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.debut = debut;
		this.fin = fin;
		this.formateur = formateur;
	}
	
	public Formation(String nom, float prix, Date debut, Date fin, Formateur formateur) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.debut = debut;
		this.fin = fin;
		this.formateur = formateur;
	}
	
	public Formation(int id, String nom, float prix, Date debut, Date fin) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.debut = debut;
		this.fin = fin;

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

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}
	

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public List<Paiement> getPaiements() {
		return paiements;
	}

	public void setPaiements(List<Paiement> paiements) {
		this.paiements = paiements;
	}

	@Override
	public String toString() {
		return "Formation [id=" + id + ", nom=" + nom + ", prix=" + prix + ", debut=" + debut + ", fin=" + fin
				+ ", formateur=" + formateur + "]";
	}
	
	
	
	
}
