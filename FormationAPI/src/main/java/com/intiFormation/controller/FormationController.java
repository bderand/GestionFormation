package com.intiFormation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intiFormation.model.Formateur;
import com.intiFormation.model.Formation;
import com.intiFormation.model.Paiement;
import com.intiFormation.model.Participant;
import com.intiFormation.service.IFormateurService;
import com.intiFormation.service.IFormationService;
import com.intiFormation.service.IPaiementService;
import com.intiFormation.service.IParticipantService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class FormationController {
	
	@Autowired
	private IFormationService fservice;
	
	@Autowired
	private IFormateurService formateurService;
	
	@Autowired
	private IParticipantService participantService;
	
	@Autowired
	private IPaiementService paiementService;
	
	@PostMapping("/Formations")
	public void post(@RequestBody Formation f) {
		fservice.ajouter(f);
	}
	
	@PostMapping("/Formations/formateur")
	public Formation setFormations_formateur(@RequestParam("id_formation") int id_formation, @RequestParam("id_formateur") int id_formateur) {
		
		Formation formation = fservice.afficherparId(id_formation);
		Formateur formateur = formateurService.afficherparId(id_formateur);
		
		formation.setFormateur(formateur);
		fservice.ajouter(formation);
		
		return formation;
		
	}
	
	@DeleteMapping("/Formations/{id}")
	public void delete(@PathVariable("id") int id) {
		
		Formation formation = fservice.afficherparId(id);
		
		List<Participant> participants = formation.getParticipants();
		for(Participant p:participants) {
			List<Formation> formations = p.getFormations();
			formations.removeIf(f -> f.getId() == id);
			p.setFormations(formations);
			
			participantService.addParticipant(p);
			
		}
		
		List<Paiement> paiements = formation.getPaiements();
		for(Paiement p:paiements) {
			
			paiementService.suppPaiement(p.getId());
			
		}
		
		fservice.supprimer(id);
	}
	
	@PutMapping("/Formations")
	public void put(@RequestBody Formation f) {
		fservice.modifier(f);
	}
	
	@GetMapping("/Formations")
	public List<Formation> getAll(){
		List<Formation> Formations = fservice.affichertous();
		return Formations;
	}
	
	@GetMapping("/Formations/{id}/participants")
	public List<Participant> getFormation_participant(@PathVariable("id") int id){
	
		return fservice.afficherparId(id).getParticipants();
		
	}
	
	@GetMapping("/Formations/{id}")
	public Formation get(@PathVariable("id") int id) {
		Formation Formation = fservice.afficherparId(id);
		return Formation;
	}
}
