package com.example.BUS_BOOKING.Service;

import com.example.BUS_BOOKING.Exception.CustomException;
import com.example.BUS_BOOKING.Model.BusFareModel;
import com.example.BUS_BOOKING.Model.BusModel;
import com.example.BUS_BOOKING.Model.DTO.Request.CreateBus;
import com.example.BUS_BOOKING.Model.DTO.Request.FareRequest;
import com.example.BUS_BOOKING.Repository.BusFareRepository;
import com.example.BUS_BOOKING.Repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {
    private static Logger logger = LoggerFactory.getLogger(BusService.class);

    private final BusRepository busRepository;
    private final BusFareRepository busFareRepository;

    public void createBus(CreateBus bus) {

        boolean busExist = busRepository.existsByBusNumber(bus.getBusNumber());
        if (busExist) {
            logger.error("Bus Already Exists Give New BusNumber");
            throw new CustomException("Bus Already Exists");
        }
        BusModel busModel = new BusModel();
        busModel.setBusName(bus.getBusName());
        busModel.setBusNumber(bus.getBusNumber());
        busModel.setBusType(bus.getBusType());
        busModel.setTotalSeats(bus.getTotalSeats());
        busModel.setAcType(bus.getAcType());

        BusModel savedBus = busRepository.save(busModel);

        for (FareRequest fareReq : bus.getFares()) {

            BusFareModel fare = new BusFareModel();
            fare.setBus(savedBus);
            fare.setSeatType(fareReq.getSeatType());
            fare.setFareAmount(fareReq.getFareAmount());
            busFareRepository.save(fare);
        }
    }

    public List<BusModel> getAllBus() {
        List<BusModel> bus = busRepository.findAll();
        if (bus.isEmpty()) {
            logger.error("No buses Found");
            throw new CustomException("No buses available");
        }
        return bus;
    }

    public BusModel updateBus(BusModel bus, Long busId) {

        BusModel existingBus = busRepository.findById(busId).orElseThrow(() -> new CustomException("Bus Not Found"));

        if (bus.getBusNumber() != null) {
            boolean  existingBusNumber =busRepository.existsByBusNumber(bus.getBusNumber());
            if(existingBusNumber){
                throw new CustomException("Bus Already Exists");
            }
            existingBus.setBusNumber(bus.getBusNumber());
        }
        if (bus.getBusName() != null) {
            existingBus.setBusName(bus.getBusName());
        }
        if (bus.getAcType() != null) {
            existingBus.setAcType(bus.getAcType());
        }
        if(bus.getBusType()!=null){
            existingBus.setBusType(bus.getBusType());
        }
        if(bus.getTotalSeats()!=null){
            existingBus.setTotalSeats(bus.getTotalSeats());
        }

        return busRepository.save(existingBus);
    }
}
