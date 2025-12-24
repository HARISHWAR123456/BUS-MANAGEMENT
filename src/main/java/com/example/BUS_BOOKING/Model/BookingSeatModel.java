package com.example.BUS_BOOKING.Model;

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
@Table(
        name = "booking_seat",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"schedule_id", "seat_id"}),
                @UniqueConstraint(columnNames = {"passenger_id"})
        }
)
public class BookingSeatModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingSeatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private BookingModel booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private BookingPassengerModel passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private BusSeatModel seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private BusScheduleModel schedule;
}
