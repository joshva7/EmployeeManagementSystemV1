package com.example.emplyee_manager_sysetm.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.emplyee_manager_sysetm.Exceptions.CustomeNullPointerExcepition;
import com.example.emplyee_manager_sysetm.eneitys.EmplyeeEntity;
import com.example.emplyee_manager_sysetm.eneitys.OtpEntity;
import com.example.emplyee_manager_sysetm.repostiry.Employeerepo;
import com.example.emplyee_manager_sysetm.repostiry.Otprepo;

@Service
public class OtpService {
	private final Otprepo repo;
	private final Employeerepo emprepo;
	@Autowired
	JavaMailSender mailSender;
	@Autowired
	BCryptPasswordEncoder passwordencourder;

	public OtpService(Otprepo repo, Employeerepo emprepo) {
		this.repo = repo;
		this.emprepo = emprepo;
	}

	@Value("${spring.mail.username}")
	private String fromMail;

	public void otpSend(String email) {
		SecureRandom scurerandomnum = new SecureRandom();
		int otp = 100000 + scurerandomnum.nextInt(900000);
		String OtpString = Integer.toString(otp);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(fromMail);
		mail.setTo(email);
		mail.setSubject("OTP Verification");
		mail.setText("Your OTP is " + OtpString);
		mailSender.send(mail);
		OtpEntity otpEntity = new OtpEntity();
		otpEntity.setEmail(email);
		otpEntity.setOtp(OtpString);
		LocalDateTime exptime = LocalDateTime.now().plusMinutes(5);
		otpEntity.setExpTime(exptime);
		otpEntity.setVerfied(false);
		repo.save(otpEntity);
	}

	public String verifedOtp(String email, String otp) {
		LocalDateTime expTime = LocalDateTime.now();
		OtpEntity otpEntity = repo.findByEmail(email);
		if (otpEntity == null) {
			throw new CustomeNullPointerExcepition("Email Opject is not found");
		}
		if (!otp.equals(otpEntity.getOtp())) {
			return "Invalid OTP";
		}
		if (expTime.isAfter(otpEntity.getExpTime())) {
			repo.delete(otpEntity);
			return "ExpiryTime";
		}
		System.out.println("Veridef");
		otpEntity.setVerfied(true);
		repo.save(otpEntity);
		return "Success";
	}

	public String resetPassword(String email, String password) {
		OtpEntity otpEntity = repo.findByEmail(email);
		EmplyeeEntity emp = emprepo.findByEmail(email);
		System.out.println("otp " + otpEntity.getVerfied());
		if (!otpEntity.getVerfied()) {
			return "Your are not User this End point";
		}
		emp.setEmail(otpEntity.getEmail());
		emp.setPassword(passwordencourder.encode(password));
		emprepo.save(emp);
		return "Sucessfully update the password";
	}
}
