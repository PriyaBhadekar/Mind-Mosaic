package com.CapstoneProject.MindMosaic.service;

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

    public PatientResponseDTO createPatient(PatientRequestDTO dto) {
        Patient patient = new Patient();
        patient.setFullName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setGender(dto.getGender());
        patient.setPhoneNumber(dto.getContact());
        patient.setMedicalCondition(dto.getCondition());
        patient.setNotes(dto.getNotes());

        patient.setCaregiverCode("CG-" + UUID.randomUUID().toString());

        Patient saved = patientRepository.save(patient);

        return new PatientResponseDTO(
                saved.getId(),
                saved.getFullName(),
                saved.getAge(),
                saved.getMedicalCondition(),
                saved.getNotes()
        );
    }

    public List<PatientResponseDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(p -> new PatientResponseDTO(
                        p.getId(),
                        p.getFullName(),
                        p.getAge(),
                        p.getMedicalCondition(),
                        p.getNotes()
                ))
                .toList();
    }
}