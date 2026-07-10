package com.ngo.ngo_event_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name="gallery")
@Data
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    @NotBlank(message = "Image title is required")
    @Size(min = 3, max = 200, message = "Image title must be between 3 and 200 characters")
    private String title;

    @NotBlank(message = "Image URL is required")
    @URL(message = "Image URL must be valid")
    private String imageUrl;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    private Integer eventId;
}