package com.ngo.ngo_event_management.repository;

import com.ngo.ngo_event_management.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository
        extends JpaRepository<Gallery,Integer> {
}