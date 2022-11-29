package com.example.demo.binance;

public class BinanceTimeResponse {
    private final String serverTime;

    public BinanceTimeResponse(String serverTime) {
        this.serverTime = serverTime;
    }

    public String getServerTime() {
        return serverTime;
    }
}
