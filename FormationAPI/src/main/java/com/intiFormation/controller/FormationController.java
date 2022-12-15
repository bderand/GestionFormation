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

import com.intiFormation.model.Formation;
import com.intiFormation.service.IFormationService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class FormationController {
	
	@Autowired
	private IFormationService fservice;
	
	@PostMapping("/Formations")
	public void post(@RequestBody Formation f) {
		fservice.ajouter(f);
	}
	
	@DeleteMapping("/Formations/{id}")
	public void delete(@PathVariable("id") int id) {
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
	
	@GetMapping("/Formations/{id}")
	public Formation get(@PathVariable("id") int id) {
		Formation Formation = fservice.afficherparId(id);
		return Formation;
	}
}
