package com.example.demo.bitmex;

public class BitMexTimeResponse {
    private final String name;
    private final String version;
    private final String timestamp;

    public BitMexTimeResponse(String name, String version, String timestamp) {
        this.name = name;
        this.version = version;
        this.timestamp = timestamp;
    }

    public String getTimeStamp() {
        return timestamp;
    }
}
