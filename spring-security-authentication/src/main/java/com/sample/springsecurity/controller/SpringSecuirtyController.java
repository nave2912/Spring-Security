package com.sample.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecuirtyController {
	@GetMapping("/admin")
	public String testadmin() {
		return "AdminLogin : Authentication success so please refer configuration class";
	}
	
	@GetMapping("/user")
	public String testuser() {
		return "UserLogin : Authentication success so please refer configuration class";
	}
}
