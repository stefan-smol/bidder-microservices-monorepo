package com.bidder.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bidder.apigateway.dto.UserAuthenticationInfo;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthFilter extends OncePerRequestFilter {

	private final UserDetailsService userDetailsService;
	private final String userServiceUrl = "http://USER-SERVICE/auth/validateToken";
	
	@Autowired
	private RestTemplate restTemplate;

	public AuthFilter(UserDetailsService userDetailsService, RestTemplate restTemplate) {
		this.userDetailsService = userDetailsService;
		this.restTemplate = restTemplate;
	}

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		if (request.getServletPath().contains("/api/v1/auth")) {
			filterChain.doFilter(request, response);
			return;
		}

		final String authHeader = request.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		String jwt = authHeader.substring(7);
		try {
			ResponseEntity<UserAuthenticationInfo> result = restTemplate.postForEntity(userServiceUrl, jwt,
					UserAuthenticationInfo.class);
			if (result.getStatusCode() == HttpStatus.OK) {
				UserAuthenticationInfo userInfo = result.getBody();

				List<GrantedAuthority> authorities = userInfo.getRoles().stream()
						.map(role -> (GrantedAuthority) new SimpleGrantedAuthority(role)).collect(Collectors.toList());

				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userInfo.getUsername(), null, authorities);

				SecurityContextHolder.getContext().setAuthentication(authToken);
			} else {
				// TO DO: Handle error
			}
		} catch (HttpClientErrorException e) {
			System.out.println("HttpClientErrorException in AuthFilter: " + e.getStatusCode() + " - " + e.getMessage());
			response.setStatus(e.getStatusCode().value());
			response.getWriter().write("Authentication error: " + e.getMessage());
			return;
		}

		filterChain.doFilter(request, response);
	}
}
