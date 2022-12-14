package com.intiFormation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intiFormation.model.Paiement;
import com.intiFormation.model.Participant;
import com.intiFormation.service.IPaiementService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class PaiementController {

	@Autowired
	IPaiementService paiementService;
	/*
	@Autowired
	IFormationService formationService;
	@Autowired
	IParticipantService participantService;
	*/
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
	/*
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
	*/
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
}
