package com.CapstoneProject.MindMosaic.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ScheduleResponseDTO {
    private Long id;
    private String description;
    private LocalDateTime scheduledTime;
    private Long patientId;
}
