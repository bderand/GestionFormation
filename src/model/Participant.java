package com.intiFormation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
@DiscriminatorValue("idParticipant")
	public class Participant extends Utilisateur {

	@ManyToMany
	@JoinTable( name = "participants_formations",
    joinColumns = @JoinColumn( name = "idParticipant" ),
    inverseJoinColumns = @JoinColumn( name = "idFormation" ) )
	private List<Formation> formations = new ArrayList<>();

	@OneToMany(mappedBy = "participant")
	private List<Paiement> paiements = new ArrayList<>();
	
	public Participant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Participant(int id, String prenom, String nom, int age, String email, String tel, Role role) {
		super(id, prenom, nom, age, email, tel, role);
		// TODO Auto-generated constructor stub
	}

	public Participant(String prenom, String nom, int age, String email, String tel, Role role, List<Formation> formations) {
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
		return "Participant [getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getRole()="
				+ getRole() + ", toString()=" + super.toString() + ", getId()=" + getId() + ", getPrenom()="
				+ getPrenom() + ", getNom()=" + getNom() + ", getAge()=" + getAge() + ", getEmail()=" + getEmail()
				+ ", getTel()=" + getTel() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
	
	
}
