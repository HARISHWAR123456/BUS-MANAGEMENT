package com.example.BUS_BOOKING.Service;

import com.example.BUS_BOOKING.Exception.CustomException;
import com.example.BUS_BOOKING.Model.UserModel;
import com.example.BUS_BOOKING.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private final UserRepository userRepository;

    public void createUser(UserModel user) {
        try{
           boolean  userData =  userRepository.existsByEmail(user.getEmail());
           if(!userData){
               userRepository.save(user);
           }else{
               logger.error("Email already exists");
               throw new CustomException("Email already Exists");
           }
        }catch (Exception e){
           throw new RuntimeException(e.getMessage());
        }
    }


}
