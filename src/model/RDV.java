package com.intiFormation.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RDV {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "idCommercial")
	private Commercial commercial;
	@ManyToOne
	@JoinColumn(name = "idPersonne")
	private Personne personne;
	private Date rdv;
	
	public RDV() {
		// TODO Auto-generated constructor stub
	}

	public RDV(int id, Commercial commercial, Personne personne, Date rdv) {
		super();
		this.id = id;
		this.commercial = commercial;
		this.personne = personne;
		this.rdv = rdv;
	}
	
	public RDV(Commercial commercial, Personne personne, Date rdv) {
		super();
		this.commercial = commercial;
		this.personne = personne;
		this.rdv = rdv;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Commercial getCommercial() {
		return commercial;
	}

	public void setCommercial(Commercial commercial) {
		this.commercial = commercial;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Date getRdv() {
		return rdv;
	}

	public void setRdv(Date rdv) {
		this.rdv = rdv;
	}

	@Override
	public String toString() {
		return "RDV [id=" + id + ", commercial=" + commercial + ", personne=" + personne + ", rdv=" + rdv + "]";
	}
	
	
	

	
	
	
	
}
