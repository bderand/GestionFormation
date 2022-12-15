package com.intiFormation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intiFormation.config.PasswordUsernameGenerator;
import com.intiFormation.model.Formation;
import com.intiFormation.model.Paiement;
import com.intiFormation.model.Participant;
import com.intiFormation.model.Personne;
import com.intiFormation.model.Role;
import com.intiFormation.service.IFormationService;
import com.intiFormation.service.IPaiementService;
import com.intiFormation.service.IParticipantService;
import com.intiFormation.service.IPersonneService;
import com.intiFormation.service.IRoleService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class ParticipantController {
	
	@Autowired
	IParticipantService participantService;
	
	@Autowired
	IPersonneService personneService;
	
	@Autowired
	IFormationService formationService;
	
	@Autowired
	IPaiementService paiementService;
	
	@Autowired
	IRoleService roleService;
	
	@Autowired
	BCryptPasswordEncoder encode;
	
	@GetMapping("/participants")
	public List<Participant> getParticipants_all(){
		
		return participantService.getParticipants_all();
		
	}
	
	@GetMapping("/participants/{id}")
	public Participant getParticipant_id(@PathVariable("id") int id) {
		
		return participantService.getParticipant_id(id);
	}
	
	@GetMapping("/participants/formation/{id_formation}")
	public List<Participant> getParticipants_formation(@PathVariable("id_formation") int id_formation){
		
		return formationService.getParticipants_formationId(id_formation);
	}
	
	@PostMapping("/participants/nouveau")
	public Participant toParticipant(@RequestParam("personne") String personne, @RequestParam(value = "id_formation", required = false) int[] id_formations) {
		
		ObjectMapper obj_mapper = new ObjectMapper();
		try {
			List<Formation> formations = new ArrayList<>();
			Personne p = obj_mapper.readValue(personne, Personne.class);
			
			if(id_formations != null)
				for(int id_formation:id_formations)
				{	
					formations.add(formationService.getFormation_id(id_formation));
				}
			
			if(p != null && p.getId() != 0) {
				
				PasswordUsernameGenerator generator = new PasswordUsernameGenerator(p.getNom(), p.getPrenom());
				Role role = roleService.getRole_nom("participant");
				
				Participant participant = new Participant(p, role, formations);
				
				String username = generator.getUsername();
				String password = generator.getPassword();
				participant.setUsername(username);
				participant.setPassword(password);
				
				personneService.contact_participant(participant);
				
				participant.setPassword(encode.encode(password));
				participantService.addParticipant(participant);
				
			}
			
			
		}
		catch(JacksonException e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/participants")
	public Participant addParticipant(@RequestParam("id_participant") int id_participant, @RequestParam("id_formations") int[] id_formations) {
		
		Participant participant = participantService.getParticipant_id(id_participant);
		
		List<Formation> formations_new = new ArrayList<>();
		
		for(int id_formation:id_formations) {
			
			formations_new.add(formationService.getFormation_id(id_formation));
		}
		if(participant != null)
		{
			List<Formation> formations = participant.getFormations();
			formations.addAll(formations_new);
			
			participant.setFormations(formations);
			
			participantService.addParticipant(participant);
			
		}
	}
	
	@DeleteMapping("/participants/{id}")
	public void deleteParticipants(@PathVariable("id") int id) {
		
		List<Paiement> paiements = paiementService.getPaiements_idParticipants(id);
		for(Paiement paiement:paiements)
		{
			paiementService.suppPaiement(paiement);
		}
		
		participantService.suppParticipant(id);
	}

}
