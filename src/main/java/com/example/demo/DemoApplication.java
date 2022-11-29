package com.example.demo;

import com.example.demo.Entities.UserApi;
import com.example.demo.binance.BinanceResponse;
import com.example.demo.binance.BinanceSnapshotAccountRequest;
import com.example.demo.binance.BinanceTime;
import com.example.demo.connection.impl.BinanceManager;
import com.example.demo.services.ApiService;
import com.example.demo.services.UserService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static java.util.stream.Collectors.joining;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.demo.repositories")
public class DemoApplication {

    @Autowired
    private UserService service;

    @Autowired
    private ApiService apiService;

    @Autowired
    private BinanceManager manager;


    public static void main(String[] args) throws Exception {

        SpringApplication.run(DemoApplication.class, args);
    }

    @PostConstruct
    public void doStuff() throws IOException {
        try {
            manager.getSpotInfo(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*UserApi api = new UserApi();
        api.setBinanceKey("32131231321dsd");
        api.setBinanceSecretKey("hcue2372hfi2b3jkrh329r2rhj32urn2");
        api.setUser(service.findUser(2).get());
        apiService.addApi(api);*/
    }

}
