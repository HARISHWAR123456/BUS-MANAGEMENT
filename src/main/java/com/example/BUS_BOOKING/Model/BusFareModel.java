package com.example.BUS_BOOKING.Model;

import com.example.BUS_BOOKING.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fare",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"bus_id", "seat_type"})
        }
)
public class BusFareModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fare_id")
    private Long fareId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", nullable = false)
    private BusModel bus;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type", nullable = false)
    private SeatType seatType;

    @Column(name = "fare_amount", nullable = false)
    private BigDecimal fareAmount;
}
