package com.ngo.ngo_event_management.service;

import com.ngo.ngo_event_management.entity.Gallery;
import com.ngo.ngo_event_management.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;

    public List<Gallery> getAllGalleryItems() {
        return galleryRepository.findAll();
    }

    public Optional<Gallery> getGalleryItemById(Integer id) {
        return galleryRepository.findById(id);
    }

    public Gallery createGalleryItem(Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    public Gallery updateGalleryItem(Integer id, Gallery galleryDetails) {
        Optional<Gallery> optionalGallery = galleryRepository.findById(id);
        if (optionalGallery.isPresent()) {
            Gallery gallery = optionalGallery.get();
            gallery.setTitle(galleryDetails.getTitle());
            gallery.setDescription(galleryDetails.getDescription());
            gallery.setImageUrl(galleryDetails.getImageUrl());
            gallery.setEventId(galleryDetails.getEventId());
            return galleryRepository.save(gallery);
        }
        return null;
    }

    public boolean deleteGalleryItem(Integer id) {
        if (galleryRepository.existsById(id)) {
            galleryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Gallery> getGalleryByEventId(Integer eventId) {
        return galleryRepository.findAll().stream()
                .filter(g -> g.getEventId() != null && g.getEventId().equals(eventId))
                .toList();
    }
}
