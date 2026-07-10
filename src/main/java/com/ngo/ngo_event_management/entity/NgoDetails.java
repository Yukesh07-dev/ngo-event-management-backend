package com.ngo.ngo_event_management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="ngo_details")
@Data
public class NgoDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ngoId;

    private String ngoName;

    private String mission;

    private String vision;

    private String email;

    private String phone;

    private String address;

    private String website;
}