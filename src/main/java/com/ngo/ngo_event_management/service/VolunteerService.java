package com.ngo.ngo_event_management.service;

import com.ngo.ngo_event_management.entity.Volunteer;
import com.ngo.ngo_event_management.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    public Optional<Volunteer> getVolunteerById(Integer id) {
        return volunteerRepository.findById(id);
    }

    public Volunteer createVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    public Volunteer updateVolunteer(Integer id, Volunteer volunteerDetails) {
        Optional<Volunteer> optionalVolunteer = volunteerRepository.findById(id);
        if (optionalVolunteer.isPresent()) {
            Volunteer volunteer = optionalVolunteer.get();
           volunteer.setFullName(volunteerDetails.getFullName());
            volunteer.setEmail(volunteerDetails.getEmail());
            volunteer.setPhone(volunteerDetails.getPhone());
            volunteer.setSkills(volunteerDetails.getSkills());
            volunteer.setAvailability(volunteerDetails.getAvailability());
            volunteer.setStatus(volunteerDetails.getStatus());
            return volunteerRepository.save(volunteer);
        }
        return null;
    }

    public boolean deleteVolunteer(Integer id) {
        if (volunteerRepository.existsById(id)) {
            volunteerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Volunteer> getActiveVolunteers() {
        return volunteerRepository.findAll().stream()
                .filter(v -> "ACTIVE".equals(v.getStatus()))
                .toList();
    }

    public List<Volunteer> getVolunteersByEmail(String email) {
        return volunteerRepository.findAll().stream()
                .filter(v -> email.equals(v.getEmail()))
                .toList();
    }
}
