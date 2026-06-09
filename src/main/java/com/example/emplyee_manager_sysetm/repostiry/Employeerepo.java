package com.example.emplyee_manager_sysetm.repostiry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.emplyee_manager_sysetm.eneitys.EmplyeeEntity;

@Repository
public interface Employeerepo extends JpaRepository<EmplyeeEntity, Long> {
	List<EmplyeeEntity> findByName(String name);

	EmplyeeEntity findByEmail(String Email);
}
