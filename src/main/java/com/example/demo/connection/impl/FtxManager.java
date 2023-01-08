package com.example.demo.connection.impl;

import com.example.demo.binance.BinanceResponse;
import com.example.demo.binance.BinanceSnapshotMarketRequest;
import com.example.demo.connection.api.CryptoManager;
import com.example.demo.services.ApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FtxManager implements CryptoManager {

    private final ApiService service;

    public FtxManager(ApiService service){
        this.service = service;
    }
    @Override
    public Map<String, String> getSpotInfo(int userId) throws Exception {
        return null;
    }

    @Override
    public String getMarginInfo(int userId) throws Exception {
        return null;
    }

//    @Override
//    public String getFuturesInfo(int userId) throws Exception {
//        return null;
//    }

    @Override
    public boolean isUserExist(String key, String secretKey) throws Exception {
        return false;
    }

    @Override
    public ArrayList<String> getExchangeInfo() throws Exception {
        return null;
    }

    @Override
    public void makeOrder(String key, String secretKey, String symbol, String side, String type, String quoteOrderQty) throws Exception {
    }

    @Override
    public List<String> allOrders(String key, String secretKey, String symbol) throws Exception {
        return null;
    }


    @Override
    public void disconnect(int id) {
    }
}

