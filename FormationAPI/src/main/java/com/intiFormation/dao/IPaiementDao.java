package com.intiFormation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intiFormation.model.Paiement;

public interface IPaiementDao extends JpaRepository<Paiement, Integer>{
	
	public List<Paiement> findByParticipant_id(int id_participant);
	public Paiement findByParticipant_idAndFormation_id(int id_participant, int id_formation);

}
