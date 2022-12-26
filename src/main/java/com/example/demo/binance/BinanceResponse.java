package com.example.demo.binance;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;
import java.util.TreeMap;

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

    public int getResponseCode() {
        return (int)obj.get("code");
    }

    public Map<String, String> getAllBalances() {
        Map<String, String> balances = new TreeMap<>();
        JSONArray arr = obj.getJSONArray("snapshotVos").getJSONObject(0).getJSONObject("data").getJSONArray("balances");
        for (int i = 0; i < arr.length(); i++) {
            if(!arr.getJSONObject(i).getString("free").equals("0")) {
                balances.put(arr.getJSONObject(i).getString("asset"), arr.getJSONObject(i).getString("free"));
            }
        }
        return balances;
    }


    public String getJsonString() {
        return jsonString;
    }
}
