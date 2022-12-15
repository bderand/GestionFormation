package com.intiFormation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intiFormation.model.Formateur;

public interface IFormateurDao extends JpaRepository<Formateur, Integer>{

}
