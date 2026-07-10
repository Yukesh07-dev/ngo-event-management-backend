package com.ngo.ngo_event_management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "contact_messages")
@Data
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;

    private String name;

    private String email;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String message;
}