package com.ngo.ngo_event_management.service;

import com.ngo.ngo_event_management.entity.Campaign;
import com.ngo.ngo_event_management.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Optional<Campaign> getCampaignById(Integer id) {
        return campaignRepository.findById(id);
    }

    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public Campaign updateCampaign(Integer id, Campaign campaignDetails) {
        Optional<Campaign> optionalCampaign = campaignRepository.findById(id);
        if (optionalCampaign.isPresent()) {
            Campaign campaign = optionalCampaign.get();
            campaign.setTitle(campaignDetails.getTitle());
            campaign.setDescription(campaignDetails.getDescription());
            campaign.setGoalAmount(campaignDetails.getGoalAmount());
            campaign.setRaisedAmount(campaignDetails.getRaisedAmount());
            campaign.setImageUrl(campaignDetails.getImageUrl());
            return campaignRepository.save(campaign);
        }
        return null;
    }

    public boolean deleteCampaign(Integer id) {
        if (campaignRepository.existsById(id)) {
            campaignRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Campaign updateDonationAmount(Integer id, Double amount) {
        Optional<Campaign> optionalCampaign = campaignRepository.findById(id);
        if (optionalCampaign.isPresent()) {
            Campaign campaign = optionalCampaign.get();
            campaign.setRaisedAmount(campaign.getRaisedAmount() + amount);
            return campaignRepository.save(campaign);
        }
        return null;
    }
}
