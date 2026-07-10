package com.ngo.ngo_event_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "event_registrations")
public class EventRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer registrationId;

    private Integer userId;

    private Integer eventId;
    private String userEmail;

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }
    
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
    public String getUserEmail() {
    return userEmail;
}
public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
}
}