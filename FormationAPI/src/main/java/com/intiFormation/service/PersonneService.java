package com.intiFormation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.intiFormation.dao.IPersonneDao;
import com.intiFormation.dao.IUtilisateurDao;
import com.intiFormation.model.Personne;
import com.intiFormation.model.Utilisateur;

@Service
public class PersonneService implements IPersonneService{
	
	@Autowired
	private IPersonneDao pdao;
	
	@Autowired
	private IUtilisateurDao udao;
	
	@Autowired
    private JavaMailSender emailSender;
	
	@Override
	public void ajout(Personne p) {
		pdao.save(p);
	}
	
	@Override
	public void supprimer(int id) {
		pdao.deleteById(id);
	}
	
	@Override
	public void modifier(Personne p) {
		pdao.save(p);
	}
	
	@Override
	public List<Personne> affichertous() {
		List<Personne> personnes = pdao.findAll();
		List<Utilisateur> utilisateurs = udao.findAll();
		for (int i=0;i<personnes.size();i++) {
			for (int j=0;j<utilisateurs.size();j++) {
				if(personnes.get(i).getId() == utilisateurs.get(j).getId()) {
					personnes.remove(i);
				}
			}
			
		}
		return personnes;
	}
	
	@Override
	public Personne afficher(int id) {
		Personne personne = pdao.findById(id).get();
		return personne;
	}
	
	@Override
	public List<Personne> chercher(String nom) {
		List<Personne> personnes = pdao.findByNomStartingWith(nom);
		List<Utilisateur> utilisateurs = udao.findAll();
		for (int i=0;i<personnes.size();i++) {
			for (int j=0;j<utilisateurs.size();j++) {
				if(personnes.get(i).getId() == utilisateurs.get(j).getId()) {
					personnes.remove(i);
				}
			}
			
		}
		return personnes;
	}
	
	@Override
	public void contact(String email_emeteur, String email_recepteur, String titre, String sujet) {
		 SimpleMailMessage message = new SimpleMailMessage(); 
		 message.setFrom(email_emeteur);
		 message.setTo(email_recepteur); 
	     message.setSubject(titre); 
	     message.setText(sujet);
	     emailSender.send(message);
	}

}
