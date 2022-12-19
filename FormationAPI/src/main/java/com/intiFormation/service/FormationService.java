package com.intiFormation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiFormation.dao.IFormationDao;
import com.intiFormation.model.Formation;


@Service
public class FormationService implements IFormationService{
	
	@Autowired
	private IFormationDao fdao; 
	
	@Override
	public void ajouter(Formation h) {
		fdao.save(h);
	}
	
	@Override
	public void modifier(Formation h) {
		fdao.save(h);
	}
	
	@Override
	public void supprimer(int id) {
		fdao.deleteById(id);
	}
	
	@Override
	public Formation afficherparId(int id) {
		Optional<Formation> op = fdao.findById(id);
		if(op.isPresent())return op.get();
		else return null;
	}
	
	@Override
	public List<Formation> affichertous(){
		List<Formation> liste = fdao.findAll();
		return liste;
	}

}
