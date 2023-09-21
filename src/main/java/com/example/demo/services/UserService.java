package com.example.demo.services;

import com.example.demo.Entities.User;
import com.example.demo.repositories.UserApiRepository;
import com.example.demo.repositories.UserInfoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    private UserInfoRepository repository;

    public UserService(){

    }
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
