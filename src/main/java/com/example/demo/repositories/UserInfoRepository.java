package com.example.demo.repositories;

import com.example.demo.Entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserInfoRepository {
    User findByUserId(long id);

    public void save(User id);

    public void deleteById(long id);

    public List<User> findAll();
}
