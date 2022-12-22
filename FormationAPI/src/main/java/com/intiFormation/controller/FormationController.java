package com.intiFormation.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	public Formation setFormations_formateur(@RequestParam("formation") String f, @RequestParam("id_formateur") int id_formateur) {
		
		ObjectMapper obj_mapper = new ObjectMapper();
		Formation formation_new = new Formation();
		try {
		Formation formation = obj_mapper.readValue(f, Formation.class);
		
		if(formation.getId() != 0)formation_new = fservice.afficherparId(formation.getId());
		
		Formateur formateur = formateurService.afficherparId(id_formateur);
		
		formation_new.setDebut(formation.getDebut());
		formation_new.setFin(formation.getFin());
		formation_new.setNom(formation.getNom());
		formation_new.setPrix(formation.getPrix());
		formation_new.setFormateur(formateur);
		
		fservice.ajouter(formation_new);
		}
		catch (JacksonException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return formation_new;
		
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
	
	@GetMapping("/Formations/{id}/participants/{idp}")
	public void deleteFormation_participant(@PathVariable("id") int id, @PathVariable("idp") int idp){
		List<Formation> liste = participantService.getParticipant_id(idp).getFormations();
		List<Formation> list = liste.stream().filter(f -> f.getId() != id).collect(Collectors.toList());
		List<Paiement> paye = paiementService.getPaiements_idParticipants(idp);
		for (Paiement p : paye) {
			if(p.getFormation().getId() == id) {
				paiementService.suppPaiement(p.getId());
			}
		}
		Participant participant = participantService.getParticipant_id(idp);
		participant.setFormations(list);
		participantService.addParticipant(participant);
		
		
	}
	
}
