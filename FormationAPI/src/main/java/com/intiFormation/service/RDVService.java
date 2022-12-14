package com.intiFormation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.intiFormation.dao.IRDVDao;
import com.intiFormation.model.Personne;
import com.intiFormation.model.RDV;



@Service
public class RDVService implements IRDVService{

	@Autowired
	private IRDVDao rdao;
	
	@Autowired
    private JavaMailSender emailSender;
	
	@Override
	public void ajouter(RDV r) {
		rdao.save(r);
	}
	
	@Override
	public void modifier(RDV r) {
		rdao.save(r);
	}
	
	@Override
	public void supprimer(int id) {
		rdao.deleteById(id);
	}
	
	@Override
	public RDV afficherparId(int id) {
		RDV rdv = rdao.findById(id).get();
		return rdv;
	}
	
	@Override
	public List<RDV> affichertous(){
		List<RDV> liste = rdao.findAll();
		return liste;
	}
	

	public void contact(String email_emeteur, String email_recepteur, String titre, String sujet) {
		 SimpleMailMessage message = new SimpleMailMessage(); 
		 message.setFrom(email_emeteur);
		 message.setTo(email_recepteur); 
	     message.setSubject(titre); 
	     message.setText(sujet);
	     emailSender.send(message);
	}
	

	
}
