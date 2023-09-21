package com.example.demo.connection.impl;

import com.example.demo.Entities.UserApi;
import com.example.demo.bitmex.BitMexAccInfoRequest;
import com.example.demo.bitmex.BitMexResponse;
import com.example.demo.connection.api.CryptoManager;
import com.example.demo.services.BitmexApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Component
public class BitMexManager implements CryptoManager {
    @Autowired
    private BitmexApiService service;
    public BitMexManager( ){
    }

    @Override
    public Map<String, String> getSpotInfo(int id) throws Exception {
        UserApi api = service.findApiByUserId(id);
        if (api != null) {
            BitMexAccInfoRequest request = new BitMexAccInfoRequest(
                    api.getSecretKey(), api.getKey());
            BitMexResponse response = request.send();
            return response.getAllBalances();
        }
        return null;
    }

    @Override
    public String getMarginInfo(int id) throws Exception {
        UserApi api = service.findApiByUserId(id);
        if (api != null) {
//            BitMexSnapshotAccountRequest request = new BitMexSnapshotAccountRequest(
//                    api.getSecretKey(), api.getKey());
//            BitMexResponse response = request.sendMargin();
//            return response.getJsonString();
        }
        return null;
    }

    @Override
    public boolean isUserExist(String key, String secretKey) throws Exception {
        BitMexAccInfoRequest request = new BitMexAccInfoRequest(secretKey, key);
        BitMexResponse response = request.send();
        System.out.println( response.getJsonString() );
        try{
            if (response.getResponseCode() != 200){
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            return true;
        }
        return true;
    }

    @Override
    public ArrayList<String> getExchangeInfo() throws Exception {
//        BitMexSnapshotMarketRequest request = new BitMexSnapshotMarketRequest();
//        BitMexResponse response = request.send();
//        return response.exchangeInfo();
        return null;
    }

    @Override
    public void makeOrder(String key, String secretKey, String symbol, String side, String type, String quantity) throws Exception {
//        BitMexOrderRequest request = new BitMexOrderRequest(key, secretKey, symbol, side, type, quantity);
//        BitMexResponse response = request.newSpotSend();
    }

    @Override
    public List<String> allOrders(String key, String secretKey, String symbol) throws Exception {
//        BitMexOrderRequest request = new BitMexOrderRequest(key, secretKey, symbol);
//        BitMexResponse response = request.allOrdersSend();
//        return response.ordersInfo();
        return null;
    }

    @Override
    public void disconnect(int id) {

    }
}
