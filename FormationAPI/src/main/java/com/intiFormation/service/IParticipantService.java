package com.intiFormation.service;

import java.util.List;

import com.intiFormation.model.Participant;

public interface IParticipantService  {

	public List<Participant> getParticipants_all();
	public Participant getParticipant_id(int id_participant);
	public Participant addParticipant(Participant participant);
	public void suppParticipant(int id_participant);
	
}
