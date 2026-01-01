package com.CapstoneProject.MindMosaic.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Memory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private MemoryType memoryType;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}