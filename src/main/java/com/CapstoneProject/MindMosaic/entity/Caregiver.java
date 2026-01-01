package com.CapstoneProject.MindMosaic.entity;


import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Caregiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private String email;
    private String address;

    @OneToMany(mappedBy = "caregiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Patient> patients;
}
