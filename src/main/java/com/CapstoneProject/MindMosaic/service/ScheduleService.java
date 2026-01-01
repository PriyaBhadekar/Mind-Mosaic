package com.CapstoneProject.MindMosaic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CapstoneProject.MindMosaic.dto.ScheduleRequestDTO;
import com.CapstoneProject.MindMosaic.dto.ScheduleResponseDTO;
import com.CapstoneProject.MindMosaic.entity.Patient;
import com.CapstoneProject.MindMosaic.entity.Schedule;
import com.CapstoneProject.MindMosaic.repository.PatientRepository;
import com.CapstoneProject.MindMosaic.repository.ScheduleRepository;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PatientRepository patientRepository;

    public ScheduleResponseDTO createSchedule(ScheduleRequestDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Schedule schedule = new Schedule();
        schedule.setDescription(dto.getDescription());
        schedule.setScheduledTime(dto.getScheduledTime());
        schedule.setPatient(patient);

        Schedule saved = scheduleRepository.save(schedule);

        return new ScheduleResponseDTO(
                saved.getId(),
                saved.getDescription(),
                saved.getScheduledTime(),
                saved.getPatient().getId()
        );
    }

    public List<ScheduleResponseDTO> getAllSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(s -> new ScheduleResponseDTO(
                        s.getId(),
                        s.getDescription(),
                        s.getScheduledTime(),
                        s.getPatient().getId()
                ))
                .toList();
    }

    public ScheduleResponseDTO updateSchedule(Long id, ScheduleRequestDTO dto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        schedule.setDescription(dto.getDescription());
        schedule.setScheduledTime(dto.getScheduledTime());

        Schedule updated = scheduleRepository.save(schedule);

        return new ScheduleResponseDTO(
                updated.getId(),
                updated.getDescription(),
                updated.getScheduledTime(),
                updated.getPatient().getId()
        );
    }

    public void deleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new RuntimeException("Schedule not found");
        }
        scheduleRepository.deleteById(id);
    }
}
