package com.ngo.ngo_event_management.service;

import com.ngo.ngo_event_management.entity.ContactMessage;
import com.ngo.ngo_event_management.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactMessageService {

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    public List<ContactMessage> getAllContactMessages() {
        return contactMessageRepository.findAll();
    }

    public Optional<ContactMessage> getContactMessageById(Integer id) {
        return contactMessageRepository.findById(id);
    }

    public ContactMessage createContactMessage(ContactMessage contactMessage) {
        return contactMessageRepository.save(contactMessage);
    }

    public ContactMessage updateContactMessage(Integer id, ContactMessage contactMessageDetails) {
        Optional<ContactMessage> optionalContactMessage = contactMessageRepository.findById(id);
        if (optionalContactMessage.isPresent()) {
            ContactMessage contactMessage = optionalContactMessage.get();
            contactMessage.setName(contactMessageDetails.getName());
            contactMessage.setEmail(contactMessageDetails.getEmail());
            contactMessage.setSubject(contactMessageDetails.getSubject());
            contactMessage.setMessage(contactMessageDetails.getMessage());
            return contactMessageRepository.save(contactMessage);
        }
        return null;
    }

    public boolean deleteContactMessage(Integer id) {
        if (contactMessageRepository.existsById(id)) {
            contactMessageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
