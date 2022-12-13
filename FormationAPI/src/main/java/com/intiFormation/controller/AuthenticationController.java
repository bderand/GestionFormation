package com.intiFormation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intiFormation.config.AuthentificationRequest;
import com.intiFormation.config.AuthentificationResponse;
import com.intiFormation.config.jwtUtil;
import com.intiFormation.service.IUtilisateurService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class AuthenticationController {

	@Autowired
	IUtilisateurService utilisateurService;
	@Autowired
	BCryptPasswordEncoder encode;
	@Autowired
	UserDetailsService customDetailsService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	jwtUtil jwtokenUtil;
	
	@PostMapping("/authenticates")
	public ResponseEntity<?> connection(@RequestBody AuthentificationRequest authentificationRequest) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentificationRequest.getUsername(), authentificationRequest.getPassword()));
			
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username ou password",e);
		}
		
		final UserDetails userDetails = customDetailsService.loadUserByUsername(authentificationRequest.getUsername());
		final String jwt = jwtokenUtil.generateToken(userDetails);
		
		return new ResponseEntity<>(new AuthentificationResponse(jwt),HttpStatus.OK);
	}
}
