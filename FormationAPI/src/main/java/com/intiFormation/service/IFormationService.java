package com.intiFormation.service;

import java.util.List;

import com.intiFormation.model.Formation;


public interface IFormationService{

	public void ajouter(Formation h) ;
	public void modifier(Formation h) ;
	public void supprimer(int id) ;
	public Formation afficherparId(int id) ;
	public List<Formation> affichertous();
}
