package com.intiFormation.service;

import java.util.List;

import com.intiFormation.model.RDV;

public interface IRDVService {

	public void ajouter(RDV r);
	public void modifier(RDV r);
	public void supprimer(int id);
	public RDV afficherparId(int id);
	public List<RDV> affichertous();
	public List<RDV> afficherparcommercial(int id);
	public List<RDV> afficherparRDVpersonne(int id);
}
