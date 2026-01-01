package com.CapstoneProject.MindMosaic.dto;

import lombok.Data;

@Data
public class MemoryRequestDTO {
    private String title;
    private String description;
    private String imageUrl;
    private String memoryType;
    private Long patientId;
}