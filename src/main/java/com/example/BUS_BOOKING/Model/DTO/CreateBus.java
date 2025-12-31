package com.example.BUS_BOOKING.Model.DTO;

import com.example.BUS_BOOKING.enums.AcType;
import com.example.BUS_BOOKING.enums.BusType;
import lombok.Data;

import java.util.List;

@Data
public class CreateBus {

    private String busNumber;
    private String busName;
    private AcType acType;
    private BusType busType;
    private Long totalSeats;
    private List<FareRequest> fares;
}
