package com.intiFormation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intiFormation.model.RDV;


import com.intiFormation.service.IPersonneService;

import com.intiFormation.service.IRDVService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")


@EnableScheduling

public class RDVController {
	
	@Autowired
	private IRDVService rservice;
	


	@Autowired
	private IPersonneService pservice;
	

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
	
	@GetMapping("/rdvs/commercial/{id}")
	public List<RDV> getbyIdcommercial(@PathVariable("id") int id) {
		List<RDV> rdv = rservice.afficherparcommercial(id);
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
	

	

	@Scheduled(fixedRate = 1000)
	public void cronJobSch() {
		List<RDV> rdvs = rservice.affichertous();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String strDate = sdf.format(now);
		Calendar cal = Calendar.getInstance();
		for(int i=0;i<rdvs.size();i++) {
		    cal.setTime(rdvs.get(i).getRdv()); // sets calendar time/date
		    cal.add(Calendar.MINUTE, -30); // substract 30 min
		    cal.getTime();
			String str = sdf.format(cal.getTime());
			if(str.equals(strDate)) {
				String titre = "rappel du rendez-vous";
				String message = "bonjour \n nous vous rappelons que votre rendez-vous commencera dans 30 minute. \n veuillez-vous préparer \n cordialement \n l'équipe de la formation";
				pservice.contact("javajeeappli2@gmail.com", rdvs.get(i).getCommercial().getEmail(), titre, message);
			}
		}
		
		}


}
