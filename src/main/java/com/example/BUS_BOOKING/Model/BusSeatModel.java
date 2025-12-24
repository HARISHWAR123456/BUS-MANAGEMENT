package com.example.BUS_BOOKING.Model;

import com.example.BUS_BOOKING.enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bus_seat")
public class BusSeatModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="seat_id")
    private Long seatId;

    @Column(name="seat_number")
    private String SeatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name="seat_type")
    private SeatType seatType;

    @Column(name="is_women_seat")
    private boolean isWomenSeat;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="bus_id",nullable = false,referencedColumnName = "bus_id")
    private BusModel bus;

}
