package com.example.demo.bitmex;

import com.example.demo.bitmex.BitMexTimeResponse;
import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;

public class BitMexTime {
    private static final String timeApi = "https://testnet.bitmex.com/api/v1/";

    public static BitMexTimeResponse get() throws IOException {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder build = HttpUrl.parse(timeApi).newBuilder();
        Request req1 = new Request.Builder()
                .url(build.build())
                .build();
        ResponseBody time = client.newCall(req1).execute().body();
        Gson gson = new Gson();
        BitMexTimeResponse tmp = gson.fromJson(time.string(), BitMexTimeResponse.class);
        return tmp;
    }
}


