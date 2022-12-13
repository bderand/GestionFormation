package com.intiFormation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Paiement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private float reste;
	@ManyToOne
	@JoinColumn(name = "idParticipant")
	private Participant participant;
	@ManyToOne
	@JoinColumn(name = "idFormation")
	private Formation formation;
	
	
	public Paiement() {}
	
	public Paiement(int id, float reste, Participant participant, Formation formation) {
		super();
		this.id = id;
		this.reste = reste;
		this.participant = participant;
		this.formation = formation;
	}
	
	public Paiement(float reste, Participant participant, Formation formation) {
		super();
		this.reste = reste;
		this.participant = participant;
		this.formation = formation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getReste() {
		return reste;
	}

	public void setPrix(float reste) {
		this.reste = reste;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	@Override
	public String toString() {
		return "Paiement [id=" + id + ", reste=" + reste + ", participant=" + participant + ", formation=" + formation
				+ "]";
	}
	
	
	
	
	
	
}
