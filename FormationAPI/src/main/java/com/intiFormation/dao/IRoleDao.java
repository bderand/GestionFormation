package com.intiFormation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intiFormation.model.Role;

public interface IRoleDao extends JpaRepository<Role, Integer>{

	public Role findByNom(String nom);
}
