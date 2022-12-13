package com.example.demo.connection.api;

public interface CryptoManager {
    String getSpotInfo(int userId) throws Exception;
    void disconnect(int userId);
}
