package com.intiFormation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intiFormation.model.Historique;

public interface IHistoriqueDao extends JpaRepository<Historique, Integer>{

	public List<Historique> findByCommercial_id(int id);
}
