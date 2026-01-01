package com.CapstoneProject.MindMosaic.service;

import com.CapstoneProject.MindMosaic.dto.CaregiverRequestDTO;
import com.CapstoneProject.MindMosaic.dto.CaregiverResponseDTO;
import com.CapstoneProject.MindMosaic.entity.Caregiver;
import com.CapstoneProject.MindMosaic.entity.Patient;
import com.CapstoneProject.MindMosaic.repository.CaregiverRepository;
import com.CapstoneProject.MindMosaic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaregiverService {

    @Autowired
    private CaregiverRepository caregiverRepository;

    @Autowired
    private PatientRepository patientRepository;

    // CREATE caregiver
    public CaregiverResponseDTO createCaregiver(CaregiverRequestDTO dto) {
        Caregiver caregiver = new Caregiver();
        caregiver.setName(dto.getName());
        caregiver.setPhoneNumber(dto.getPhoneNumber());
        caregiver.setEmail(dto.getEmail());
        caregiver.setAddress(dto.getAddress());

        Caregiver saved = caregiverRepository.save(caregiver);

        CaregiverResponseDTO response = new CaregiverResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setPhoneNumber(saved.getPhoneNumber());
        response.setEmail(saved.getEmail());
        response.setAddress(saved.getAddress());

        return response;
    }

    // GET all caregivers
    public List<CaregiverResponseDTO> getAllCaregivers() {
        return caregiverRepository.findAll()
                .stream()
                .map(c -> {
                    CaregiverResponseDTO dto = new CaregiverResponseDTO();
                    dto.setId(c.getId());
                    dto.setName(c.getName());
                    dto.setPhoneNumber(c.getPhoneNumber());
                    dto.setEmail(c.getEmail());
                    dto.setAddress(c.getAddress());
                    return dto;
                })
                .toList();
    }

    // GET caregiver by ID
    public CaregiverResponseDTO getCaregiverById(Long id) {
        Caregiver caregiver = caregiverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Caregiver not found"));

        CaregiverResponseDTO dto = new CaregiverResponseDTO();
        dto.setId(caregiver.getId());
        dto.setName(caregiver.getName());
        dto.setPhoneNumber(caregiver.getPhoneNumber());
        dto.setEmail(caregiver.getEmail());
        dto.setAddress(caregiver.getAddress());

        return dto;
    }

    public void linkPatientToCaregiver(Long caregiverId, Long patientId) {
        Caregiver caregiver = caregiverRepository.findById(caregiverId)
                .orElseThrow(() -> new RuntimeException("Caregiver not found"));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setCaregiver(caregiver);
        patientRepository.save(patient);
    }
}
