package com.example.BUS_BOOKING.Controller;

import com.example.BUS_BOOKING.Exception.CustomException;
import com.example.BUS_BOOKING.Model.BusModel;
import com.example.BUS_BOOKING.Model.DTO.CreateBus;
import com.example.BUS_BOOKING.Model.Response;
import com.example.BUS_BOOKING.Service.BusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/bus")
public class BusController {
    Logger logger = LoggerFactory.getLogger(BusController.class);

@Autowired
    BusService busService;


  @PostMapping("/create-bus")
    public ResponseEntity<Response> createBus(@RequestBody CreateBus bus){
      try{
          busService.createBus(bus);

          logger.info("bus created Successfully");
          Response response=new Response("SUCCESS","Bus Created Successfully");
          return ResponseEntity.status(HttpStatus.CREATED).body(response);

      }catch(CustomException e){
          logger.error(e.getMessage());
          Response response=new Response("ERROR","BusNumber Already Exists");
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
      }catch(Exception e ){
          logger.error(e.getMessage());
          Response response=new Response("ERROR",e.getMessage());
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
      }
    }

    @GetMapping("/getAll-bus")
    public  ResponseEntity<Response> getAllBus(){
      try{
          List<BusModel> allBus=busService.getAllBus();
          logger.info("Retrived all bus");
          Response response=new Response("SUCCESS","Data Fetched Successfully" , allBus);
          return ResponseEntity.status(HttpStatus.OK).body(response);

      }catch(CustomException e){
          Response response=new Response("ERROR",(e.getMessage()));
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }
    }
   @PutMapping("/updateBus/{busId}")
    public ResponseEntity<Response> updateBus(@RequestBody BusModel bus,@PathVariable Long busId){

      try{
         BusModel updatedBus= busService.updateBus(bus,busId);
         logger.info("Bus Updated");
         Response response= new Response("SUCCESS","Bus Updated",updatedBus);
         return ResponseEntity.status(HttpStatus.OK).body(response);
      }catch(CustomException e){
          logger.info(e.getMessage());
          Response response=new Response("ERROR",e.getMessage());
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
       }

    }

}
