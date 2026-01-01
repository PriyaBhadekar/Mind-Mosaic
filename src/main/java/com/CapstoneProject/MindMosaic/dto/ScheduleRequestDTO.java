package com.CapstoneProject.MindMosaic.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleRequestDTO {
    private String description;
    private LocalDateTime scheduledTime;
    private Long patientId;
}


