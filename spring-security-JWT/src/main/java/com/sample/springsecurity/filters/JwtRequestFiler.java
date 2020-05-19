package com.sample.springsecurity.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sample.springsecurity.service.MyUserDetailsService;
import com.sample.springsecurity.util.JwtTokenUtil;

@Component
public class JwtRequestFiler extends OncePerRequestFilter {

	@Autowired
	JwtTokenUtil JwUtil;
	@Autowired
	MyUserDetailsService myUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader("Authorization");
		String jwt = null;
		String userName=null;
		if (authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			userName = JwUtil.getUsernameFromToken(jwt);
		}
		if (userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(userName);
			if (JwUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken userNamePasswordAuthToken =
						new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				userNamePasswordAuthToken.setDetails(new WebAuthenticationDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userNamePasswordAuthToken);
			}
			
		}
		
		filterChain.doFilter(request, response);
	}

}
