package com.example.BUS_BOOKING.Controller;

import com.example.BUS_BOOKING.Exception.CustomException;
import com.example.BUS_BOOKING.Model.BusScheduleModel;
import com.example.BUS_BOOKING.Model.DTO.Request.BusScheduleRequest;
import com.example.BUS_BOOKING.Model.DTO.Response.ApiResponseModel;
import com.example.BUS_BOOKING.Model.DTO.Response.BusScheduleResponse;
import com.example.BUS_BOOKING.Model.DTO.Response.GetBusSchedulesResponse;
import com.example.BUS_BOOKING.Service.BusScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/busSchedule")
public class BusScheduleController {

    @Autowired
    BusScheduleService busScheduleService;

    private static final Logger logger= LoggerFactory.getLogger(BusScheduleService.class);

    @RequestMapping("/create-schedule")
    public ResponseEntity<ApiResponseModel> createBusSchedule(@RequestBody BusScheduleRequest schedule){
        try{
            BusScheduleResponse scheduleData= busScheduleService.createBusSchedule(schedule);
            logger.info("BusSchedule Created Successfully");
            ApiResponseModel response=new ApiResponseModel("SUCCESS","Bus Schedule Added",scheduleData);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(CustomException e){
            logger.warn(e.getMessage());
            ApiResponseModel response=new ApiResponseModel("ERROR",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch(Exception e){
            logger.error(e.getMessage());
            ApiResponseModel response=new ApiResponseModel("ERROR",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    @GetMapping("/get-all-schedules")
    public ResponseEntity<ApiResponseModel> getAllSchedules(){
        try {
            List<GetBusSchedulesResponse> schedules = busScheduleService.getAllSchedules();
            return ResponseEntity.ok(new ApiResponseModel("SUCCESS" , "Bus Schedule Fetched" , schedules));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseModel("ERROR","Failed To Fetch Schedule Details"));
        }
    }


//    @RequestMapping("/update-schedule/{scheduleId}")
//    public ResponseEntity<ApiResponseModel> updateBusSchedule(@RequestBody BusScheduleRequest schedule , @PathVariable Long scheduleId){
//        try{
//
//
//        }
//    }


}
