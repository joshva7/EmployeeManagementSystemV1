package com.example.emplyee_manager_sysetm.Security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtsFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtiles jwtutiles;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader("Authorization");
		if (token != null && token.startsWith("Bearer ")) {
			String userName = jwtutiles.extractUsername(token);
			String role = jwtutiles.extractRole(token);
			SimpleGrantedAuthority roleauth = new SimpleGrantedAuthority("ROLE_" + role);
			System.out.println("Role"+role);
			System.out.println("RoleAuth "+roleauth);
			if (jwtutiles.validToken(token, userName)) {
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null,
						List.of(roleauth));
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		filterChain.doFilter(request, response);
	}

}
