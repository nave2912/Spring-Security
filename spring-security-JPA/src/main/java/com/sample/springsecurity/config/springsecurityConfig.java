package com.sample.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class springsecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    UserDetailsService userDetailsService;

	// *AUTHENTICATION PART*//
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder getPassword() {
		return NoOpPasswordEncoder.getInstance();
	}

	// *AUTHORIZATION PART*//
	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/user").hasAnyRole("user", "ceo").antMatchers("/admin")
				.hasAnyRole("admin", "ceo").antMatchers("/ceo").hasRole("ceo").and().formLogin();
	}
}

//NoOp is not recommened way to use.Always password must be encoded.
