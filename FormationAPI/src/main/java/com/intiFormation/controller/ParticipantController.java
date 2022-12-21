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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intiFormation.config.PasswordUsernameGenerator;
import com.intiFormation.model.Formation;
import com.intiFormation.model.Historique;
import com.intiFormation.model.Paiement;
import com.intiFormation.model.Participant;
import com.intiFormation.model.Personne;
import com.intiFormation.model.RDV;
import com.intiFormation.model.Role;
import com.intiFormation.service.IFormationService;
import com.intiFormation.service.IHistoriqueService;
import com.intiFormation.service.IPaiementService;
import com.intiFormation.service.IParticipantService;
import com.intiFormation.service.IPersonneService;
import com.intiFormation.service.IRDVService;
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
	IRDVService rdvService;
	
	@Autowired
	IHistoriqueService historiqueService; 
	
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
		Formation formation = formationService.afficherparId(id_formation);
		if(formation == null)return null;
		else return formation.getParticipants();
	}
	
	@GetMapping("/participants/{id_participant}/formations")
	public List<Formation> getFormation_participant(@PathVariable("id_participant") int id_participant)
	{
		Participant participant = participantService.getParticipant_id(id_participant);
		
		return participant.getFormations();
	}
	
	@PostMapping("/participants/nouveau")
	public Participant toParticipant(@RequestParam("id") int id,@RequestParam("age") int age, @RequestParam("email") String email, @RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("tel") String tel, @RequestParam(value = "id_formation", required = false) int id_form) {
		List<Integer> id_formations = new ArrayList<>();
	
		Participant participant = null;
		
		List<Formation> formations = new ArrayList<>();
		//Personne p = obj_mapper.readValue(personne, Personne.class);
		Personne p = new Personne(id,prenom,nom,age,email,tel);
		if(id_formations != null)
			for(int id_formation:id_formations)
			{	
				formations.add(formationService.afficherparId(id_formation));
			}
		
		if(p != null && p.getId() != 0) {
			
			PasswordUsernameGenerator generator = new PasswordUsernameGenerator(p.getNom(), p.getPrenom());
			Role role = roleService.getRole_nom("participant");
			
			participant = new Participant(p, role, formations);
			
			String username = generator.getUsername();
			String password = generator.getPassword();
			participant.setUsername(username);
			participant.setPassword(password);
			
			personneService.contact_participant(participant);
			
			participant.setPassword(encode.encode(password));
			
			participant = participantService.addParticipant(participant);
			
			
			List<RDV> rdv = rdvService.afficherparRDVpersonne(id);
			List<Historique> historique = historiqueService.afficherparPersonne(id);
			
			for (Historique hist : historique) {
				Personne ph = personneService.afficher(participant.getId());
				hist.setPersonne(ph);
				historiqueService.ajouter(hist);
			}
			
			for (RDV r : rdv) {
				Personne pr = personneService.afficher(participant.getId());
				r.setPersonne(pr);
				rdvService.ajouter(r);
			}
			personneService.supprimer(p.getId());
			
		}
		
		return participant;
	}
	
	@PostMapping("/participants/affectation")
	public void registerformation(@RequestParam("idp") int idp, @RequestParam("idf") int idf) {
		Participant participant = participantService.getParticipant_id(idp);
		Formation formation = formationService.afficherparId(idf);
		List<Formation> liste = participant.getFormations();
		liste.add(formation);
		participant.setFormations(liste);
		participantService.addParticipant(participant);
		String titre = "confirmation d'inscription à la formation de " + formation.getNom();
		String message = "bonjour, \n Vous êtes inscrit à la formation de " + formation.getNom() + ", afin de finaliser la formation, vous devez payez " + formation.getPrix() + "euro. \n pour payer la formation veuillez vous connecter et allez dans l'onglet paiement. \n cordialement \n l'équipe de formation";
		personneService.contact("javajeeappli@gmail.com", participant.getEmail(), titre, message);
	}
	
	@PostMapping("/participants")
	public Participant addParticipant(@RequestParam("id_participant") int id_participant, @RequestParam("id_formations") int[] id_formations) {
		
		Participant participant = participantService.getParticipant_id(id_participant);
		
		List<Formation> formations_new = new ArrayList<>();
		
		for(int id_formation:id_formations) {
			
			formations_new.add(formationService.afficherparId(id_formation));
		}
		if(participant != null)
		{
			List<Formation> formations = participant.getFormations();
			formations.addAll(formations_new);
			
			participant.setFormations(formations);
			
			participantService.addParticipant(participant);
			
		}
		
		return participantService.addParticipant(participant);
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
	
	@PostMapping("/participants/paiement")
	public void demanderPaiement(@RequestParam("id_participant") int id_participant, @RequestParam("id_formation") int id_formation, @RequestParam("reste") float reste)
	{
		Formation formation = formationService.afficherparId(id_formation);
		Participant participant = participantService.getParticipant_id(id_participant);
		String message = "Bonjour " + participant.getNom() + " " + participant.getPrenom() + "\n" +
		" Vous êtes inscrit à la formation \"" + formation.getNom() + "\" et vous devez payer le montant de " + 
		reste + " euros\n" + " N'oubliez pas qu'il est possible de régler en plusieurs fois. Bonne journée \n \n Votre assistant";
		String titre = "Demande de paiement pour la formation " + formation.getNom();
		personneService.contact("javajeeappli2@gmail.com", participant.getEmail(), titre, message);
	}
	
	@PostMapping("/participants/envoi/paiement")
	public void envoiPaiement(@RequestParam("argent") float argent, @RequestParam("id_paiement") int id_paiement) {
		
		Paiement paiement = paiementService.getPaiement_id(id_paiement);
		if(paiement != null)
		{
			paiement.setReste(paiement.getReste() - argent);
			paiementService.addPaiement(paiement);
		}
	}

}
