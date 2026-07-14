package com.ngo.ngo_event_management.controller;

import com.ngo.ngo_event_management.dto.DashboardStats;
import com.ngo.ngo_event_management.repository.*;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")

public class DashboardController {

    private final UserRepository userRepository;
    private final VolunteerRepository volunteerRepository;
    private final EventRepository eventRepository;
    private final DonationRepository donationRepository;
    private final CampaignRepository campaignRepository;

    public DashboardController(
            UserRepository userRepository,
            VolunteerRepository volunteerRepository,
            EventRepository eventRepository,
            DonationRepository donationRepository,
            CampaignRepository campaignRepository) {

        this.userRepository = userRepository;
        this.volunteerRepository = volunteerRepository;
        this.eventRepository = eventRepository;
        this.donationRepository = donationRepository;
        this.campaignRepository = campaignRepository;
    }

    @GetMapping
    public DashboardStats getStats(){

        DashboardStats stats =
                new DashboardStats();

        stats.setUsers(
                userRepository.count());

        stats.setVolunteers(
                volunteerRepository.count());

        stats.setEvents(
                eventRepository.count());

        stats.setDonations(
                donationRepository.count());

        stats.setCampaigns(
                campaignRepository.count());

        return stats;
    }
}