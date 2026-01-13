package com.example.BUS_BOOKING.Model.DTO.Response;

import com.example.BUS_BOOKING.enums.BusScheduleStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BusScheduleResponse {
    private Long scheduleId;
    private Long busId;

    private String source;
    private String destination;

    private LocalTime departureTime;
    private LocalTime arrivalTime;

    private LocalDate travelDate;
    private BusScheduleStatus status;
}
