package com.example.demo.binance;

import okhttp3.*;

import java.io.IOException;
import java.net.URL;

public class BinanceOrderRequest {

    private static String urlAllOrders = "https://testnet.binance.vision/api/v3/allOrders?";
    private static String urlNewOrder = "https://testnet.binance.vision/api/v3/order?";
    private String secretKey;
    private String apiKey;

    private String symbol = "BTCUSDT";
    private String type = "SPOT";
    private final String timeStamp;
    private final String signature;
    private String quantity;
    private String side;
    private Signature sign = new Signature();

    public BinanceOrderRequest(String apiKey, String secretKey, String symbol) throws Exception {
        this.secretKey = secretKey;
        this.apiKey = apiKey;
        this.symbol = symbol;
        this.timeStamp = BinanceTime.get().getServerTime();
        this.signature = sign.getSignature("timestamp=" + timeStamp + "&symbol=" + symbol, secretKey);
    }

    public BinanceOrderRequest(String apiKey, String secretKey, String symbol, String side, String type,
                               String quantity) throws Exception {
        this.secretKey = secretKey;
        this.symbol = symbol;
        this.apiKey = apiKey;
        this.timeStamp = BinanceTime.get().getServerTime();
        this.quantity = quantity;
        this.side = side;
        this.type = type;
        this.signature = sign.getSignature("symbol=" + symbol + "&side=" + side +
                "&type=" + type + "&quantity=" + quantity + "&timestamp=" + timeStamp, secretKey);
    }

    public BinanceResponse allOrdersSend() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(new URL(urlAllOrders + "timestamp=" + timeStamp + "&symbol=" + symbol + "&signature=" + signature))
                .addHeader("X-MBX-APIKEY", apiKey)
                .build();
        ResponseBody response = client.newCall(request).execute().body();
        return new BinanceResponse(response.string());
    }

    public BinanceResponse newSpotSend() throws IOException { //side = BUY or SELL
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(new URL(urlNewOrder + "symbol=" + symbol + "&side=" + side + "&type=" + type + "&quantity=" + quantity + "&timestamp=" + timeStamp + "&signature=" + signature))
                .addHeader("X-MBX-APIKEY", apiKey)
                .method("POST", RequestBody.create(new byte[]{}))
                .build();
        ResponseBody response = client.newCall(request).execute().body();
        return new BinanceResponse(response.string());
    }
}
