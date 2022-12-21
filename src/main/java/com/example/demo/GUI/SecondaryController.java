package com.example.demo.GUI;

import com.example.demo.DemoApplication;
import com.example.demo.Entities.UserApi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.security.core.parameters.P;

import java.io.IOException;
import java.util.Optional;

public class SecondaryController {
    @FXML
    TextField binKeyId;

    @FXML
    TextField secretBinKeyId;

    @FXML
    AnchorPane authPane;
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    public void login(ActionEvent event) throws Exception {
        String key = binKeyId.getText();
        String secretKey = secretBinKeyId.getText();
        if (DemoApplication.isExist(key, secretKey)){
            DemoApplication.addUser(key, secretKey);
        } else {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("No such user at binance!");
            a.show();
            binKeyId.clear();
            secretBinKeyId.clear();
            return;
        }
        switchAccount(1);
    }

    @FXML
    public void switchAccount(int id) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/balance.fxml"));
        Pane registerPane = (Pane) loader.load();
        authPane.getChildren().clear();
        authPane.getChildren().add(registerPane);
        String balance = null;
        Label lblData = (Label) authPane.lookup("#balanceLabel");
        try {
            balance = DemoApplication.getBalance(id);
        } catch (Exception e) {
            lblData.setText("Error to connect and get balance");
            return;
        }
        lblData.setText(lblData.getText() + balance);
    }

    @FXML
    private void initialize() throws IOException {
        try {
            initStart();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public boolean initStart() throws Exception {
        UserApi user = DemoApplication.getUser(1);
        if (user != null) {
            if (DemoApplication.isExist(user.getBinanceKey(), user.getBinanceSecretKey())) {
                switchAccount(1);
            } else {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.CONFIRMATION);
                a.setContentText("Can't get information about saved user from binance. Do you want to delete this record or Exit the App?");
                ButtonType buttonTypeOne = new ButtonType("Delete");
                ButtonType buttonTypeTwo = new ButtonType("Exit");
                a.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
                Optional<ButtonType> result = a.showAndWait();
                if (result.get() == buttonTypeOne) {
                    DemoApplication.deleteUser(1);
                } else if (result.get() == buttonTypeTwo) {
                    return false;
                }
                a.show();
            }
        }
        return true;
    }
}