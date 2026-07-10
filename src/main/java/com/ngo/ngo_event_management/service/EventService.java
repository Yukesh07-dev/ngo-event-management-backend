package com.ngo.ngo_event_management.service;

import com.ngo.ngo_event_management.entity.Event;
import com.ngo.ngo_event_management.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Integer id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Integer id, Event eventDetails) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            event.setTitle(eventDetails.getTitle());
            event.setDescription(eventDetails.getDescription());
            event.setEventDate(eventDetails.getEventDate());
            event.setEventTime(eventDetails.getEventTime());
            event.setLocation(eventDetails.getLocation());
            event.setImageUrl(eventDetails.getImageUrl());
            event.setCategory(eventDetails.getCategory());
            event.setMaxParticipants(eventDetails.getMaxParticipants());
            return eventRepository.save(event);
        }
        return null;
    }

    public boolean deleteEvent(Integer id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Event> getUpcomingEvents() {
        return eventRepository.findAll().stream()
                .filter(e -> e.getEventDate() != null && e.getEventDate().isAfter(java.time.LocalDate.now()))
                .toList();
    }

    public List<Event> getActiveEvents() {
        return eventRepository.findAll().stream()
                .filter(e -> e.getCurrentParticipants() != null && e.getCurrentParticipants() < e.getMaxParticipants())
                .toList();
    }
}
