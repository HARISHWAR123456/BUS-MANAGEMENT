package com.example.BUS_BOOKING.Mapper;

import com.example.BUS_BOOKING.Model.BusModel;
import com.example.BUS_BOOKING.Model.BusScheduleModel;
import com.example.BUS_BOOKING.Model.DTO.Request.BusScheduleRequest;
import com.example.BUS_BOOKING.Model.DTO.Response.BusScheduleResponse;
import org.springframework.stereotype.Component;

@Component
public class BusScheduleMapper {


    public  BusScheduleModel toCreateBusScheduleEntity(BusScheduleRequest request , BusModel bus){
        BusScheduleModel model = new BusScheduleModel();
        model.setTravelDate(request.getTravelDate());
        model.setDepartureTime(request.getDepartureTime());
        model.setArrivalTime(request.getArrivalTime());
        model.setDestination(request.getDestination());
        model.setSource(request.getSource());
        model.setStatus(request.getStatus());
        model.setBus(bus);
        return model;
    }

    public BusScheduleResponse toReturnCreateBusScheduleEntity(BusScheduleModel request){
        BusScheduleResponse busScheduleResponse = new BusScheduleResponse();
        busScheduleResponse.setScheduleId(request.getScheduleId());
        busScheduleResponse.setStatus(request.getStatus());
        busScheduleResponse.setBusId(request.getBus().getBusId());
        busScheduleResponse.setDestination(request.getDestination());
        busScheduleResponse.setSource(request.getSource());
        busScheduleResponse.setArrivalTime(request.getArrivalTime());
        busScheduleResponse.setDepartureTime(request.getDepartureTime());
        busScheduleResponse.setTravelDate(request.getTravelDate());
        return busScheduleResponse;
    }
}
