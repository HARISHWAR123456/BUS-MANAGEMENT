package com.example.BUS_BOOKING.Model;

import com.example.BUS_BOOKING.enums.Gender;
import com.example.BUS_BOOKING.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="gender")
    private Gender gender;

    @Column(name="role")
    private Role role;
}
