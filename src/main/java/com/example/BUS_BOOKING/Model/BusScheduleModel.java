package com.example.BUS_BOOKING.Model;

import com.example.BUS_BOOKING.enums.BusScheduleStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Table(name="bus_schedule")
public class BusScheduleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;


    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn( name="bus_id" , nullable = false)
    private BusModel bus;


    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "departure_time", nullable = false)
    private LocalTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalTime arrivalTime;

    @Column(name = "travel_date", nullable = false)
    private LocalDate travelDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BusScheduleStatus status;
}
