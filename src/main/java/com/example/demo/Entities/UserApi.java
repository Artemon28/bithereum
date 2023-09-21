package com.example.demo.Entities;

import org.springframework.stereotype.Component;

public class UserApi {

    private Long id;

    private String key;

    private String secretKey;

    private User user;

    public UserApi() {
        id = 1L;
    }

    public UserApi(String key, String secretKey, User user) {
        this.id = 1L;
        this.key = key;
        this.secretKey = secretKey;
        this.user = user;
    }

    public UserApi(Long id, String key, String secretKey, User user) {
        this.id = id;
        this.key = key;
        this.secretKey = secretKey;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getKey() {
        return key;
    }

    public Long getId() {
        return id;
    }

    public String getSecretKey() {
        return secretKey;
    }
    public Long getUserId() {
        return user.getId();
    }
}
