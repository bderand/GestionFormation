package com.intiFormation.controller;

import java.util.ArrayList;
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

import com.intiFormation.model.Personne;
import com.intiFormation.model.Utilisateur;
import com.intiFormation.service.IPersonneService;
import com.intiFormation.service.IUtilisateurService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class PersonneController {
	
	
	@Autowired
	private IPersonneService pservice;
	
	@Autowired
	private IUtilisateurService uservice;
	
	@PostMapping("/personnes")
	public void post(@RequestBody Personne p) {
		Personne p2 = pservice.afficher(p.getId());
		p2.setAge(p.getAge());
		p2.setNom(p.getNom());
		p2.setPrenom(p.getPrenom());
		p2.setEmail(p.getEmail());
		p2.setTel(p.getTel());
		pservice.ajout(p2);
	}
	
	@DeleteMapping("/personnes/{id}")
	public void delete(@PathVariable("id") int id) {
		pservice.supprimer(id);
	}
	
	@PutMapping("/personnes")
	public void put(@RequestBody Personne p) {
		pservice.modifier(p);
	}
	
	@GetMapping("/personnes")
	public List<Personne> getAll(){
		List<Personne> personnes = pservice.affichertous();
		List<Personne> prospects = new ArrayList<>();
		for(Personne p:personnes) {
			if(!(p instanceof Utilisateur)) {
				prospects.add(p);
			}
		}
		return prospects;
	}
	
	@GetMapping("/personnes/{id}")
	public Personne get(@PathVariable("id") int id) {
		Personne personne = pservice.afficher(id);
		return personne;
	}
	
	@GetMapping("/personnes/chercher/{nom}")
	public List<Personne> getbynom(@PathVariable("nom") String nom){
		List<Personne> personnes = pservice.chercher(nom);
		List<Personne> prospects = new ArrayList<>();
		for(Personne p:personnes) {
			if(!(p instanceof Utilisateur)) {
				prospects.add(p);
			}
		}

		return prospects;
	}
	
	@PostMapping("personnes/contact/{idp}")
	public void message(@PathVariable("idp") int idp, @RequestParam("idu") int idu, @RequestParam("titre") String titre, @RequestParam("message") String message) {
		Personne personne = pservice.afficher(idp);
		Utilisateur utilisateur = uservice.getUtilisateur_id(idu);
		pservice.contact(utilisateur.getEmail(), personne.getEmail(), titre, message);
	}

}
