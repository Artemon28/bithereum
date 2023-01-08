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
import java.util.LinkedHashMap;

public class BinanceSnapshotMarketRequest {

    private static String url = "https://testnet.binance.vision/api/v3/exchangeInfo";
    private final String timeStamp;
//    private final String signature;
    private Signature sign = new Signature();

    public BinanceSnapshotMarketRequest() throws Exception {
        this.timeStamp = BinanceTime.get().getServerTime();
//        this.signature = sign.getSignature("timestamp=" + timeStamp + "&type=" + type, secretKey);
    }

    public BinanceResponse send() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(new URL(url))
                .build();
        ResponseBody response = client.newCall(request).execute().body();
        return new BinanceResponse(response.string());
    }

}
