package com.example.demo.connection.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CryptoManager {
    Map<String, String> getSpotInfo(int userId) throws Exception;
    String getMarginInfo(int userId) throws Exception;
//    String getFuturesInfo(int userId) throws Exception;
    public boolean isUserExist(String key, String secretKey) throws Exception;
    void disconnect(int userId);
    ArrayList<String> getExchangeInfo() throws Exception;
    public void makeOrder(String key, String secretKey, String symbol, String side, String type, String quoteOrderQty) throws Exception;
    public List<String> allOrders(String key, String secretKey, String symbol) throws Exception;
}
