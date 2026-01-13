package com.example.BUS_BOOKING.Controller;

import com.example.BUS_BOOKING.Exception.CustomException;
import com.example.BUS_BOOKING.Model.DTO.Response.ApiResponseModel;
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

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create-user")
    public ResponseEntity<ApiResponseModel> createUser(@RequestBody UserModel user)  {
        try{
            userService.createUser(user);
            logger.info("User Created");
            ApiResponseModel response=new ApiResponseModel("SUCCESS","User Created");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(CustomException e){
            ApiResponseModel response=new ApiResponseModel("ERROR",e.getMessage());
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch(Exception e){
            ApiResponseModel response=new ApiResponseModel("ERROR",e.getMessage());
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

}
