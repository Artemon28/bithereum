package com.example.demo.GUI;

import com.example.demo.DemoApplication;
import com.example.demo.connection.impl.BinanceManager;
import com.example.demo.services.BitmexApiService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BitmexPageController {

    public VBox bitmexPage;
    @Autowired
    BitmexApiService apiService;
    @Autowired
    private ConfigurableApplicationContext springContext;

    @FXML
    public void initialize() throws Exception {
        if (apiService.findApiByUserId(1) != null) {
            switchToBalance();
        }
    }
    public void switchToBalance() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/balanceBitmex.fxml"));
        loader.setControllerFactory(springContext::getBean);
        Pane registerPane = (Pane) loader.load();
        bitmexPage.getChildren().clear();
        bitmexPage.getChildren().add(registerPane);
    }

    public void switchToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cnbLogin.fxml"));
        loader.setControllerFactory(springContext::getBean);
        Pane registerPane = (Pane) loader.load();
        bitmexPage.getChildren().clear();
        bitmexPage.getChildren().add(registerPane);
    }
}
