package com.example.BUS_BOOKING.Repository;

import com.example.BUS_BOOKING.Model.BusModel;
import com.example.BUS_BOOKING.Model.BusScheduleModel;
import com.example.BUS_BOOKING.Model.DTO.Response.BusScheduleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface BusScheduleRepository extends JpaRepository<BusScheduleModel,Long> {
    boolean existsByBus_BusIdAndTravelDateAndDepartureTime(Long busId , LocalDate travelDate, LocalTime departureTime);
    Optional<BusScheduleModel> findTopByBus_BusIdAndTravelDateOrderByDepartureTimeDesc( Long busId, LocalDate travelDate);
}
