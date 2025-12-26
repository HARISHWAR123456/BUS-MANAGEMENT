package com.example.BUS_BOOKING.Controller;

import com.example.BUS_BOOKING.Model.BusModel;
import com.example.BUS_BOOKING.Repository.BusRepository;
import com.example.BUS_BOOKING.Service.BusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bus")
public class BusController {
    Logger logger = LoggerFactory.getLogger(BusController.class);

@Autowired
    BusService busService;
  @PostMapping("/create-bus")
    public ResponseEntity<Map<String,Object>> createBus(@RequestBody BusModel bus){
          busService.createBus(bus);

          logger.info("bus created ");
          Map<String,Object> response = new HashMap<>();
           response.put("status" ,"SUCCESS");
           response.put("message" ,"Bus Created SuccessFully");

          return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getAll-bus")
    public  ResponseEntity<Map<String,Object>> getAllBus(){
      List<BusModel> allBus=busService.getAllBus();

      logger.info("Retrived all bus");
      Map<String,Object> response=new HashMap<>();
      response.put("status","SUCCESS");
      response.put("message","Bus Fetched Successfully");
      response.put("data",allBus);
       return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
