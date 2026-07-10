package com.ngo.ngo_event_management.repository;

import com.ngo.ngo_event_management.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Integer> {
}