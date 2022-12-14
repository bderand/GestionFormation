package com.intiFormation.service;



import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
		
		return pdao.findAll();
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
	
	@Override
	public void contact_participant(Utilisateur user) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("javajeeappli2@gmail.com");
		message.setTo(user.getEmail());
		message.setSubject("Identifiants de connection : Formation");
		String txt = "Bonjour " + user.getNom() + " " + user.getPrenom()
					+ "\nVos identifiants pour acc??der ??  votre compte sont: \n " + "Nom d'utilisateur: " + user.getUsername() + "\n Mot de passe: " + user.getPassword()
						+ "\n";
		message.setText(txt);
		emailSender.send(message);
	}
	
	@Override
	public void readPersonnesFromCSV(String fileName) {
	   
			Path pathToFile = Paths.get(fileName);

	        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
	            String line = br.readLine();
	            while (line != null) {
	                String[] attributes = line.split(",");
	                Personne p = createPersonne(attributes);
	                pdao.save(p);
	                line = br.readLine();
	            }
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	        }
	    }
	
	@Override
	public Personne createPersonne(String[] metadata) {
	    	//int id = Integer.parseInt(metadata[0]);
	    	String prenom = metadata[0];
	    	String nom = metadata[1];
	        int age = Integer.parseInt(metadata[2]);
	        String email = metadata[3];
	        String tel = metadata[4];
	        //return new Personne(id, prenom, nom, age, email, tel);
	        return new Personne(prenom, nom, age, email, tel);
	  }

}
