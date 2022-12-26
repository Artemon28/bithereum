package com.example.demo.connection.impl;

import com.example.demo.connection.api.CryptoManager;
import com.example.demo.services.ApiService;

public class FtxManager implements CryptoManager {

    private final ApiService service;

    public FtxManager(ApiService service){
        this.service = service;
    }
    @Override
    public String getSpotInfo(int userId) throws Exception {
        return null;
    }

    @Override
    public String getMarginInfo(int userId) throws Exception {
        return null;
    }

    @Override
    public String getFuturesInfo(int userId) throws Exception {
        return null;
    }

    @Override
    public boolean isUserExist(String key, String secretKey) throws Exception {
        return false;
    }

    @Override
    public void disconnect(int id) {
    }
}

