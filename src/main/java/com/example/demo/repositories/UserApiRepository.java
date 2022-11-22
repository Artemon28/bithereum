package com.example.demo.repositories;

import com.example.demo.Entities.UserApi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserApiRepository extends JpaRepository<UserApi, Long> {
    Optional<UserApi> findByUserId(long id);
}
