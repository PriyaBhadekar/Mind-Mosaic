package com.CapstoneProject.MindMosaic.service;

import com.CapstoneProject.MindMosaic.dto.MemoryRequestDTO;
import com.CapstoneProject.MindMosaic.dto.MemoryResponseDTO;
import com.CapstoneProject.MindMosaic.entity.Memory;
import com.CapstoneProject.MindMosaic.entity.MemoryType;
import com.CapstoneProject.MindMosaic.repository.MemoryRepository;
import com.CapstoneProject.MindMosaic.entity.Patient;
import com.CapstoneProject.MindMosaic.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemoryService {

    @Autowired
    private MemoryRepository memoryRepository;

    @Autowired
    private PatientRepository patientRepository;

    public MemoryResponseDTO createMemory(MemoryRequestDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Memory memory = new Memory();
        memory.setTitle(dto.getTitle());
        memory.setDescription(dto.getDescription());
        memory.setImageUrl(dto.getImageUrl());
        memory.setMemoryType(MemoryType.valueOf(dto.getMemoryType()));
        memory.setPatient(patient);

        Memory savedMemory = memoryRepository.save(memory);

        return new MemoryResponseDTO(
                savedMemory.getId(),
                savedMemory.getTitle(),
                savedMemory.getDescription(),
                savedMemory.getImageUrl(),
                savedMemory.getMemoryType().name(),
                savedMemory.getPatient().getId()
        );
    }

    public List<MemoryResponseDTO> getMemoriesByPatientId(Long patientId) {
        List<Memory> memories = memoryRepository.findByPatientId(patientId);
        return memories.stream()
                .map(memory -> new MemoryResponseDTO(
                        memory.getId(),
                        memory.getTitle(),
                        memory.getDescription(),
                        memory.getImageUrl(),
                        memory.getMemoryType().name(),
                        memory.getPatient().getId()
                ))
                .collect(Collectors.toList());
    }
}
