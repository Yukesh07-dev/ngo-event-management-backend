package com.ngo.ngo_event_management.repository;

import com.ngo.ngo_event_management.entity.NgoDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NgoDetailsRepository extends JpaRepository<NgoDetails,Integer> {
}