package com.sample.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecuirtyController {
	@GetMapping
	public String test() {
		return "Authentication success so please refer configuration class";
	}
}
