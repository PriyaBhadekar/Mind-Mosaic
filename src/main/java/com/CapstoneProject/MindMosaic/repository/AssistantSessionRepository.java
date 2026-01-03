package com.CapstoneProject.MindMosaic.repository;

import com.CapstoneProject.MindMosaic.entity.AssistantSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssistantSessionRepository extends JpaRepository<AssistantSession, Long> {

    Optional<AssistantSession> findByPatientId(Long patientId);
}
