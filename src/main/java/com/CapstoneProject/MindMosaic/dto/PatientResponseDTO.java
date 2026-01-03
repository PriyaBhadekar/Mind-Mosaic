package com.CapstoneProject.MindMosaic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatientResponseDTO {

    private Long id;
    private String name;
    private int age;
    private String condition;
    private String notes;
    private Long caregiverId;
}
