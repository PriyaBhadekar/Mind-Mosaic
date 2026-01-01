package com.CapstoneProject.MindMosaic.repository;

import com.CapstoneProject.MindMosaic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient, Long> {
}
