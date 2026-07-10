package com.ngo.ngo_event_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name="campaigns")
@Data
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer campaignId;

    @NotBlank(message = "Campaign title is required")
    @Size(min = 3, max = 200, message = "Campaign title must be between 3 and 200 characters")
    private String title;

    @NotBlank(message = "Campaign description is required")
    @Size(min = 10, max = 2000, message = "Campaign description must be between 10 and 2000 characters")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Goal amount is required")
    @DecimalMin(value = "1.0", message = "Goal amount must be at least 1.0")
    @DecimalMax(value = "10000000.0", message = "Goal amount must not exceed 10,000,000")
    private Double goalAmount;

    @DecimalMin(value = "0.0", message = "Raised amount cannot be negative")
    private Double raisedAmount;

    @URL(message = "Image URL must be valid")
    private String imageUrl;

    @Pattern(regexp = "^(ACTIVE|COMPLETED|CANCELLED)$", message = "Status must be ACTIVE, COMPLETED, or CANCELLED")
    private String status;
}