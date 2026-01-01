package com.CapstoneProject.MindMosaic.dto;

import lombok.Data;

@Data
public class PatientRequestDTO {
    private String name;
    private Integer age;
    private String gender;
    private String contact;
    private String condition;
    private String notes;
}