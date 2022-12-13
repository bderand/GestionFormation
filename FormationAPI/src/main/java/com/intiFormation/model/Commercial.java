package com.intiFormation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("idCommercial")
public class Commercial extends Utilisateur {

	@JsonIgnore
	@OneToMany(mappedBy = "commercial")
	private List<RDV> rdv = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "commercial")
	private List<Historique> historiques = new ArrayList<>();
	
	public Commercial() {super();}

	public Commercial(int id, String prenom, String nom, int age, String email, String tel, Role role) {
		super(id, prenom, nom, age, email, tel, role);
		
	}

	public Commercial(String prenom, String nom, int age, String email, String tel, Role role) {
		super(prenom, nom, age, email, tel, role);
		
	}

	public List<RDV> getRdv() {
		return rdv;
	}

	public void setRdv(List<RDV> rdv) {
		this.rdv = rdv;
	}

	public List<Historique> getHistoriques() {
		return historiques;
	}

	public void setHistoriques(List<Historique> historiques) {
		this.historiques = historiques;
	}
	
	
	
	
}
