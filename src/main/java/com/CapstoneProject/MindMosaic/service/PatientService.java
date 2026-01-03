package com.CapstoneProject.MindMosaic.service;

import com.CapstoneProject.MindMosaic.entity.Caregiver;
import com.CapstoneProject.MindMosaic.repository.CaregiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CapstoneProject.MindMosaic.dto.PatientRequestDTO;
import com.CapstoneProject.MindMosaic.dto.PatientResponseDTO;
import com.CapstoneProject.MindMosaic.entity.Patient;
import com.CapstoneProject.MindMosaic.repository.PatientRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private CaregiverRepository caregiverRepository;

    public PatientResponseDTO createPatient(PatientRequestDTO dto) {

        Patient patient = new Patient();
        patient.setFullName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setGender(dto.getGender());
        patient.setPhoneNumber(dto.getContact());
        patient.setMedicalCondition(dto.getCondition());
        patient.setNotes(dto.getNotes());
        patient.setCaregiverCode("CG-" + UUID.randomUUID());

        Patient saved = patientRepository.save(patient);

        return mapToDTO(saved);
    }

    public List<PatientResponseDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public void assignCaregiver(Long patientId, Long caregiverId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Caregiver caregiver = caregiverRepository.findById(caregiverId)
                .orElseThrow(() -> new RuntimeException("Caregiver not found"));

        patient.setCaregiver(caregiver);
        patientRepository.save(patient);
    }

    // 🔥 SINGLE SOURCE OF TRUTH
    private PatientResponseDTO mapToDTO(Patient patient) {
        return new PatientResponseDTO(
                patient.getId(),
                patient.getFullName(),
                patient.getAge(),
                patient.getMedicalCondition(),
                patient.getNotes(),
                patient.getCaregiver() != null ? patient.getCaregiver().getId() : null
        );
    }
}
