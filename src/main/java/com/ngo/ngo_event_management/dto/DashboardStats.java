package com.ngo.ngo_event_management.dto;

import lombok.Data;

@Data
public class DashboardStats {

    private Long users;

    private Long volunteers;

    private Long events;

    private Long donations;

    private Long campaigns;
}