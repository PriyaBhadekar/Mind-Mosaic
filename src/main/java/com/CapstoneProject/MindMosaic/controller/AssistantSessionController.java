package com.CapstoneProject.MindMosaic.controller;

import com.CapstoneProject.MindMosaic.dto.AssistantActionResponseDTO;
import com.CapstoneProject.MindMosaic.service.AssistantSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assistant")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AssistantSessionController {

    private final AssistantSessionService assistantSessionService;

    @GetMapping("/patients/{patientId}/next-action")
    public AssistantActionResponseDTO getNextAction(@PathVariable Long patientId) {
        return assistantSessionService.decideNextAction(patientId);
    }
}
