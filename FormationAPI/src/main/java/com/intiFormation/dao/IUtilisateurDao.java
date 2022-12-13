package com.intiFormation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intiFormation.model.Utilisateur;

public interface IUtilisateurDao extends JpaRepository<Utilisateur, Integer> {

	public List<Utilisateur> findByRole_nom(String nom);
	public Utilisateur findByUsername(String username);
}
