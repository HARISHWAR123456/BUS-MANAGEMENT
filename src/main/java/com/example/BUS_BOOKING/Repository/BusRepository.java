package com.example.BUS_BOOKING.Repository;

import com.example.BUS_BOOKING.Model.BusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<BusModel, Long> {

    boolean existsByBusNumber(String busNumber);
}
