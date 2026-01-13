package com.example.BUS_BOOKING.Model.DTO.Response;

import lombok.Data;

@Data
public class BusResponse {
    private Long busId;
    private String busNumber;
    private String busName;
    private String acType;
    private String busType;
    private int totalSeats;
}
