package com.example.BUS_BOOKING.Model.DTO;

import com.example.BUS_BOOKING.enums.SeatType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FareRequest {

    private SeatType seatType;
    private BigDecimal fareAmount;
}
