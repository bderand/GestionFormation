package com.intiFormation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intiFormation.model.Personne;

public interface IPersonneDao extends JpaRepository<Personne, Integer>{

	public List<Personne> findByNomStartingWith(String nom);
}
