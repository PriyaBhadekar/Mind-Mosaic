package com.CapstoneProject.MindMosaic.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class PatientInteraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Enumerated(EnumType.STRING)
    private InteractionType interactionType;

    @Column(length = 2000)
    private String responseText;

    @Enumerated(EnumType.STRING)
    private SentimentType sentiment;

    private LocalDateTime timestamp;
}
