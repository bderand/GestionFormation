package com.intiFormation.service;

import java.util.List;

import com.intiFormation.model.Formateur;

public interface IFormateurService {

	void ajouter(Formateur h);

	void modifier(Formateur h);

	void supprimer(int id);

	Formateur afficherparId(int id);

	List<Formateur> affichertous();

}