package com.example.emplyee_manager_sysetm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.emplyee_manager_sysetm.Exceptions.EmployeeNotFound;
import com.example.emplyee_manager_sysetm.Security.JwtUtiles;
import com.example.emplyee_manager_sysetm.eneitys.EmplyeeEntity;
import com.example.emplyee_manager_sysetm.repostiry.Employeerepo;

@Service
public class LoginService {
	@Autowired
	public BCryptPasswordEncoder PasswordEncoderhash;
	private final Employeerepo repo;
	private final JwtUtiles jwtutiles;

	public LoginService(Employeerepo repo, JwtUtiles jwtutiles) {
		this.repo = repo;
		this.jwtutiles = jwtutiles;
	}

	public String Login(String email, String password) {
		String Email = email;
		EmplyeeEntity orgEmail = repo.findByEmail(Email);
		if (orgEmail == null) {
			throw new EmployeeNotFound("Employee Not Found");
		}
		if (!PasswordEncoderhash.matches(password, orgEmail.getPassword())) {
			return "Incorrect Password";
		}
		return jwtutiles.generateToken(Email,orgEmail.getRole());
	}
}
