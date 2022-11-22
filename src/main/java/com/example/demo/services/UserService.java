package com.example.demo.services;

import com.example.demo.Entities.User;
import com.example.demo.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserInfoRepository repository;

    public void addUser(User user) {
        repository.save(user);
    }

    public void deleteUser(long id) {
        repository.deleteById(id);
    }

    public Optional<User> findUser(long id) {
        return repository.findById(id);
    }

    public List<User> findAll() {
        return repository.findAll();
    }
}
