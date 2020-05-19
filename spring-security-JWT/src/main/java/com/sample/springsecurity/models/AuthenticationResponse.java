package com.sample.springsecurity.models;

public class AuthenticationResponse {
	private String jwt;

	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

}
