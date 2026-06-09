package com.example.emplyee_manager_sysetm.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecuritConfiger {
	@Autowired
	private JwtsFilter jwtfilter;

	@Bean
	public SecurityFilterChain securitfileterchain(HttpSecurity http) throws Exception {
		http.csrf(css -> css.disable());
		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers(HttpMethod.POST, "/api/login/**").permitAll();
			auth.requestMatchers(HttpMethod.POST, "/api/employee/creat").permitAll();
			auth.requestMatchers(HttpMethod.DELETE,"/api/employee/{id}").hasRole("ADMIN");
			auth.anyRequest().authenticated();
		});
		http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder PasswordEncoderhash() {
		return new BCryptPasswordEncoder();
	}
}
