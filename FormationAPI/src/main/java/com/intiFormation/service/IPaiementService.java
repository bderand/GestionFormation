package com.intiFormation.service;

import java.util.List;

import com.intiFormation.model.Paiement;

public interface IPaiementService {

	public List<Paiement> getPaiements_all();
	public List<Paiement> getPaiements_idParticipants(int id_participant);
	public List<Paiement> getPaiements_idParticipantsFormation(int id_participant, int id_formation);
	public Paiement getPaiement_id(int id_paiement);
	public Paiement addPaiement(Paiement paiement);
	public void suppPaiement(Paiement p);
	public void suppPaiement(int id_paiement);
	
	public float RestantPaiement(int id_participant, int id_formation);
}
