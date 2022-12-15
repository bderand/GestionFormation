package com.intiFormation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiFormation.dao.IFormateurDao;
import com.intiFormation.model.Formateur;


@Service
public class FormateurService implements IFormateurService{
	
	@Autowired
	private IFormateurDao fdao; 
	
	@Override
	public void ajouter(Formateur h) {
		fdao.save(h);
	}
	
	@Override
	public void modifier(Formateur h) {
		fdao.save(h);
	}
	
	@Override
	public void supprimer(int id) {
		fdao.deleteById(id);
	}
	
	@Override
	public Formateur afficherparId(int id) {
		Formateur Formateur = fdao.findById(id).get();
		return Formateur;
	}
	
	@Override
	public List<Formateur> affichertous(){
		List<Formateur> liste = fdao.findAll();
		return liste;
	}

}
