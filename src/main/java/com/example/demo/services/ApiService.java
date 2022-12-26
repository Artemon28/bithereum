package com.example.demo.services;

import com.example.demo.Entities.User;
import com.example.demo.Entities.UserApi;
import com.example.demo.repositories.UserApiRepository;
import com.example.demo.repositories.UserInfoRepository;

import java.util.List;
import java.util.Optional;

public class ApiService {

    public ApiService(UserApiRepository repository){
        this.repository = repository;
    }
    private UserApiRepository repository;

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
