package com.example.demo.connection.api;

public interface CryptoManager {
    void getSpotInfo(int userId) throws Exception;
    void disconnect(int userId);
}
