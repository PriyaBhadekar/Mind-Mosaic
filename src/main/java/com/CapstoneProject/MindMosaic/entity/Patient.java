package com.CapstoneProject.MindMosaic.entity;

// File: src/main/java/your/package/entity/Patient.java
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private Integer age;
    private String gender;
    private String phoneNumber;
    private String medicalCondition;
    private String caregiverCode;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "caregiver_id")
    @JsonIgnore
    private Caregiver caregiver;
}
