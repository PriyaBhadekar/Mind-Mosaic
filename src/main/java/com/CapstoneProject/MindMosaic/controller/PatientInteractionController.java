package com.CapstoneProject.MindMosaic.controller;

import com.CapstoneProject.MindMosaic.dto.PatientInteractionRequestDTO;
import com.CapstoneProject.MindMosaic.service.PatientInteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interactions")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PatientInteractionController {

    private final PatientInteractionService interactionService;

    @PostMapping
    public ResponseEntity<String> submitInteraction(
            @RequestBody PatientInteractionRequestDTO dto) {

        interactionService.handleInteraction(dto);
        return ResponseEntity.ok("Interaction processed successfully");
    }
}
