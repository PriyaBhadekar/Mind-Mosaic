package com.CapstoneProject.MindMosaic.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoryResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String memoryType;
    private Long patientId;
}
