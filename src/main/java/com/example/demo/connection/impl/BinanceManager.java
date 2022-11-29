package com.example.demo.connection.impl;

import com.example.demo.Entities.UserApi;
import com.example.demo.binance.BinanceResponse;
import com.example.demo.binance.BinanceSnapshotAccountRequest;
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
    public void getSpotInfo(int id) throws Exception {
        Optional<UserApi> api = service.findApiByUserId(id);
        if (api.isPresent()) {
            BinanceSnapshotAccountRequest request = new BinanceSnapshotAccountRequest(
                    api.get().getBinanceSecretKey(), api.get().getBinanceKey());
            BinanceResponse response = request.send();
            System.out.println(response.getJsonString());
        }
    }

    @Override
    public void disconnect(int id) {

    }
}
