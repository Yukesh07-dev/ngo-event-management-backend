package com.ngo.ngo_event_management.controller;

import com.ngo.ngo_event_management.entity.Campaign;
import com.ngo.ngo_event_management.repository.CampaignRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
@CrossOrigin(origins = "*")
public class CampaignController {

    private final CampaignRepository repository;

    public CampaignController(
            CampaignRepository repository) {

        this.repository = repository;
    }

    @GetMapping
    public List<Campaign> getAllCampaigns() {

        return repository.findAll();
    }

    @PostMapping
    public Campaign createCampaign(
            @RequestBody Campaign campaign) {

        if(campaign.getRaisedAmount() == null){

            campaign.setRaisedAmount(0.0);
        }

        return repository.save(campaign);
    }

    @PutMapping("/{id}/donate")
public Campaign updateDonation(
        @PathVariable Integer id,
        @RequestParam Double amount) {

    Campaign campaign =
            repository.findById(id)
                    .orElseThrow();

    campaign.setRaisedAmount(
            campaign.getRaisedAmount() + amount
    );

    return repository.save(campaign);
}
}