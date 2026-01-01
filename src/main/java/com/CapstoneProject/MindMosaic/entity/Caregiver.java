package com.CapstoneProject.MindMosaic.entity;

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

    @Column(unique = true, nullable = false)
    private String uniqueCode;   // 🔑 UNIQUE LINKING CODE

    @OneToMany(mappedBy = "caregiver", cascade = CascadeType.ALL)
    private List<Patient> patients;
}
