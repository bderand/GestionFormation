package com.intiFormation.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.intiFormation.dao.IRoleDao;
import com.intiFormation.model.Role;

public class RoleService implements IRoleService {

	@Autowired
	IRoleDao roleDao;
	
	@Override
	public Role getRole_nom(String nom) {
		// TODO Auto-generated method stub
		return roleDao.findByNom(nom);
	}

	@Override
	public Role addRole(Role role) {
		// TODO Auto-generated method stub
		return roleDao.save(role);
	}

	@Override
	public void suppRole(int id_role) {
		// TODO Auto-generated method stub
		roleDao.deleteById(id_role);
	}

	
}
