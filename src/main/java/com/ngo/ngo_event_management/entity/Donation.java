package com.ngo.ngo_event_management.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name="donations")
@Data
public class Donation {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer donationId;

@NotBlank(message = "Donor name is required")
@Size(min = 2, max = 100, message = "Donor name must be between 2 and 100 characters")
private String donorName;

@NotBlank(message = "Email is required")
@Email(message = "Email should be valid")
@Size(max = 100, message = "Email must not exceed 100 characters")
private String email;

@NotNull(message = "Donation amount is required")
@DecimalMin(value = "1.0", message = "Donation amount must be at least 1.0")
@DecimalMax(value = "1000000.0", message = "Donation amount must not exceed 1,000,000")
private Double amount;

@NotBlank(message = "Campaign is required")
@Size(max = 200, message = "Campaign must not exceed 200 characters")
private String campaign;

@Size(max = 500, message = "Message must not exceed 500 characters")
private String message;

@Pattern(regexp = "^(SUCCESS|PENDING|FAILED)$", message = "Payment status must be SUCCESS, PENDING, or FAILED")
private String paymentStatus;

@Size(max = 100, message = "Transaction ID must not exceed 100 characters")
private String transactionId;

private LocalDateTime donationDate;
}