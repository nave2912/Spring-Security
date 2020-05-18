package com.sample.springsecurity.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecuirtyController {

	@GetMapping("/")
	public Map<String, String> loginDetails() {
		UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder
				.getContext().getAuthentication();
		LdapUserDetailsImpl principal = (LdapUserDetailsImpl) authentication.getPrincipal();
		Map<String, String> loginDetails = new HashMap<String, String>();
		loginDetails.put("UserName", principal.getUsername());
		loginDetails.put("Authorities", String.valueOf(principal.getAuthorities()));
		return loginDetails;
	}

	
	@GetMapping("/admin")
	public String testadmin() {
		return "AdminLogin : Authentication success so please refer configuration class";
	}

	@GetMapping("/user")
	public String testuser() {
		return "UserLogin : Authentication success so please refer configuration class";
	}

	@GetMapping("/ceo")
	public String testForPermitAll() {
		return "CEOLogin : Authentication success so please refer configuration class";
	}

}
