package com.example.demo.repositories;

import com.example.demo.Entities.UserApi;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserApiRepository {
    UserApi findByUserId(long id);

    public void save(UserApi id);

    public void deleteById(long id);

    public List<UserApi> findAll();
}
