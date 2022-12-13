package com.intiFormation.service;

import java.util.List;

import com.intiFormation.model.Utilisateur;

public interface IUtilisateurService {

	public Utilisateur getUtilisateur_id(int id);
	public Utilisateur getUtilisateur_username(String username);
	public List<Utilisateur> getUtilisateurByRole(String role);
	public List<Utilisateur> getUtilisateurs_all();
	public Utilisateur addUtilisateur(Utilisateur u);
	public void suppUtilisateur(int id);
	public void suppUtilisateur(Utilisateur u);
}
