package com.example.demo.services;

import com.example.demo.Entities.User;
import com.example.demo.repositories.UserApiRepository;
import com.example.demo.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class UserService {
    private UserInfoRepository repository;

    public UserService(UserInfoRepository repository){
        this.repository = repository;
    }


    public void addUser(User user) {
        repository.save(user);
    }

    public void deleteUser(long id) {
        repository.deleteById(id);
    }

    public User findUser(long id) {
        return repository.findByUserId(id);
    }

    public List<User> findAll() {
        return repository.findAll();
    }
}
