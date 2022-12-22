package com.intiFormation.controller;

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

import com.intiFormation.model.Role;
import com.intiFormation.model.Utilisateur;
import com.intiFormation.service.IRoleService;
import com.intiFormation.service.IUtilisateurService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class UtilisateurController {

	
	@Autowired
	IUtilisateurService utilisateurService;
	
	@Autowired
	IRoleService rservice;
	
	@Autowired
	BCryptPasswordEncoder encode;
	
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
		
		Utilisateur utilisateur = null;
		int id_user = user.getId();
		user.setPassword(encode.encode(user.getPassword()));
		if(id_user != 0)
		{
			utilisateur = utilisateurService.getUtilisateur_id(user.getId());
			if(utilisateur != null)
			{
				utilisateur.setAge(user.getAge());
				utilisateur.setNom(user.getNom());
				utilisateur.setPrenom(user.getPrenom());
				utilisateur.setEmail(user.getEmail());
				utilisateur.setTel(user.getTel());
				utilisateur.setUsername(user.getUsername());
				utilisateur.setPassword(user.getPassword());
				utilisateurService.addUtilisateur(utilisateur);
			}
		}
		else
		{
			user.setPassword(encode.encode(user.getPassword()));
			utilisateur = utilisateurService.addUtilisateur(user);
		}
		
		return utilisateur;
	}
	
	@PostMapping("/utilisateurs/matched")
	public boolean changePassword(@RequestParam("id_user") int id_user, @RequestParam("mpts_new") String mpts_new, @RequestParam("mpts") String mpts) {
		
		Utilisateur user = utilisateurService.getUtilisateur_id(id_user);
		
		if(encode.matches(mpts, user.getPassword()))
		{
			user.setPassword(encode.encode(mpts_new));
			utilisateurService.addUtilisateur(user);
			return true;
		}
		else return false;
	}
	
	@DeleteMapping("/utilisateurs/{id}")
	public void suppUtilisateur(@PathVariable("id") int id) {
		
		utilisateurService.suppUtilisateur(id);
	}
	
	@DeleteMapping("/utilisateurs")
	public void suppUtilisateur(@RequestBody Utilisateur u) {
		
		utilisateurService.suppUtilisateur(u);
	}
	
	@GetMapping("/roles")
	public List<Role> afficherRole(){
		return rservice.getAll();
	}
	
	@GetMapping("/roles/{id}")
	public Role afficherR(@PathVariable("id") int id){
		return rservice.getbyid(id);
	}
}
