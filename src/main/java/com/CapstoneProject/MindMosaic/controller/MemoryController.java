package com.CapstoneProject.MindMosaic.controller;

import com.CapstoneProject.MindMosaic.dto.MemoryRequestDTO;
import com.CapstoneProject.MindMosaic.dto.MemoryResponseDTO;
import com.CapstoneProject.MindMosaic.service.MemoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memories")
public class MemoryController {

    @Autowired
    private MemoryService memoryService;

    @PostMapping
    public ResponseEntity<MemoryResponseDTO> createMemory(@RequestBody MemoryRequestDTO dto) {
        MemoryResponseDTO createdMemory = memoryService.createMemory(dto);
        return ResponseEntity.ok(createdMemory);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MemoryResponseDTO>> getMemoriesByPatient(@PathVariable Long patientId) {
        List<MemoryResponseDTO> memories = memoryService.getMemoriesByPatientId(patientId);
        return ResponseEntity.ok(memories);
    }
}