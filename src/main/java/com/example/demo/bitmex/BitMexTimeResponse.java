package com.example.demo.bitmex;

public class BitMexTimeResponse {
    private final String name;
    private final String version;
    private final String timeStamp;

    public BitMexTimeResponse(String name, String version, String timeStamp) {
        this.name = name;
        this.version = version;
        this.timeStamp = timeStamp;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
