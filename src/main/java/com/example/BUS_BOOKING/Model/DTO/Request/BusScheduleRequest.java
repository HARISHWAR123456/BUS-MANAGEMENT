package com.example.BUS_BOOKING.Model.DTO.Request;

import com.example.BUS_BOOKING.enums.BusScheduleStatus;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data

public class BusScheduleRequest {

    private Long busId;

    private String source;

    private String destination;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private LocalDate travelDate;

    private BusScheduleStatus status;
}