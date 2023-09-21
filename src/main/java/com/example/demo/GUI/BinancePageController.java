package com.example.demo.GUI;

import com.example.demo.DemoApplication;
import com.example.demo.services.BinanceApiService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class BinancePageController {

    @Autowired
    BinanceApiService apiService;
    @Autowired
    private ConfigurableApplicationContext springContext;
    public VBox binancePageController;

    @FXML
    public void initialize() throws Exception {
        if (apiService.findApiByUserId(1) != null) {
            switchToBalance();
        }
    }

    public void switchToBalance() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/balance.fxml"));
        loader.setControllerFactory(springContext::getBean);
        Pane registerPane = (Pane) loader.load();
        binancePageController.getChildren().clear();
        binancePageController.getChildren().add(registerPane);
    }

    public void switchToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/binLogin.fxml"));
        loader.setControllerFactory(springContext::getBean);
        Pane registerPane = (Pane) loader.load();
        binancePageController.getChildren().clear();
        binancePageController.getChildren().add(registerPane);
    }
}
