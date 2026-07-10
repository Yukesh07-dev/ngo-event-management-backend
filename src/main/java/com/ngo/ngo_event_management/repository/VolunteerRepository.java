package com.ngo.ngo_event_management.repository;

import com.ngo.ngo_event_management.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {
}