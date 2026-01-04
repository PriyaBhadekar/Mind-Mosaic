package com.CapstoneProject.MindMosaic.service;

import com.CapstoneProject.MindMosaic.dto.CaregiverRequestDTO;
import com.CapstoneProject.MindMosaic.dto.CaregiverResponseDTO;
import com.CapstoneProject.MindMosaic.entity.Caregiver;
import com.CapstoneProject.MindMosaic.entity.Patient;
import com.CapstoneProject.MindMosaic.repository.CaregiverRepository;
import com.CapstoneProject.MindMosaic.repository.PatientRepository;
import com.CapstoneProject.MindMosaic.utils.UniqueCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaregiverService {

    @Autowired
    private CaregiverRepository caregiverRepository;

    @Autowired
    private PatientRepository patientRepository;

    public CaregiverResponseDTO createCaregiver(CaregiverRequestDTO dto) {

        Caregiver caregiver = new Caregiver();
        caregiver.setName(dto.getName());
        caregiver.setPhoneNumber(dto.getPhoneNumber());
        caregiver.setEmail(dto.getEmail());
        caregiver.setAddress(dto.getAddress());

        caregiver.setUniqueCode(
                UniqueCodeGenerator.generateCaregiverCode()
        );

        Caregiver saved = caregiverRepository.save(caregiver);

        CaregiverResponseDTO response = new CaregiverResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setPhoneNumber(saved.getPhoneNumber());
        response.setEmail(saved.getEmail());
        response.setAddress(saved.getAddress());
        response.setUniqueCode(saved.getUniqueCode());

        return response;
    }

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
                    dto.setUniqueCode(c.getUniqueCode());
                    return dto;
                })
                .toList();
    }

    public CaregiverResponseDTO getCaregiverById(Long id) {

        Caregiver caregiver = caregiverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Caregiver not found"));

        CaregiverResponseDTO dto = new CaregiverResponseDTO();
        dto.setId(caregiver.getId());
        dto.setName(caregiver.getName());
        dto.setPhoneNumber(caregiver.getPhoneNumber());
        dto.setEmail(caregiver.getEmail());
        dto.setAddress(caregiver.getAddress());
        dto.setUniqueCode(caregiver.getUniqueCode());

        return dto;
    }

    @Transactional
    public void linkPatientToCaregiver(Long caregiverId, Long patientId) {

        Caregiver caregiver = caregiverRepository.findById(caregiverId)
                .orElseThrow(() -> new RuntimeException("Caregiver not found"));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setCaregiver(caregiver);

        if (caregiver.getPatients() == null) {
            caregiver.setPatients(new ArrayList<>());
        }

        caregiver.getPatients().add(patient);

        patientRepository.save(patient);
    }

    public List<Patient> getPatientsOfCaregiver(Long caregiverId) {

        Caregiver caregiver = caregiverRepository.findById(caregiverId)
                .orElseThrow(() -> new RuntimeException("Caregiver not found"));

        return caregiver.getPatients();
    }
    // ✅ VALIDATE CAREGIVER UNIQUE CODE
public CaregiverResponseDTO validateCaregiverCode(String code) {

    Caregiver caregiver = caregiverRepository.findByUniqueCode(code)
            .orElseThrow(() -> new RuntimeException("Invalid caregiver code"));

    CaregiverResponseDTO dto = new CaregiverResponseDTO();
    dto.setId(caregiver.getId());
    dto.setName(caregiver.getName());
    dto.setPhoneNumber(caregiver.getPhoneNumber());
    dto.setEmail(caregiver.getEmail());
    dto.setAddress(caregiver.getAddress());
    dto.setUniqueCode(caregiver.getUniqueCode());

    return dto;
}

}
