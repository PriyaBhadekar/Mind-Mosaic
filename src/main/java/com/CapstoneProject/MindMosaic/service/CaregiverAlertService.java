package com.CapstoneProject.MindMosaic.service;

import com.CapstoneProject.MindMosaic.dto.CaregiverAlertDTO;
import com.CapstoneProject.MindMosaic.entity.*;
import com.CapstoneProject.MindMosaic.repository.CaregiverAlertRepository;
import com.CapstoneProject.MindMosaic.repository.CaregiverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CaregiverAlertService {

    private final CaregiverAlertRepository alertRepository;
    private final CaregiverRepository caregiverRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public void triggerAlert(
            Caregiver caregiver,
            Patient patient,
            AlertType alertType,
            String message) {

        CaregiverAlert alert = new CaregiverAlert();
        alert.setCaregiver(caregiver);
        alert.setPatient(patient);
        alert.setAlertType(alertType);
        alert.setMessage(message);
        alert.setAcknowledged(false);
        alert.setCreatedAt(LocalDateTime.now());

        CaregiverAlert saved = alertRepository.save(alert);

        CaregiverAlertDTO dto = new CaregiverAlertDTO(
                saved.getId(),
                patient.getId(),
                alertType.name(),
                message
        );

        // 🔥 REAL-TIME PUSH
        messagingTemplate.convertAndSend(
                "/topic/caregiver/" + caregiver.getId(),
                dto
        );
    }
}
