package com.example.demo;

import com.example.demo.GUI.SecondaryController;
import com.example.demo.GUI.startSceneController;
import com.example.demo.connection.impl.BinanceManager;
import com.example.demo.services.ApiService;
import com.example.demo.services.UserService;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
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
public class DemoApplication extends Application {

    protected ConfigurableApplicationContext springContext;

    @Autowired
    private UserService service;

    @Autowired
    private ApiService apiService;

    @Autowired
    private BinanceManager manager;


    public static void main(String[] args) throws Exception {
//        SpringApplication.run(DemoApplication.class, args);
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = springBootApplicationContext();

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

    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
//        Parent root = FXMLLoader.load(SecondaryController.class.getResource("primary.fxml"));
//        Scene scene1 = new Scene(root);
//        stage.setScene(scene1);
//        stage.show();

        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }


    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(startSceneController.class.getResource("/primary.fxml"));
        fxmlLoader.setLocation(getClass().getResource("/primary.fxml"));
        return fxmlLoader.load();
    }

    private ConfigurableApplicationContext springBootApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
        String[] args = getParameters().getRaw().stream().toArray(String[]::new);
        return builder.run(args);
    }

}

//--module-path "\path\to\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml
