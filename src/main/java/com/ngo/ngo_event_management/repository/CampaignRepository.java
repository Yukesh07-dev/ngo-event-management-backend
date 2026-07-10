package com.ngo.ngo_event_management.repository;

import com.ngo.ngo_event_management.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository
        extends JpaRepository<Campaign, Integer> {
}