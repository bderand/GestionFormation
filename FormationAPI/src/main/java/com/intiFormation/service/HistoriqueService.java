package com.intiFormation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiFormation.dao.IHistoriqueDao;
import com.intiFormation.model.Historique;


@Service
public class HistoriqueService implements IHistoriqueService{
	
	@Autowired
	private IHistoriqueDao hdao; 
	
	@Override
	public void ajouter(Historique h) {
		hdao.save(h);
	}
	
	@Override
	public void modifier(Historique h) {
		hdao.save(h);
	}
	
	@Override
	public void supprimer(int id) {
		hdao.deleteById(id);
	}
	
	@Override
	public Historique afficherparId(int id) {
		Historique historique = hdao.findById(id).get();
		return historique;
	}
	
	@Override
	public List<Historique> affichertous(){
		List<Historique> liste = hdao.findAll();
		return liste;
	}
	
	@Override
	public List<Historique> affichertousparIdcommercial(int id){
		List<Historique> liste = hdao.findByCommercial_id(id);
		return liste;
	}
	
	@Override
	public List<Historique> afficherparPersonne(int id){
		List<Historique> liste = hdao.findByPersonne_id(id);
		return liste;
	}
	

}
