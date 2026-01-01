package com.CapstoneProject.MindMosaic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.CapstoneProject.MindMosaic.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
