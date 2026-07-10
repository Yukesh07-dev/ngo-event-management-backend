package com.ngo.ngo_event_management.repository;

import com.ngo.ngo_event_management.entity.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRegistrationRepository
        extends JpaRepository<EventRegistration,Integer> {

    List<EventRegistration> findByUserId(Integer userId);

    List<EventRegistration> findByEventId(Integer eventId);

    boolean existsByUserIdAndEventId(
            Integer userId,
            Integer eventId
    );
}