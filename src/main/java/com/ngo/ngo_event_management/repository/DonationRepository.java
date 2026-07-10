package com.ngo.ngo_event_management.repository;

import com.ngo.ngo_event_management.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository
        extends JpaRepository<Donation,Integer> {

    List<Donation> findByEmail(String email);

}