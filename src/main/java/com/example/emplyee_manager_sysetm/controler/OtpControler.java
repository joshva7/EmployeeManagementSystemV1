package com.example.emplyee_manager_sysetm.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.emplyee_manager_sysetm.service.OtpService;

@RestController
public class OtpControler {
	private final OtpService service;

	public OtpControler(OtpService service) {
		this.service = service;
	}

	@PostMapping("/api/otpsend/{email}")
	public void OtpSender(@PathVariable String email) {
		service.otpSend(email);
	}

	@GetMapping("/api/verified")
	public String verfiedOtp(@RequestParam String email, @RequestParam String otp) {
		return service.verifedOtp(email, otp);
	}

	@PostMapping("/api/resetpassword")
	public String resetPassword(@RequestParam String email, @RequestParam String password) {
		return service.resetPassword(email, password);
	}
}
