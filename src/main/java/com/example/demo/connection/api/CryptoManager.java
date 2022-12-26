package com.example.demo.connection.api;

public interface CryptoManager {
    String getSpotInfo(int userId) throws Exception;
    String getMarginInfo(int userId) throws Exception;
    String getFuturesInfo(int userId) throws Exception;
    public boolean isUserExist(String key, String secretKey) throws Exception;
    void disconnect(int userId);
}
