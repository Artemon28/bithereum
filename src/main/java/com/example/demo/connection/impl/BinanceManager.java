package com.example.demo.connection.impl;

import com.example.demo.Entities.UserApi;
import com.example.demo.binance.*;
import com.example.demo.connection.api.CryptoManager;
import com.example.demo.services.ApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BinanceManager implements CryptoManager {
    private final ApiService service;
    public BinanceManager(ApiService service){
        this.service = service;
    }

    @Override
    public Map<String, String> getSpotInfo(int id) throws Exception {
        UserApi api = service.findApiByUserId(id);
        if (api != null) {
            BinanceAccInfoRequest request = new BinanceAccInfoRequest(
                    api.getSecretKey(), api.getKey());
            BinanceResponse response = request.send();
            return response.getAllBalances();
        }
        return null;
    }

    @Override
    public String getMarginInfo(int id) throws Exception {
        UserApi api = service.findApiByUserId(id);
        if (api != null) {
            BinanceSnapshotAccountRequest request = new BinanceSnapshotAccountRequest(
                    api.getSecretKey(), api.getKey());
            BinanceResponse response = request.sendMargin();
            return response.getJsonString();
        }
        return null;
    }

//    @Override
//    public String getFuturesInfo(int id) throws Exception {
//        UserApi api = service.findApiByUserId(id);
//        if (api != null) {
//            BinanceSnapshotAccountRequest request = new BinanceSnapshotAccountRequest(
//                    api.getBinanceSecretKey(), api.getBinanceKey());
//            BinanceResponse response = request.sendFutures();
//            return response.getAllBalances();
//        }
//        return null;
//    }

    @Override
    public boolean isUserExist(String key, String secretKey) throws Exception {
        BinanceAccInfoRequest request = new BinanceAccInfoRequest(secretKey, key);
        BinanceResponse response = request.send();
        try{
            if (response.getResponseCode() != 200){
                return false;
            }
        } catch (Exception e){
            return true;
        }
        return true;
    }

    @Override
    public ArrayList<String> getExchangeInfo() throws Exception {
        BinanceSnapshotMarketRequest request = new BinanceSnapshotMarketRequest();
        BinanceResponse response = request.send();
        return response.exchangeInfo();
    }

    @Override
    public void makeOrder(String key, String secretKey, String symbol, String side, String type, String quantity) throws Exception {
        BinanceOrderRequest request = new BinanceOrderRequest(key, secretKey, symbol, side, type, quantity);
        BinanceResponse response = request.newSpotSend();
//        System.out.println(response.getJsonString());
    }

    @Override
    public List<String> allOrders(String key, String secretKey, String symbol) throws Exception {
        BinanceOrderRequest request = new BinanceOrderRequest(key, secretKey, symbol);
        BinanceResponse response = request.allOrdersSend();
        return response.ordersInfo();
    }

    @Override
    public void disconnect(int id) {

    }
}
