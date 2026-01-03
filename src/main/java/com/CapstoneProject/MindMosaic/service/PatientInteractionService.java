package com.CapstoneProject.MindMosaic.service;

import com.CapstoneProject.MindMosaic.dto.PatientInteractionRequestDTO;
import com.CapstoneProject.MindMosaic.entity.*;
import com.CapstoneProject.MindMosaic.repository.AssistantSessionRepository;
import com.CapstoneProject.MindMosaic.repository.PatientInteractionRepository;
import com.CapstoneProject.MindMosaic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PatientInteractionService {

    private final PatientInteractionRepository interactionRepository;
    private final PatientRepository patientRepository;
    private final AssistantSessionRepository sessionRepository;
    private final SentimentAnalysisService sentimentService;
    private final CaregiverAlertService alertService;


    public void handleInteraction(PatientInteractionRequestDTO dto) {

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        SentimentType sentiment = sentimentService.analyzeSentiment(dto.getResponseText());

        PatientInteraction interaction = new PatientInteraction();
        interaction.setPatient(patient);
        interaction.setInteractionType(InteractionType.valueOf(dto.getInteractionType()));
        interaction.setResponseText(dto.getResponseText());
        interaction.setSentiment(sentiment);
        interaction.setTimestamp(LocalDateTime.now());

        interactionRepository.save(interaction);

        AssistantSession session = sessionRepository.findByPatientId(patient.getId())
                .orElseThrow(() -> new RuntimeException("Session not found"));

        // Engagement & escalation logic
        if (sentiment == SentimentType.POSITIVE) {
            session.setEngagementScore(session.getEngagementScore() + 1);
        }
        else if (sentiment == SentimentType.NEGATIVE) {
            session.setEngagementScore(session.getEngagementScore() - 1);
            session.setEscalationCount(session.getEscalationCount() + 1);
        }

        // 🔴 ADD THIS BLOCK (THIS IS THE FIX)
        if (session.getEscalationCount() >= 2) {

            Caregiver caregiver = patient.getCaregiver();

            if (caregiver != null) {
                alertService.triggerAlert(
                        caregiver,
                        patient,
                        AlertType.NEGATIVE_SENTIMENT,
                        "Patient shows repeated negative emotional responses"
                );
            }

            // Reset after alert
            session.setEscalationCount(0);
        }

        session.setLastInteractionTime(LocalDateTime.now());
        sessionRepository.save(session);
    }

}
