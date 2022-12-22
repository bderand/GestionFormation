package com.intiFormation.service;

import java.util.List;

import com.intiFormation.model.Role;

public interface IRoleService {

	public Role getRole_nom(String nom);
	public Role addRole(Role role);
	public void suppRole(int id_role);
	public List<Role> getAll();
}
