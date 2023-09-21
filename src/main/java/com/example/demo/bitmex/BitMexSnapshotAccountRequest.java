package com.example.demo.bitmex;

import com.example.demo.binance.BinanceResponse;
import com.example.demo.binance.BinanceTime;
import com.example.demo.binance.Signature;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.net.URL;

public class BitMexSnapshotAccountRequest {
//
//    private static String url = "https://api.binance.com/sapi/v1/accountSnapshot?";
//    private String secretKey;
//    private String apiKey;
//
//    private final static String symbol = "BTCUSDT";
//    private final static String type_spot = "SPOT";
//    private final static String type_margin = "MARGIN";
//    private final static String type_futures = "FUTURES";
//    private final static String recvWindow = "10000";
//    private final String timeStamp;
//    private String signature;
//    private Signature sign = new Signature();
//    private OkHttpClient client;
//
//    public BinanceSnapshotAccountRequest(String secretKey, String apiKey) throws Exception {
//        this.secretKey = secretKey;
//        this.apiKey = apiKey;
//        this.client = new OkHttpClient();
//        this.timeStamp = BinanceTime.get().getServerTime();
//    }
//
//    public BinanceResponse sendSpot() throws IOException {
//        this.signature = sign.getSignature("timestamp=" + timeStamp + "&type=" + type_spot, secretKey);
//        Request request = new Request.Builder()
//                .url(new URL(url + "timestamp=" + timeStamp + "&type=" + type_spot + "&signature=" + signature))
//                .addHeader("X-MBX-APIKEY", apiKey)
//                .build();
//        ResponseBody response = client.newCall(request).execute().body();
//        return new BinanceResponse(response.string());
//    }
//
//    public BinanceResponse sendMargin() throws IOException {
//        this.signature = sign.getSignature("timestamp=" + timeStamp + "&type=" + type_margin, secretKey);
//        Request request = new Request.Builder()
//                .url(new URL(url + "timestamp=" + timeStamp + "&type=" + type_margin + "&signature=" + signature))
//                .addHeader("X-MBX-APIKEY", apiKey)
//                .build();
//        ResponseBody response = client.newCall(request).execute().body();
//        return new BinanceResponse(response.string());
//    }
//
//    public BinanceResponse sendFutures() throws IOException {
//        this.signature = sign.getSignature("timestamp=" + timeStamp + "&type=" + type_futures, secretKey);
//        Request request = new Request.Builder()
//                .url(new URL(url + "timestamp=" + timeStamp + "&type=" + type_futures + "&signature=" + signature))
//                .addHeader("X-MBX-APIKEY", apiKey)
//                .build();
//        ResponseBody response = client.newCall(request).execute().body();
//        return new BinanceResponse(response.string());
//    }

}
