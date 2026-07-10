package com.ngo.ngo_event_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name="events")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;

    @NotBlank(message = "Event title is required")
    @Size(min = 3, max = 200, message = "Event title must be between 3 and 200 characters")
    private String title;

    @NotBlank(message = "Event description is required")
    @Size(min = 10, max = 2000, message = "Event description must be between 10 and 2000 characters")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Event date is required")
    @Future(message = "Event date must be in the future")
    private LocalDate eventDate;

    @NotNull(message = "Event time is required")
    private LocalTime eventTime;

    @NotBlank(message = "Event location is required")
    @Size(max = 255, message = "Location must not exceed 255 characters")
    private String location;

    @NotBlank(message = "Event category is required")
    @Size(max = 100, message = "Category must not exceed 100 characters")
    private String category;

    @NotNull(message = "Maximum participants is required")
    @Min(value = 1, message = "Maximum participants must be at least 1")
    @Max(value = 10000, message = "Maximum participants must not exceed 10000")
    private Integer maxParticipants;

    @URL(message = "Image URL must be valid")
    private String imageUrl;

    @Min(value = 0, message = "Current participants cannot be negative")
    private Integer currentParticipants;
}