package com.CapstoneProject.MindMosaic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssistantActionResponseDTO {

    private String action;
    // SPEAK, START_GAME, ALERT

    private String message;
    // Text assistant should speak

    private String mode;
    // REMINDER / MEMORY_GAME / CHAT
}
