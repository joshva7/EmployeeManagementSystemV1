package com.example.emplyee_manager_sysetm.eneitys;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OtpEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String email;
	private String Otp;
	private LocalDateTime expTime;
	private boolean Verfied;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return Otp;
	}

	public void setOtp(String otp) {
		Otp = otp;
	}

	public LocalDateTime getExpTime() {
		return expTime;
	}

	public void setExpTime(LocalDateTime expTime) {
		this.expTime = expTime;
	}

	public boolean getVerfied() {
		return Verfied;
	}

	public void setVerfied(boolean verfied) {
		Verfied = verfied;
	}
}
