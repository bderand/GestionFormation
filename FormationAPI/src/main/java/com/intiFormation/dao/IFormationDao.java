package com.intiFormation.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.intiFormation.model.Formation;

public interface IFormationDao extends JpaRepository<Formation, Integer>{

	
	
}
