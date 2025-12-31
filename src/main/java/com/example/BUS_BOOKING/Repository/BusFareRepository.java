package com.example.BUS_BOOKING.Repository;

import com.example.BUS_BOOKING.Model.BusFareModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusFareRepository  extends JpaRepository<BusFareModel,Long> {

}
