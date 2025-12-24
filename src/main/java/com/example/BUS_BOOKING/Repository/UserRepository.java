package com.example.BUS_BOOKING.Repository;

import com.example.BUS_BOOKING.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository< UserModel, Long> {

   boolean existsByEmail(String email);
}
