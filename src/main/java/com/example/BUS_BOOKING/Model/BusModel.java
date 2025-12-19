package com.example.BUS_BOOKING.Model;

import com.example.BUS_BOOKING.enums.AcType;
import com.example.BUS_BOOKING.enums.BusType;
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
@Table(name="bus")
public class BusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bus_id")
    private Long busId;

    @Column(name="bus_number", nullable = false, unique=true)
    private  String busNumber;

    @Column(name="bus_name")
    private String busName;

    @Column (name="ac_type")
    private AcType acType;

    @Column(name="bus_type")
    private BusType busType;

    @Column(name="total_seats")
    private Long totalSeats;


}
