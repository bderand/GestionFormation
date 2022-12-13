package com.intiFormation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiFormation.dao.IUtilisateurDao;
import com.intiFormation.model.Utilisateur;

@Service
public class UtilisateurService implements IUtilisateurService {

	@Autowired
	IUtilisateurDao utilisateurDao;
	
	@Override
	public List<Utilisateur> getUtilisateurs_all() {
		return utilisateurDao.findAll();
	}

	@Override
	public Utilisateur getUtilisateur_id(int id) {
		Optional<Utilisateur> op = utilisateurDao.findById(id);
		if(op.isPresent())return op.get();
		else return null;
	}

	@Override
	public List<Utilisateur> getUtilisateurByRole(String role) {
		// TODO Auto-generated method stub
		return utilisateurDao.findByRole_nom(role);
	}

	@Override
	public Utilisateur addUtilisateur(Utilisateur u) {
		// TODO Auto-generated method stub
		return utilisateurDao.save(u);
	}

	@Override
	public void suppUtilisateur(int id) {
		// TODO Auto-generated method stub
		utilisateurDao.deleteById(id);
	}

	@Override
	public void suppUtilisateur(Utilisateur u) {
		// TODO Auto-generated method stub
		utilisateurDao.delete(u);
	}

	@Override
	public Utilisateur getUtilisateur_username(String username) {
		// TODO Auto-generated method stub
		return utilisateurDao.findByUsername(username);
	}
	
	
}
