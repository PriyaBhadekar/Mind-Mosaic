package com.CapstoneProject.MindMosaic.repository;

import com.CapstoneProject.MindMosaic.entity.Caregiver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CaregiverRepository extends JpaRepository<Caregiver, Long> {

    // ✅ Find caregiver by unique code
    Optional<Caregiver> findByUniqueCode(String uniqueCode);
}
