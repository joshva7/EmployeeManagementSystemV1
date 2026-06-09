package com.example.emplyee_manager_sysetm.controler;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emplyee_manager_sysetm.service.LoginService;

@RestController
public class LoginControler {
	private final LoginService service;

	public LoginControler(LoginService service) {
		this.service = service;
	}

	@PostMapping("/api/login/{email}/{password}")
	public String Logincontrole(@PathVariable String email, @PathVariable String password) {
		return service.Login(email, password);
	}
}
