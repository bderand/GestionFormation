package com.intiFormation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intiFormation.model.RDV;


public interface IRDVDao extends JpaRepository<RDV, Integer>{
	
	
}
