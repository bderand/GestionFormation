package com.intiFormation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiFormation.dao.IRoleDao;
import com.intiFormation.model.Role;

@Service
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
	
	@Override
	public List<Role> getAll(){
		List<Role> r = roleDao.findAll();
		return r;
	}

	
}
