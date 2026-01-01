package com.CapstoneProject.MindMosaic.service;

import com.CapstoneProject.MindMosaic.dto.CaregiverRequestDTO;
import com.CapstoneProject.MindMosaic.dto.CaregiverResponseDTO;
import com.CapstoneProject.MindMosaic.entity.Caregiver;
import com.CapstoneProject.MindMosaic.repository.CaregiverRepository;
import com.CapstoneProject.MindMosaic.utils.UniqueCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaregiverService {

    @Autowired
    private CaregiverRepository caregiverRepository;

    // ✅ CREATE CAREGIVER WITH UNIQUE CODE
    public CaregiverResponseDTO createCaregiver(CaregiverRequestDTO dto) {

        Caregiver caregiver = new Caregiver();
        caregiver.setName(dto.getName());
        caregiver.setPhoneNumber(dto.getPhoneNumber());
        caregiver.setEmail(dto.getEmail());
        caregiver.setAddress(dto.getAddress());

        // 🔑 Generate and assign unique code
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

    // ✅ GET all caregivers
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

    // ✅ GET caregiver by ID (FIXED)
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

    public void linkPatientToCaregiver(Long caregiverId, Long patientId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'linkPatientToCaregiver'");
    }
}
