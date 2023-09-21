package com.example.demo.bitmex;

import com.example.demo.bitmex.BitMexResponse;
import com.example.demo.bitmex.BitMexTime;
import com.google.gson.internal.bind.util.ISO8601Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URL;

public class BitMexAccInfoRequest {
    private static String url = "https://testnet.bitmex.com/api/v1";
    private String secretKey;
    private String apiKey;
    private final String timeStamp;
    private Signature sign = new Signature();
    private final String signature;

    public BitMexAccInfoRequest(String secretKey, String apiKey) throws Exception {
        this.secretKey = secretKey;
        this.apiKey = apiKey;

        System.out.println("timestamp");
        this.timeStamp = BitMexTime.get().getTimeStamp();
        System.out.println( "NOW TIMESTAMP");
        System.out.println( timeStamp );
        System.out.println("=====");
        String verb = "GET";
        String path = "/user";
        String data = verb + path + timeStamp + "";
        this.signature = sign.getSignature(data, secretKey);
        System.out.println( signature );
    }

    public BitMexResponse send() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(new URL(url + "timestamp=" + timeStamp + "&signature=" + signature))
                .addHeader("X-MBX-APIKEY", apiKey)
                .build();
        ResponseBody response = client.newCall(request).execute().body();
        return new BitMexResponse( response.string() );
    }

}
