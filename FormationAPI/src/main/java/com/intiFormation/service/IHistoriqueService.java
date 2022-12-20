package com.intiFormation.service;

import java.util.List;

import com.intiFormation.model.Historique;

public interface IHistoriqueService {

	public void ajouter(Historique h);
	public void modifier(Historique h);
	public void supprimer(int id);
	public Historique afficherparId(int id);
	public List<Historique> affichertous();
	public List<Historique> affichertousparIdcommercial(int id);
	public List<Historique> afficherparPersonne(int id);
}
