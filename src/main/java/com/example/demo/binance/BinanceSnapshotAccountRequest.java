package com.example.demo.binance;

import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class BinanceSnapshotAccountRequest {

    private static String url = "https://api.binance.com/sapi/v1/accountSnapshot?";
    private String secretKey;
    private String apiKey;

    private final static String symbol = "BTCUSDT";
    private final static String type = "SPOT";
    private final static String recvWindow = "10000";
    private final String timeStamp;
    private final String signature;
    private Signature sign = new Signature();

    public BinanceSnapshotAccountRequest(String secretKey, String apiKey) throws Exception {
        this.secretKey = secretKey;
        this.apiKey = apiKey;
        this.timeStamp = BinanceTime.get().getServerTime();
        this.signature = sign.getSignature("timestamp=" + timeStamp + "&type=" + type, secretKey);
    }

    public BinanceResponse send() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(new URL(url + "timestamp=" + timeStamp + "&type=" + type + "&signature=" + signature))
                .addHeader("X-MBX-APIKEY", apiKey)
                .build();
        ResponseBody response = client.newCall(request).execute().body();
        return new BinanceResponse(response.string());
    }

}
