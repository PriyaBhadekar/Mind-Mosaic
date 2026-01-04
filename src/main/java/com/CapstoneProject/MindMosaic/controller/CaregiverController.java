package com.CapstoneProject.MindMosaic.controller;

import com.CapstoneProject.MindMosaic.dto.CaregiverRequestDTO;
import com.CapstoneProject.MindMosaic.dto.CaregiverResponseDTO;
import com.CapstoneProject.MindMosaic.entity.Patient;
import com.CapstoneProject.MindMosaic.service.CaregiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/caregivers")
@CrossOrigin("*")
public class CaregiverController {

    @Autowired
    private CaregiverService caregiverService;

    // ✅ CREATE caregiver
    @PostMapping
    public ResponseEntity<CaregiverResponseDTO> createCaregiver(
            @RequestBody CaregiverRequestDTO dto) {
        return ResponseEntity.ok(caregiverService.createCaregiver(dto));
    }

    // ✅ GET all caregivers
    @GetMapping
    public ResponseEntity<List<CaregiverResponseDTO>> getAllCaregivers() {
        return ResponseEntity.ok(caregiverService.getAllCaregivers());
    }

    // ✅ GET caregiver by ID
    @GetMapping("/{id}")
    public ResponseEntity<CaregiverResponseDTO> getCaregiverById(
            @PathVariable Long id) {
        return ResponseEntity.ok(caregiverService.getCaregiverById(id));
    }

    // ✅ LINK patient to caregiver
    @PostMapping("/{caregiverId}/patients/{patientId}")
    public ResponseEntity<String> linkPatientToCaregiver(
            @PathVariable Long caregiverId,
            @PathVariable Long patientId) {

        caregiverService.linkPatientToCaregiver(caregiverId, patientId);
        return ResponseEntity.ok("Patient linked to caregiver successfully.");
    }

    // ✅ GET patients of a caregiver (FIXED PATH)
    @GetMapping("/{caregiverId}/patients")
    public ResponseEntity<List<Patient>> getPatientsOfCaregiver(
            @PathVariable Long caregiverId) {

        return ResponseEntity.ok(
                caregiverService.getPatientsOfCaregiver(caregiverId)
        );
    }
    // ✅ VALIDATE CAREGIVER CODE
@GetMapping("/validate/{code}")
public ResponseEntity<CaregiverResponseDTO> validateCaregiver(
        @PathVariable String code) {

    return ResponseEntity.ok(
            caregiverService.validateCaregiverCode(code)
    );
}

}
