package com.CapstoneProject.MindMosaic.dto;

import lombok.Data;

@Data
public class CaregiverResponseDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String uniqueCode; // 🔑 returned to frontend
}
