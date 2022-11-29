package com.example.demo.Entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "users_api")
public class UserApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "binance_key")
    private String binanceKey;

    @Column(name = "binance_secret_key")
    private String binanceSecretKey;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public UserApi() { }

    public void setUser(User user) {
        this.user = user;
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

    public String getBinanceSecretKey() {
        return binanceSecretKey;
    }
}
