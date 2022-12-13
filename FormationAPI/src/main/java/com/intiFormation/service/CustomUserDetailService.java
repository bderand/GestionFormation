package com.intiFormation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.intiFormation.model.Utilisateur;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	IUtilisateurService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur u = userService.getUtilisateur_username(username);
		if(u!=null)
		{
			return new User(u.getUsername(), u.getPassword(), this.getGrantedAuthority(u));
		
		}
		else throw new UsernameNotFoundException(username);
	}
	private List<GrantedAuthority> getGrantedAuthority(Utilisateur u){
		
		List<GrantedAuthority> listes = new ArrayList<>();
		listes.add(new SimpleGrantedAuthority(u.getRole().getNom()));
		return listes;
	}	

}
