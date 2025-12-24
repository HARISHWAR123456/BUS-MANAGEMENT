package com.example.BUS_BOOKING.Controller;

import com.example.BUS_BOOKING.Model.UserModel;
import com.example.BUS_BOOKING.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(userController.class);

    @PostMapping("/create-user")
    public ResponseEntity<Map<String,Object>> createUser(@RequestBody UserModel user)  {
        Map<String,Object> response= new HashMap<>();
        try{
            logger.info("Entered Create User Contoller");
            String serviceResposne = userService.createUser(user);
            response.put("status" , "Success");
            response.put("message" ,  serviceResposne);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(Exception e){
            response.put("status" , "Error");
            response.put("message" ,  e.getMessage());
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        }

    }

}
