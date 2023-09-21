package com.example.demo.GUI;

import com.example.demo.DemoApplication;
import com.example.demo.Entities.UserApi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class SecondaryController {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    TextField binKeyId;
    @FXML
    TextField bitMexKeyId;
    @FXML
    private Button close_button;
    @FXML
    private Button collapse_button;
    @FXML
    private HBox title_bar;
    @FXML
    private VBox main_window;
    @FXML
    TextField secretBinKeyId;
    @FXML
    TextField bitMexSecretKeyId;

    @FXML
    VBox authPane;
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    public void login(ActionEvent event) throws Exception {
        String key = binKeyId.getText();
        String secretKey = secretBinKeyId.getText();
        if (DemoApplication.isExist(key, secretKey, DemoApplication.managerType.BINANCE)){
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
    public void bitMexLogin(ActionEvent event) throws Exception {
        String key = bitMexKeyId.getText();
        String secretKey = bitMexSecretKeyId.getText();
        if (DemoApplication.isExist(key, secretKey, DemoApplication.managerType.BITMEX ) ){
            System.out.println( "BitMEX account exist");
            DemoApplication.addUser(key, secretKey);
        } else {
            System.out.println( "BitMEX account doesn't exist");
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("No such user at BitMEX!");
            a.show();
            bitMexKeyId.clear();
            bitMexSecretKeyId.clear();
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
        StringBuilder balance = new StringBuilder("Your top currencies is\n");
        Label lblData = (Label) authPane.lookup("#balanceLabel");
        try {
            Map<String, String> currencies = DemoApplication.getBalance(id, DemoApplication.managerType.BINANCE );
            for (Map.Entry<String, String> entry : currencies.entrySet()) {
                balance.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
        } catch (Exception e) {
            lblData.setText("Error to connect and get balance");
            return;
        }
        lblData.setText(lblData.getText() + balance);
    }

    @FXML
    public void bitMexSwitchAccount(int id) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/balance.fxml"));
        Pane registerPane = (Pane) loader.load();
        authPane.getChildren().clear();
        authPane.getChildren().add(registerPane);
        StringBuilder balance = new StringBuilder("Your top currencies is\n");
        Label lblData = (Label) authPane.lookup("#balanceLabel");
        try {
            Map<String, String> currencies = DemoApplication.getBalance(id, DemoApplication.managerType.BITMEX );
            for (Map.Entry<String, String> entry : currencies.entrySet()) {
                balance.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
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
            if (DemoApplication.isExist(user.getKey(), user.getSecretKey(), DemoApplication.managerType.BINANCE ) ) {
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

    @FXML
    public void onActionCloseWindow(ActionEvent event) {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onActionCollapseWindow(ActionEvent event) {
        Stage stage = (Stage) collapse_button.getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    protected void handleClickAction(MouseEvent event) {
        Stage stage = (Stage) title_bar.getScene().getWindow();
        xOffset = stage.getX() - event.getScreenX();
        yOffset = stage.getY() - event.getScreenY();
    }
    @FXML
    protected void handleMovementAction(MouseEvent event) {
        Stage stage = (Stage) title_bar.getScene().getWindow();
        stage.setX(event.getScreenX() + xOffset);
        stage.setY(event.getScreenY() + yOffset);
    }
}