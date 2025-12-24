package com.example.BUS_BOOKING.Service;

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

    public String createUser(UserModel user) {
        try{
            logger.info("Entered CreateUser Service");
           boolean  userData =  userRepository.existsByEmail(user.getEmail());
           if(!userData){
               userRepository.save(user);
               return "User Created SuccessFully";
           }else{
               throw new Exception("Email already Exists");
           }

        }catch (Exception e){
           throw new RuntimeException(e.getMessage());
        }
    }


}
