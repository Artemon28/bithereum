package com.example.demo.connection.impl;

import com.example.demo.Entities.UserApi;
import com.example.demo.connection.api.CryptoManager;
import com.example.demo.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BinanceManager implements CryptoManager {

    @Autowired
    private ApiService service;


    @Override
    public void connect(int id) {
        Optional<UserApi> api = service.findApiByUserId(id);
    }

    @Override
    public void disconnect(int id) {

    }
}
