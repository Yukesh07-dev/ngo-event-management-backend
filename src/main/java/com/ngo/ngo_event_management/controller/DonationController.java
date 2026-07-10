package com.ngo.ngo_event_management.controller;

import com.ngo.ngo_event_management.entity.Donation;
import com.ngo.ngo_event_management.repository.DonationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
@CrossOrigin(origins="*")
public class DonationController {

    private final DonationRepository donationRepository;

    public DonationController(
            DonationRepository donationRepository) {

        this.donationRepository = donationRepository;
    }

    @GetMapping
    public List<Donation> getDonations() {
        return donationRepository.findAll();
    }

  @PostMapping
public Donation createDonation(
        @RequestBody Donation donation) {

    donation.setDonationDate(
            java.time.LocalDateTime.now());

    return donationRepository.save(donation);
}
    @GetMapping("/user/{email}")
public List<Donation> getUserDonations(
        @PathVariable String email) {

    return donationRepository.findByEmail(email);
}
}