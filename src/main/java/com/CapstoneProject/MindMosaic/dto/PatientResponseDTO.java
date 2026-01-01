package com.CapstoneProject.MindMosaic.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class PatientResponseDTO {
    private Long id;
    private String name;
    private Integer age;
    private String condition;
    private String notes;
}
