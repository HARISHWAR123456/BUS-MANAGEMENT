package com.example.BUS_BOOKING.Service;

import com.example.BUS_BOOKING.Exception.CustomException;
import com.example.BUS_BOOKING.Mapper.BusScheduleMapper;
import com.example.BUS_BOOKING.Model.BusModel;
import com.example.BUS_BOOKING.Model.BusScheduleModel;
import com.example.BUS_BOOKING.Model.DTO.Request.BusScheduleRequest;
import com.example.BUS_BOOKING.Model.DTO.Response.BusScheduleResponse;
import com.example.BUS_BOOKING.Model.DTO.Response.GetBusSchedulesResponse;
import com.example.BUS_BOOKING.Repository.BusRepository;
import com.example.BUS_BOOKING.Repository.BusScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BusScheduleService{
    private final BusScheduleRepository busScheduleRepository;
    private final BusScheduleMapper busScheduleMapper;
    private final BusRepository busRepository;

    public BusScheduleService(BusScheduleRepository busScheduleRepository ,BusScheduleMapper busScheduleMapper ,BusRepository busRepository){
        this.busScheduleRepository=busScheduleRepository;
        this .busScheduleMapper=busScheduleMapper;
        this.busRepository=busRepository;
    }
//
//    public BusScheduleResponse createBusSchedule(BusScheduleRequest schedule) {
//
//        BusModel bus= busRepository.findById(schedule.getBusId()).orElseThrow(()->new CustomException("bus Not Available"));
//
//        boolean alreadyExists=busScheduleRepository.existsByBus_BusIdAndTravelDateAndDepartureTime(schedule.getBusId(),schedule.getTravelDate(),schedule.getDepartureTime());
//
//        if(alreadyExists){
//            throw new CustomException("Bus Schedule already available with the same date and departure time ");
//
//        }
//
//        BusScheduleModel scheduleData =busScheduleMapper.toCreateBusScheduleEntity(schedule,bus);
//
//
//        Optional<BusScheduleModel> availableBusData=busScheduleRepository.findTopByBus_BusIdAndTravelDateOrderByArrivalTimeDesc(scheduleData.getBus().getBusId(),schedule.getTravelDate());
//        if(availableBusData.isPresent()){
//
//            //arrival date and time of last trip of the bus stored in this variable in this format  lastArrivalDateTime = 2026-01-05T12:00
//            LocalDateTime lastArrivalDateTime=LocalDateTime.of(availableBusData.get().getTravelDate(),availableBusData.get().getArrivalTime());
//
//
//            // update the next eligible departure time for the next trip
//            LocalDateTime allowedNextDepartureTime=lastArrivalDateTime.plusHours(8);
//
//            // creates a new departure object with the new departure time and date to check with the allowednextDepartureTime
//            LocalDateTime nextDepartureTimeDate=LocalDateTime.of(schedule.getTravelDate(),schedule.getDepartureTime());
//
//            if(nextDepartureTimeDate.isBefore(allowedNextDepartureTime)){
//                throw new CustomException("Bus already scheduled. Next trip allowed after " + allowedNextDepartureTime);
//            }
//
//        }
//        BusScheduleModel responseData = busScheduleRepository.save(scheduleData);
//
//        return busScheduleMapper.toReturnCreateBusScheduleEntity(responseData);
//
//    }

    public BusScheduleResponse createBusSchedule(BusScheduleRequest schedule) {

        BusModel bus = busRepository.findById(schedule.getBusId())
                .orElseThrow(() -> new CustomException("Bus Not Available"));

        boolean alreadyExists = busScheduleRepository.existsByBus_BusIdAndTravelDateAndDepartureTime(
                        schedule.getBusId(),
                        schedule.getTravelDate(),
                        schedule.getDepartureTime()
                );

        if (alreadyExists) {
            throw new CustomException(
                    "Bus schedule already exists for this date and departure time"
            );
        }

        Optional<BusScheduleModel> lastScheduleOpt =
                busScheduleRepository
                        .findTopByBus_BusIdAndTravelDateOrderByDepartureTimeDesc(
                                schedule.getBusId(),
                                schedule.getTravelDate()
                        );

        if (lastScheduleOpt.isPresent()) {

            BusScheduleModel lastSchedule = lastScheduleOpt.get();

            LocalDateTime lastDepartureDateTime =
                    LocalDateTime.of(
                            lastSchedule.getTravelDate(),
                            lastSchedule.getDepartureTime()
                    );

            LocalDateTime newDepartureDateTime =
                    LocalDateTime.of(
                            schedule.getTravelDate(),
                            schedule.getDepartureTime()
                    );

            if (newDepartureDateTime.isBefore(lastDepartureDateTime)) {
                throw new CustomException("New departure time cannot be earlier than the last scheduled departure: " + lastDepartureDateTime );
            }

            LocalDateTime lastArrivalDateTime =
                    LocalDateTime.of(
                            lastSchedule.getTravelDate(),
                            lastSchedule.getArrivalTime()
                    );

            if (lastSchedule.getArrivalTime()
                    .isBefore(lastSchedule.getDepartureTime())) {
                lastArrivalDateTime = lastArrivalDateTime.plusDays(1);
            }

            LocalDateTime allowedNextDeparture =
                    lastArrivalDateTime.plusHours(8);



            if (newDepartureDateTime.isBefore(allowedNextDeparture)) {
                throw new CustomException(
                        "Next trip allowed only after " + allowedNextDeparture
                );
            }
        }

        BusScheduleModel scheduleData =
                busScheduleMapper.toCreateBusScheduleEntity(schedule, bus);

        BusScheduleModel responseData =
                busScheduleRepository.save(scheduleData);

        return busScheduleMapper.toReturnCreateBusScheduleEntity(responseData);
    }


    public List<GetBusSchedulesResponse> getAllSchedules() {

        return busScheduleRepository.findAll();

    }
}




