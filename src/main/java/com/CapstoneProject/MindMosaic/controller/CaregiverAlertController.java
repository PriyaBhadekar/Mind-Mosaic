package com.CapstoneProject.MindMosaic.controller;

import com.CapstoneProject.MindMosaic.entity.CaregiverAlert;
import com.CapstoneProject.MindMosaic.repository.CaregiverAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CaregiverAlertController {

    private final CaregiverAlertRepository alertRepository;

    @GetMapping("/caregiver/{caregiverId}")
    public List<CaregiverAlert> getAlerts(@PathVariable Long caregiverId) {
        return alertRepository.findByCaregiverId(caregiverId);
    }
}
