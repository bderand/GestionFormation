package com.intiFormation.service;

import java.util.List;
import java.util.Optional;

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
		Optional<Formateur> op = fdao.findById(id);
		if(op.isPresent())return op.get();
		else return null;
	}
	
	@Override
	public List<Formateur> affichertous(){
		List<Formateur> liste = fdao.findAll();
		return liste;
	}

}
