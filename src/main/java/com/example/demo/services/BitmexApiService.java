package com.example.demo.services;

import com.example.demo.Entities.UserApi;
import com.example.demo.repositories.UserApiBitMexRepositoryImpl;
import com.example.demo.repositories.UserApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BitmexApiService {

    @Autowired
    UserApiBitMexRepositoryImpl repository;

    public void addApi(UserApi api) {
        repository.save(api);
    }

    public void deleteApi(long id) {
        repository.deleteById(id);
    }

    public UserApi findApiByUserId(long id) {
        return repository.findByUserId(id);
    }

    public List<UserApi> findAll() {
        return repository.findAll();
    }

}
