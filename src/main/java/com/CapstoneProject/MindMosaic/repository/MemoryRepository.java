package com.CapstoneProject.MindMosaic.repository;

import com.CapstoneProject.MindMosaic.entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
    List<Memory> findByPatientId(Long patientId);
}