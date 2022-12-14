package com.intiFormation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.intiFormation.dao.IParticipantDao;
import com.intiFormation.model.Participant;

public class ParticipantService implements IParticipantService {

	
	@Autowired
	IParticipantDao participantDao;
	
	@Override
	public List<Participant> getParticipants_all() {
		// TODO Auto-generated method stub
		return participantDao.findAll();
	}

	@Override
	public Participant getParticipant_id(int id_participant) {
		Optional<Participant> op = participantDao.findById(id_participant);
		if(op.isPresent())return op.get();
		else return null;
	}

	@Override
	public Participant addParticipant(Participant participant) {
		// TODO Auto-generated method stub
		return participantDao.save(participant);
	}

	@Override
	public void suppParticipant(int id_participant) {
		// TODO Auto-generated method stub
		participantDao.deleteById(id_participant);
	}

}
