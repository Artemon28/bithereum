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
import java.math.BigInteger;
import java.net.URL;

public class BitMexAccInfoRequest {
    private static String url = "https://testnet.bitmex.com/api/v1";
    private String secretKey;
    private String apiKey;
    private final String timeStamp;
    private Signature sign = new Signature();

    private String verb;
    private String path;
    private String expires;
    private final String signature;

    public BitMexAccInfoRequest(String secretKey, String apiKey) throws Exception {
        this.secretKey = secretKey;
        this.apiKey = apiKey;
        this.timeStamp = BitMexTime.get().getTimeStamp();
        verb = "GET";
        path = "/api/v1/user/wallet";
        expires = String.valueOf(Long.parseLong(timeStamp) + 5);
        this.signature = sign.getSignature(verb + path + expires + "", secretKey);
        System.out.println( signature );
    }

    public BitMexResponse send() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(new URL(url + "/user/wallet"))
                .addHeader("api-expires", expires)
                .addHeader("api-key", apiKey)
                .addHeader("api-signature", signature)
                .build();
        ResponseBody response = client.newCall(request).execute().body();
        return new BitMexResponse( response.string() );
    }

}
