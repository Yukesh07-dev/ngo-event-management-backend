package com.ngo.ngo_event_management.controller;

import com.ngo.ngo_event_management.entity.ContactMessage;
import com.ngo.ngo_event_management.repository.ContactMessageRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins="*")
public class ContactMessageController {

    private final ContactMessageRepository repository;

    public ContactMessageController(
            ContactMessageRepository repository) {

        this.repository = repository;
    }

    @GetMapping
    public List<ContactMessage> getMessages() {

        return repository.findAll();
    }

    @PostMapping
    public ContactMessage saveMessage(
            @RequestBody ContactMessage message) {

        return repository.save(message);
    }
}