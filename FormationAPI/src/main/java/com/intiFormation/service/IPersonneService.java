package com.intiFormation.service;

import java.util.List;

import com.intiFormation.model.Personne;

public interface IPersonneService {

	public void ajout(Personne p);
	public void supprimer(int id);
	public void modifier(Personne p);
	public List<Personne> affichertous();
	public Personne afficher(int id);
	public List<Personne> chercher(String nom) ;
	public void contact(String email_emeteur, String email_recepteur, String titre, String sujet);
}
