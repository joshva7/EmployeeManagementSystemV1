package com.example.emplyee_manager_sysetm.repostiry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.emplyee_manager_sysetm.eneitys.OtpEntity;

public interface Otprepo extends JpaRepository<OtpEntity, Long> {
	OtpEntity findByEmail(String email);

}
