package com.example.BUS_BOOKING.Service;

import com.example.BUS_BOOKING.Exception.BusAlreadyExistsException;
import com.example.BUS_BOOKING.Exception.ResourceNotFoundException;
import com.example.BUS_BOOKING.Model.BusModel;
import com.example.BUS_BOOKING.Repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {
    private static Logger logger = LoggerFactory.getLogger(BusService.class);

    private final BusRepository busRepository;

    public void createBus(BusModel bus) {

        boolean busExist = busRepository.existsByBusNumber(bus.getBusNumber());
        if (busExist) {
            logger.error("Bus Already Exists Give New BusNumber");
            throw new BusAlreadyExistsException("Bus Already Exists");
        }
        busRepository.save(bus);

    }

    public List<BusModel> getAllBus() {

        List<BusModel> bus=busRepository.findAll();

        if(bus.isEmpty()){
            logger.info("No buses Found");
            throw new ResourceNotFoundException("No buses available");

        }
        return bus;
    }
}
