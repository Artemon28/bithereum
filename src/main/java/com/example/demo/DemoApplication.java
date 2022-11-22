package com.example.demo;

import com.example.demo.Entities.User;
import com.example.demo.Entities.UserApi;
import com.example.demo.connection.impl.BinanceManager;
import com.example.demo.services.ApiService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;

import static java.lang.Thread.sleep;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.demo.repositories")
public class DemoApplication {

    @Autowired
    private UserService service;

    @Autowired
    private ApiService apiService;

    @Autowired
    private BinanceManager manager;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostConstruct
    public void doStuff() {
        UserApi api = new UserApi();
        api.setBinanceKey("fjqoeinjr1obn4hb14b18vyhvj1b4h76");
        User vain = service.findUser(1L).get();
        api.setUser(vain);
        apiService.addApi(api);
        manager.connect(1);
    }

}
