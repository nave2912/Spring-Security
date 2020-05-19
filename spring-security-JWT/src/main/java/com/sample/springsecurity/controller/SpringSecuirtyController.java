package com.sample.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.springsecurity.models.AuthenticationRequest;
import com.sample.springsecurity.models.AuthenticationResponse;
import com.sample.springsecurity.util.JwtTokenUtil;

@RestController
public class SpringSecuirtyController {

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@GetMapping("/test")
		public String demo() {
			return "API call success with authentication";
		}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest)
			throws Exception {

		//authenticate using userName and password
		
		try {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception(e);
		}

		//after authenticate get JWT from server
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());
		String jwtToken = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
	}
}
