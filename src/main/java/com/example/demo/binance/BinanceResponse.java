package com.example.demo.binance;

import org.json.JSONObject;

public class BinanceResponse {
    private int code;
    private String msg;
    private String jsonString;
    private JSONObject obj;

    public BinanceResponse(String jsonString) {
        this.jsonString = jsonString;
        this.obj = new JSONObject(this.jsonString);
    }

    public String getTotalAssetsOfBtc() {
        return obj.getJSONArray("snapshotVos").getJSONObject(0).getJSONObject("data").getString("totalAssetOfBtc");
    }

    public String getJsonString() {
        return jsonString;
    }
}
