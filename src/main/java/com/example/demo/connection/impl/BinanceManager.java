package com.example.demo.connection.impl;

import com.example.demo.Entities.UserApi;
import com.example.demo.binance.BinanceResponse;
import com.example.demo.binance.BinanceSnapshotAccountRequest;
import com.example.demo.connection.api.CryptoManager;
import com.example.demo.services.ApiService;

public class BinanceManager implements CryptoManager {
    private final ApiService service;
    public BinanceManager(ApiService service){
        this.service = service;
    }

    @Override
    public String getSpotInfo(int id) throws Exception {
        UserApi api = service.findApiByUserId(id);
        if (api != null) {
            BinanceSnapshotAccountRequest request = new BinanceSnapshotAccountRequest(
                    api.getBinanceSecretKey(), api.getBinanceKey());
            BinanceResponse response = request.send();
            return response.getJsonString();
        }
        return null;
    }

    @Override
    public boolean isUserExist(String key, String secretKey) throws Exception {
        BinanceSnapshotAccountRequest request = new BinanceSnapshotAccountRequest(secretKey, key);
        BinanceResponse response = request.send();
        if (response.getResponseCode() != 200){
            return false;
        }
        return true;
    }

    @Override
    public void disconnect(int id) {

    }
}
