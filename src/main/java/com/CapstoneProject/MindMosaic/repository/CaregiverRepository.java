package com.CapstoneProject.MindMosaic.repository;

import com.CapstoneProject.MindMosaic.entity.Caregiver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaregiverRepository extends JpaRepository<Caregiver, Long> {
    // Additional query methods if needed
}
