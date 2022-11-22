package com.example.demo.repositories;


import com.example.demo.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserInfoRepository extends JpaRepository<User, Long> {
    // some our methods
}
