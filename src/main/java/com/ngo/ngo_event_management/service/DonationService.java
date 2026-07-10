package com.ngo.ngo_event_management.service;

import com.ngo.ngo_event_management.entity.Donation;
import com.ngo.ngo_event_management.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Optional<Donation> getDonationById(Integer id) {
        return donationRepository.findById(id);
    }

    public List<Donation> getDonationsByEmail(String email) {
        return donationRepository.findByEmail(email);
    }

    public Donation createDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    public Donation updateDonation(Integer id, Donation donationDetails) {
        Optional<Donation> optionalDonation = donationRepository.findById(id);
        if (optionalDonation.isPresent()) {
            Donation donation = optionalDonation.get();
            donation.setDonorName(donationDetails.getDonorName());
            donation.setEmail(donationDetails.getEmail());
            donation.setAmount(donationDetails.getAmount());
            donation.setCampaign(donationDetails.getCampaign());
            donation.setTransactionId(donationDetails.getTransactionId());
            donation.setPaymentStatus(donationDetails.getPaymentStatus());
            return donationRepository.save(donation);
        }
        return null;
    }

    public boolean deleteDonation(Integer id) {
        if (donationRepository.existsById(id)) {
            donationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Donation> getDonationsByCampaignId(Integer campaignId) {
        return donationRepository.findAll().stream()
                .filter(d -> d.getCampaign().equals(String.valueOf(campaignId)))
                .toList();
    }

    public Double getTotalDonationsByCampaign(Integer campaignId) {
        return donationRepository.findAll().stream()
                .filter(d -> d.getCampaign().equals(String.valueOf(campaignId)) && "SUCCESS".equals(d.getPaymentStatus()))
                .mapToDouble(Donation::getAmount)
                .sum();
    }
}
