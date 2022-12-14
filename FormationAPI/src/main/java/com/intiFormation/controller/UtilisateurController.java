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
import org.springframework.web.bind.annotation.RestController;

import com.intiFormation.model.Utilisateur;
import com.intiFormation.service.IUtilisateurService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class UtilisateurController {

	
	@Autowired
	IUtilisateurService utilisateurService;
	
	@GetMapping("/utilisateurs")
	public List<Utilisateur> getUtilisateur_all() {
		
		return utilisateurService.getUtilisateurs_all();
		
		
	}
	
	@GetMapping("/utilisateurs/{id}")
	public Utilisateur getUtilisateur_id(@PathVariable("id") int id) {
		
		return utilisateurService.getUtilisateur_id(id);
	}
	
	@GetMapping("/utilisateurs/username/{username}")
	public Utilisateur getUtilisateur_username(@PathVariable("username") String username) {
		
		return utilisateurService.getUtilisateur_username(username);
	}
	
	@GetMapping("/utilisateurs/roles/{role}")
	public List<Utilisateur> getUtilisateurs_role(@PathVariable("role") String nom) {
		
		return utilisateurService.getUtilisateurByRole(nom);
	}
	
	@PostMapping("/utilisateurs")
	public Utilisateur ajoutUtilisateur(@RequestBody Utilisateur user) {
		
		return utilisateurService.addUtilisateur(user);
	}
	
	@DeleteMapping("/utilisateurs/{id}")
	public void suppUtilisateur(@PathVariable("id") int id) {
		
		utilisateurService.suppUtilisateur(id);
	}
	
	@DeleteMapping("/utilisateurs")
	public void suppUtilisateur(@RequestBody Utilisateur u) {
		
		utilisateurService.suppUtilisateur(u);
	}
}
