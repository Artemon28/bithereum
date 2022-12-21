package com.example.demo.repositories;

import com.example.demo.Entities.UserApi;

import java.util.List;

public interface UserApiRepository {
    UserApi findByUserId(long id);

    public void save(UserApi id);

    public void deleteById(long id);

    public List<UserApi> findAll();
}
