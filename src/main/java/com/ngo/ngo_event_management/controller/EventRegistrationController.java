package com.ngo.ngo_event_management.controller;

import com.ngo.ngo_event_management.entity.Event;
import com.ngo.ngo_event_management.repository.EventRepository;
import com.ngo.ngo_event_management.entity.EventRegistration;
import com.ngo.ngo_event_management.repository.EventRegistrationRepository;
// import com.ngo.ngo_event_management.repository.EventRepository;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/registrations")
public class EventRegistrationController {

private final EventRegistrationRepository repository;
private final EventRepository eventRepository;

public EventRegistrationController(
        EventRegistrationRepository repository,
        EventRepository eventRepository) {

    this.repository = repository;
    this.eventRepository = eventRepository;
}

    @GetMapping
    public List<EventRegistration> getAllRegistrations() {

        return repository.findAll();
    }

 @PostMapping
public Object registerForEvent(
        @RequestBody EventRegistration registration) {

    boolean alreadyExists =
        repository.existsByUserIdAndEventId(
                registration.getUserId(),
                registration.getEventId()
        );

if(alreadyExists){

    return Map.of(
            "message",
            "ALREADY_REGISTERED"
    );
}

Event event =
        eventRepository.findById(
                registration.getEventId()
        ).orElse(null);

if(event == null){

    return Map.of(
            "message",
            "EVENT_NOT_FOUND"
    );
}

if(event.getCurrentParticipants() >=
        event.getMaxParticipants()){

    return Map.of(
            "message",
            "EVENT_FULL"
    );
}

event.setCurrentParticipants(
        event.getCurrentParticipants() + 1
);

eventRepository.save(event);

repository.save(registration);

return Map.of(
        "message",
        "REGISTRATION_SUCCESSFUL"
);
}
    @GetMapping("/user/{userId}")
public List<EventRegistration> getUserRegistrations(
        @PathVariable Integer userId) {

    return repository.findByUserId(userId);
}
@GetMapping("/event/{eventId}")
public List<EventRegistration> getEventParticipants(
        @PathVariable Integer eventId){

    return repository.findByEventId(eventId);
}
}