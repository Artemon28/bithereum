package com.example.demo.binance;

import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;

public class BinanceTime {
    private static final String timeApi = "https://api.binance.com/api/v3/time";

    public static BinanceTimeResponse get() throws IOException {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder build = HttpUrl.parse(timeApi).newBuilder();
        Request req1 = new Request.Builder()
                .url(build.build())
                .build();
        ResponseBody time = client.newCall(req1).execute().body();
        Gson gson = new Gson();
        return gson.fromJson(time.string(), BinanceTimeResponse.class);
    }
}


