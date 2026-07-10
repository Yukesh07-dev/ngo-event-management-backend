package com.ngo.ngo_event_management.controller;

import com.ngo.ngo_event_management.entity.Gallery;
import com.ngo.ngo_event_management.repository.GalleryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gallery")
@CrossOrigin(origins="*")
public class GalleryController {

    private final GalleryRepository repository;

    public GalleryController(
            GalleryRepository repository) {

        this.repository = repository;
    }

    @GetMapping
    public List<Gallery> getImages(){

        return repository.findAll();
    }

    @PostMapping
    public Gallery addImage(
            @RequestBody Gallery image){

        return repository.save(image);
    }
    @DeleteMapping("/{id}")
public void deleteGallery(@PathVariable Integer id){

    repository.deleteById(id);

}
}