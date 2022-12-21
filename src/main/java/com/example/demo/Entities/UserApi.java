package com.example.demo.Entities;

public class UserApi {

    private Long id;

    private String binanceKey;

    private String binanceSecretKey;

    private User user;

    public UserApi() { }

    public UserApi(String binanceKey, String binanceSecretKey, User user) {
        this.id = id;
        this.binanceKey = binanceKey;
        this.binanceSecretKey = binanceSecretKey;
        this.user = user;
    }

    public UserApi(Long id, String binanceKey, String binanceSecretKey, User user) {
        this.id = id;
        this.binanceKey = binanceKey;
        this.binanceSecretKey = binanceSecretKey;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setBinanceKey(String binanceKey) {
        this.binanceKey = binanceKey;
    }
    public void setBinanceSecretKey(String binanceSecretKey) {
        this.binanceSecretKey = binanceSecretKey;
    }

    public String getBinanceKey() {
        return binanceKey;
    }

    public Long getId() {
        return id;
    }

    public String getBinanceSecretKey() {
        return binanceSecretKey;
    }
    public Long getUserId() {
        return user.getId();
    }
}
