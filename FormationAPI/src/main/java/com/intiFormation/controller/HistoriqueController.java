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

import com.intiFormation.model.Historique;
import com.intiFormation.service.IHistoriqueService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class HistoriqueController {
	
	@Autowired
	private IHistoriqueService hservice;
	
	@GetMapping("/historiques")
	public List<Historique> affichertous(){
		List<Historique> historiques = hservice.affichertous();
		return historiques;
	}
	
	@GetMapping("/historiques/{id}")
	public Historique getbyId(@PathVariable("id") int id) {
		Historique historique = hservice.afficherparId(id);
		return historique;
	}
	
	@PostMapping("/historiques")
	public void post(@RequestBody Historique h) {
		hservice.ajouter(h);
	}
	
	@DeleteMapping("/historiques/{id}")
	public void delete(@PathVariable("id") int id) {
		hservice.supprimer(id);
	}
	
	@PutMapping("/historiques")
	public void put(@RequestBody Historique h) {
		hservice.modifier(h);
	}
	
}
