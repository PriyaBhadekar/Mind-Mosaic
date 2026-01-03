package com.CapstoneProject.MindMosaic.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class AssistantSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One session per patient
    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Enumerated(EnumType.STRING)
    private SessionStatus sessionStatus;

    @Enumerated(EnumType.STRING)
    private AssistantMode currentMode;

    private String lastAction;
    // Example: "REMINDER_GIVEN", "GAME_STARTED"

    private int engagementScore;
    // Increases on positive interaction, decreases otherwise

    private int escalationCount;

    private LocalDateTime lastInteractionTime;

    private int conversationTurns;


}
