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

import com.intiFormation.model.Personne;
import com.intiFormation.model.RDV;
import com.intiFormation.service.IRDVService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class RDVController {
	
	@Autowired
	private IRDVService rservice;
	
	@GetMapping("/rdvs")
	public List<RDV> getall(){
		List<RDV> rdvs = rservice.affichertous();
		return rdvs;
	}
	
	@GetMapping("/rdvs/{id}")
	public RDV getbyid(@PathVariable("id") int id) {
		RDV rdv = rservice.afficherparId(id);
		return rdv;
	}
	
	@PostMapping("/rdvs")
	public void post(@RequestBody RDV r) {
		rservice.ajouter(r);
	}
	
	@DeleteMapping("/rdvs/{id}")
	public void delete(@PathVariable("id") int id) {
		rservice.supprimer(id);
	}
	
	@PutMapping("/rdvs")
	public void put(@RequestBody RDV r) {
		rservice.modifier(r);
	}
	
	

}
