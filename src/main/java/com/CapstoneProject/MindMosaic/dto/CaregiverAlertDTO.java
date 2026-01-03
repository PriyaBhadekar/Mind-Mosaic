package com.CapstoneProject.MindMosaic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CaregiverAlertDTO {

    private Long alertId;
    private Long patientId;
    private String alertType;
    private String message;
}
