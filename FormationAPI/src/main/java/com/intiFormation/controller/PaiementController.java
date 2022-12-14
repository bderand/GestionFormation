package com.intiFormation.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intiFormation.model.Formation;
import com.intiFormation.model.Paiement;
import com.intiFormation.model.Participant;
import com.intiFormation.service.IPaiementService;
import com.intiFormation.service.IPersonneService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class PaiementController {

	@Autowired
	IPaiementService paiementService;
	
	@Autowired
	IPersonneService pservice;

	@Autowired
	IFormationService formationService;
	@Autowired
	IParticipantService participantService;
	
	@GetMapping("/paiements")
	public List<Paiement> getPaiements_all() {
		return paiementService.getPaiements_all();
	}
	
	@GetMapping("/paiements/participant/{id}")
	public List<Paiement> getPaiements_idParticipant(@PathVariable("id") int id_participant){
		return paiementService.getPaiements_idParticipants(id_participant);
	}
	
	@GetMapping("/paiements/{id}")
	public Paiement getPaiement_id(@PathVariable("id") int id_paiement) {
		
		return paiementService.getPaiement_id(id_paiement);
	}

	@GetMapping("/paiements/formation")
	public float getPaiementReste(@RequestParam("id_participant") int id_participant, @RequestParam("id_formation") int id_formation) {
		float reste = paiementService.RestantPaiement(id_participant, id_formation);
		if(reste > 0)
		{
			Participant participant = participantService.getParticipant_id(id_participant);
			Formation formation = formationService.getFormation_id(id_formation);
			paiementService.addPaiement(new Paiement(reste, participant, formation));
		}
		
		return reste;
	}

	@PostMapping("/paiements")
	public Paiement addPaiement(@RequestBody Paiement paiement) {
		
		return paiementService.addPaiement(paiement);
	}
	
	@DeleteMapping("/paiements")
	public void suppPaiements(@RequestBody Paiement paiement) {
		
		paiementService.suppPaiement(paiement);
	}
	
	@DeleteMapping("/paiements/{id}")
	public void suppPaiements(@PathVariable("id") int id_paiement) {
		
		paiementService.suppPaiement(id_paiement);
	}

	
	@PostMapping("/paiements/contact/{id}")
	public void message(@PathVariable("id") int id_paiement) {
		Paiement paiement  = paiementService.getPaiement_id(id_paiement);
		String titre = "relancement du paiement pour la formation: " + paiement.getFormation().getNom();
		String message = "bonjour, \n nous vous permettons de vous recontacter afin de payer la formation : " + paiement.getFormation().getNom() + " \n si nous avons pas reçu le solde restant, nous serons en mesure de vous supprimer de la liste des participants de la formation. \n Cordialement \n l'équipe de la formation";
		pservice.contact("javajeeappli@gmail.com", paiement.getParticipant().getEmail(), titre, message);
	}
	
	
	
	public void cronJobSch() {
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	   Date now = new Date();
	   String strDate = sdf.format(now);
	   System.out.println("Java cron job expression:: " + strDate);
	}
}
