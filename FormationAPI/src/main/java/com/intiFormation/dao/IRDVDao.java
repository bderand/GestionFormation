package com.intiFormation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intiFormation.model.RDV;


public interface IRDVDao extends JpaRepository<RDV, Integer>{
	
	public List<RDV> findByCommercial_id(int id);
	public List<RDV> findByPersonne_id(int id);
}
