package com.example.BUS_BOOKING.Model.DTO.Response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class GetBusSchedulesResponse {

    private Long scheduleId;
    private String source;
    private String destination;
    private LocalDate travelDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String status;

    private BusResponse bus;

}
