package com.intiFormation.service;

import java.util.List;

import com.intiFormation.model.Personne;
import com.intiFormation.model.Utilisateur;

public interface IPersonneService {

	public void ajout(Personne p);
	public void supprimer(int id);
	public void modifier(Personne p);
	public List<Personne> affichertous();
	public Personne afficher(int id);
	public List<Personne> chercher(String nom) ;
	public void contact(String email_emeteur, String email_recepteur, String titre, String sujet);
	public void contact_participant(Utilisateur user);
	public void readPersonnesFromCSV(String fileName);
	public Personne createPersonne(String[] metadata);
	

}
