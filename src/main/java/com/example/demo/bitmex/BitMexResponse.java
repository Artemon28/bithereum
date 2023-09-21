package com.example.demo.bitmex;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class BitMexResponse {
    private int code;
    private String msg;
    private String jsonString;
    private JSONObject obj;
    private JSONArray objArray;

    public BitMexResponse(String jsonString) {
        this.jsonString = jsonString;
        if (this.jsonString.charAt(0) == '['){
            this.objArray = new JSONArray(this.jsonString);
            this.obj = new JSONObject();
            this.obj.append("orders", objArray);
        } else {
            this.obj = new JSONObject(this.jsonString);
        }
    }

    public String getTotalAssetsOfBtc() {
        return obj.getJSONArray("snapshotVos").getJSONObject(0).getJSONObject("data").getString("totalAssetOfBtc");
    }

    public int getResponseCode() {
        return (int)obj.get("code");
    }

    public Map<String, String> getAllBalances() {
        Map<String, String> balances = new TreeMap<>();
        JSONArray arr = obj.getJSONArray("balances");
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

    public ArrayList<String> exchangeInfo(){
        JSONArray symbols = obj.getJSONArray("symbols");
        ArrayList<String> availableSymbols = new ArrayList<>();
        for (int i = 0; i < symbols.length(); i++){
            if (symbols.getJSONObject(i).getString("status").equals("TRADING"))
                availableSymbols.add(symbols.getJSONObject(i).getString("symbol"));
        }
        if (availableSymbols.size() == 0){
            return null;
        }
        return availableSymbols;
    }

    public ArrayList<String> ordersInfo(){
        JSONArray orders = (JSONArray) obj.getJSONArray("orders").get(0);
//        obj.toJSONArray(orders);
        ArrayList<String> allOrdersList = new ArrayList<>();
        for (int i = 0; i < orders.length(); i++){
            allOrdersList.add(orders.getJSONObject(i).toString());
        }
        return allOrdersList;
    }
}
