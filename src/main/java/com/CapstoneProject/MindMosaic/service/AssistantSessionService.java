package com.CapstoneProject.MindMosaic.service;

import com.CapstoneProject.MindMosaic.dto.AssistantActionResponseDTO;
import com.CapstoneProject.MindMosaic.entity.*;
import com.CapstoneProject.MindMosaic.repository.AssistantSessionRepository;
import com.CapstoneProject.MindMosaic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AssistantSessionService {

    private final AssistantSessionRepository sessionRepository;
    private final PatientRepository patientRepository;

    public AssistantSession getOrCreateSession(Long patientId) {

        return sessionRepository.findByPatientId(patientId)
                .orElseGet(() -> {
                    AssistantSession session = new AssistantSession();
                    session.setPatient(
                            patientRepository.findById(patientId)
                                    .orElseThrow(() -> new RuntimeException("Patient not found"))
                    );
                    session.setSessionStatus(SessionStatus.ACTIVE);
                    session.setCurrentMode(AssistantMode.CONVERSATION);
                    session.setConversationTurns(0);
                    session.setEngagementScore(5);
                    session.setEscalationCount(0);
                    session.setLastInteractionTime(LocalDateTime.now());
                    return sessionRepository.save(session);
                });
    }

    public AssistantActionResponseDTO decideNextAction(Long patientId) {

        AssistantSession session = getOrCreateSession(patientId);
        session.setLastInteractionTime(LocalDateTime.now());

        AssistantActionResponseDTO response;

        // 🟢 1. CONVERSATION PHASE (FIRST 2 TURNS)
        if (session.getCurrentMode() == AssistantMode.CONVERSATION) {

            if (session.getConversationTurns() < 2) {
                session.setConversationTurns(session.getConversationTurns() + 1);

                response = new AssistantActionResponseDTO(
                        "CONTINUE_CONVERSATION",
                        "How are you feeling today?",
                        AssistantMode.CONVERSATION.name()
                );

                sessionRepository.save(session);
                return response;
            }

            // After conversation → decide next mode
            if (session.getEngagementScore() <= 2) {
                session.setCurrentMode(AssistantMode.MEMORY_GAME);
            } else {
                session.setCurrentMode(AssistantMode.CHAT);
            }

            sessionRepository.save(session);
        }

        // 🟡 2. MEMORY GAME MODE
        if (session.getCurrentMode() == AssistantMode.MEMORY_GAME) {

            response = new AssistantActionResponseDTO(
                    "START_GAME",
                    "Let’s try a small memory game together.",
                    AssistantMode.MEMORY_GAME.name()
            );

            sessionRepository.save(session);
            return response;
        }

        // 🔵 3. NORMAL CHAT MODE
        response = new AssistantActionResponseDTO(
                "SPEAK",
                "Would you like to talk more or do something fun?",
                AssistantMode.CHAT.name()
        );

        sessionRepository.save(session);
        return response;
    }
}
