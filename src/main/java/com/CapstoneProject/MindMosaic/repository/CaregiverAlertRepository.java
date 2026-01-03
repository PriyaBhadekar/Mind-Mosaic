package com.CapstoneProject.MindMosaic.repository;

import com.CapstoneProject.MindMosaic.entity.CaregiverAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaregiverAlertRepository extends JpaRepository<CaregiverAlert, Long> {

    List<CaregiverAlert> findByCaregiverId(Long caregiverId);
}
