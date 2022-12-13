package com.example.demo.GUI;

import com.example.demo.DemoApplication;
import com.example.demo.connection.impl.BinanceManager;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class SecondaryController {
    @FXML
    TextField tokenId;

    @Lazy
    @Autowired
    private BinanceManager manager;

    @FXML
    AnchorPane authPane;
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    public void login(ActionEvent event) throws IOException {
        String token = tokenId.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/balance.fxml"));
        Pane registerPane = (Pane) loader.load();
        authPane.getChildren().clear();
        authPane.getChildren().add(registerPane);
        String balance = null;
        Label lblData = (Label) authPane.lookup("#balanceLabel");
        try {
//            BinanceManager manager = (BinanceManager)springContext.getBean("BinanceManager");
            balance = manager.getSpotInfo(Integer.parseInt(token));
        } catch (Exception e) {
            lblData.setText("Error to connect and get balance");
            return;
        }
        lblData.setText(lblData.getText() + balance);
    }
}