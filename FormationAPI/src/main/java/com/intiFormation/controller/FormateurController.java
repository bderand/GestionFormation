package com.intiFormation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intiFormation.config.PasswordUsernameGenerator;
import com.intiFormation.model.Formateur;
import com.intiFormation.model.Formation;
import com.intiFormation.model.Role;
import com.intiFormation.service.IFormateurService;
import com.intiFormation.service.IFormationService;
import com.intiFormation.service.IPersonneService;
import com.intiFormation.service.IRoleService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class FormateurController {
	
	@Autowired
	private IFormateurService fservice;
	
	@Autowired
	private IFormationService formationService;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IPersonneService personneService;
	
	@Autowired
	private BCryptPasswordEncoder encode;
	
	@PostMapping("/Formateurs")
	public void post(@RequestBody Formateur f) {
		
		System.out.println(f.getNom());
		PasswordUsernameGenerator generator = new PasswordUsernameGenerator(f.getNom(), f.getPrenom());
		Role role = roleService.getRole_nom("formateur");
		
		String password = generator.getPassword();
		f.setRole(role);
		f.setPassword(password);
		
		personneService.contact_participant(f);
		
		f.setPassword(encode.encode(password));
		
		fservice.ajouter(f);

	}
	
	@DeleteMapping("/Formateurs/{id}")
	public void delete(@PathVariable("id") int id) {
		Formateur formateur = fservice.afficherparId(id);
		if(formateur != null)
		{
			List<Formation> formations = formateur.getFormations();
			for(Formation f:formations) {
				f.setFormateur(null);
				formationService.ajouter(f);
			}
		}
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
