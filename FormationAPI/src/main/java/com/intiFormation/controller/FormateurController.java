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
import org.springframework.web.bind.annotation.RestController;

import com.intiFormation.model.Formateur;
import com.intiFormation.model.Formation;
import com.intiFormation.service.IFormateurService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class FormateurController {
	
	@Autowired
	private IFormateurService fservice;
	
	@PostMapping("/Formateurs")
	public void post(@RequestBody Formateur f) {
		fservice.ajouter(f);
	}
	
	@DeleteMapping("/Formateurs/{id}")
	public void delete(@PathVariable("id") int id) {
		fservice.supprimer(id);
	}
	
	@PutMapping("/Formateurs")
	public void put(@RequestBody Formateur f) {
		
		Formateur formateur = fservice.afficherparId(f.getId());
		
		formateur.setNom(f.getNom());
		formateur.setPrenom(f.getPrenom());
		formateur.setAge(f.getAge());
		formateur.setTel(f.getTel());
		formateur.setEmail(f.getEmail());
		formateur.setUsername(f.getUsername());
		
		fservice.ajouter(formateur);
		
	}
	
	@GetMapping("/Formateurs")
	public List<Formateur> getAll(){
		List<Formateur> Formateurs = fservice.affichertous();
		return Formateurs;
	}
	
	@GetMapping("/Formateurs/{id}")
	public Formateur get(@PathVariable("id") int id) {
		Formateur Formateur = fservice.afficherparId(id);
		return Formateur;
	}
	
	@GetMapping("/Formateurs/{id}/formations")
	public List<Formation> getFormateurs_formations(@PathVariable("id") int id_formation){
		
		return fservice.afficherparId(id_formation).getFormations();
	}
}
