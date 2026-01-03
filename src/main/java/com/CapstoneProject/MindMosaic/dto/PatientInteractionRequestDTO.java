package com.CapstoneProject.MindMosaic.dto;

import lombok.Data;

@Data
public class PatientInteractionRequestDTO {

    private Long patientId;
    private String interactionType;  // VOICE / GAME / NO_RESPONSE
    private String responseText;      // Speech-to-text result
}
