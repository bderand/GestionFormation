package com.intiFormation.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;




@EnableWebSecurity
@Configuration
public class Security extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private RequestFilter jwtrequestfilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService);
	}
	
	
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	http.csrf().disable()
	.authorizeRequests().antMatchers(HttpMethod.GET,"/api/utilisateurs/username/**","/documents/**").permitAll()
	.antMatchers("/api/authenticates").permitAll()
	.antMatchers(HttpMethod.OPTIONS).permitAll()
	//.requestMatchers(req -> req.getRequestURI().contains("admin")).hasAuthority("admin")
	.anyRequest().authenticated()
	.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	.and().addFilterBefore(jwtrequestfilter, UsernamePasswordAuthenticationFilter.class);
	
		
	}

}

