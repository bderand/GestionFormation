package com.intiFormation.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiFormation.dao.IPaiementDao;
import com.intiFormation.model.Paiement;
import com.intiFormation.model.Participant;

@Service
public class PaiementService implements IPaiementService {

	@Autowired
	IPaiementDao paiementDao;

	@Autowired
	IFormationDao formationDao;
	
	@Override
	public List<Paiement> getPaiements_all() {
		// TODO Auto-generated method stub
		return paiementDao.findAll();
	}

	@Override
	public List<Paiement> getPaiements_idParticipants(int id_participant) {
		// TODO Auto-generated method stub
		return paiementDao.findByParticipant_id(id_participant);
	}

	@Override
	public List<Paiement> getPaiements_idParticipantsFormation(int id_participant, int id_formation){
		
		return paiementDao.findByParticipant_idAndFormation_id(id_participant, id_formation);
	}
	
	@Override
	public Paiement getPaiement_id(int id_paiement) {
		Optional<Paiement> op = paiementDao.findById(id_paiement);
		if(op.isPresent())return op.get();
		else return null;

	}

	@Override
	public Paiement addPaiement(Paiement paiement) {
		// TODO Auto-generated method stub
		return paiementDao.save(paiement);
	}

	@Override
	public void suppPaiement(Paiement p) {
		// TODO Auto-generated method stub
		paiementDao.delete(p);
	}

	@Override
	public void suppPaiement(int id_paiement) {
		// TODO Auto-generated method stub
		paiementDao.deleteById(id_paiement);
	}

	@Override
	public float RestantPaiement(int id_participant, int id_formation) {
		
		float somme_restant = 0.f;
		List<Paiement> paiements = this.getPaiements_idParticipantsFormation(id_participant, id_formation);
		Formation formation = formationService.getFormation_id(id_formation);
		if(paiements != null && formation != null)
		{
			float somme_paye = 0.f;
			float somme_total = formation.getPrix();
			
			for(Paiement p:paiements)
			{
				somme_paye += p.getReste(); 
			}
			
			somme_restant = somme_total - somme_paye;	
	
		}
		
		return 0.f;
	}
	
}

