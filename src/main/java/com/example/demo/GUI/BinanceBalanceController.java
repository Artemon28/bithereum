package com.example.demo.GUI;

import com.example.demo.DemoApplication;
import com.example.demo.Entities.UserApi;
import com.example.demo.connection.impl.BinanceManager;
import com.example.demo.services.BinanceApiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public class BinanceBalanceController {

    @Autowired
    BinanceManager manager;
    @Autowired
    BinanceApiService service;
    @FXML
    ChoiceBox<String> buyWhat;

    @FXML
    ChoiceBox<String> orderType;
    @FXML
    TextField amount;
    @Autowired
    BinancePageController pageController;
    public VBox binBalance;

    @FXML
    public void initialize(){

        orderType.getItems().addAll(types);
        ArrayList<String> curs = new ArrayList<>();
        try {
            curs = manager.getExchangeInfo();
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText(e.getMessage());
            a.show();
        }
        buyWhat.getItems().addAll(curs);

        StringBuilder balance = new StringBuilder("Your top currencies is\n");
        Label lblData = (Label) binBalance.lookup("#balanceLabel");
        try {
            Map<String, String> currencies = manager.getSpotInfo(1);
            for (Map.Entry<String, String> entry : currencies.entrySet()) {
                balance.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
        } catch (Exception e) {
            lblData.setText("Error to connect and get balance");
            return;
        }
        lblData.setText(lblData.getText() + balance);
    }

    private final String[] types = {"BUY", "SELL"};

    @FXML
    public void makeOrder(){
        try {
            UserApi user = service.findApiByUserId(1);
            String key = user.getKey();
            String secretKey = user.getSecretKey();
            manager.makeOrder(key, secretKey, buyWhat.getValue(), orderType.getValue(), "MARKET", amount.getText());
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText(e.getMessage());
            a.show();
        }
    }

    @FXML
    public void getAllOrders(){
        if (buyWhat.getValue() == null){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Choose your trade pair");
            a.show();
            return;
        }

        Stage allOrders = new Stage();
        allOrders.initModality(Modality.APPLICATION_MODAL);
        Label orders = new Label();
        orders.setMaxWidth(580);
        orders.setWrapText(true);
        try {
            UserApi user = service.findApiByUserId(1);
            String key = user.getKey();
            String secretKey = user.getSecretKey();
            orders.setText(manager.allOrders(key, secretKey, buyWhat.getValue()).toString());
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText(e.getMessage());
            a.show();
        }
        ScrollPane scpane = new ScrollPane(orders);
        scpane.setPrefViewportHeight(380);
        scpane.setPrefViewportWidth(580);
        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10, scpane);
        Scene scene = new Scene(root, 600, 400);
        allOrders.setScene(scene);
        allOrders.setTitle("all Orders");
        allOrders.setX(allOrders.getX() + 50);
        allOrders.setY(allOrders.getY() + 50);
        allOrders.showAndWait();
    }


    @FXML
    public void refresh(){
        StringBuilder balance = new StringBuilder("Your top currencies is\n");
        Label lblData = (Label) binBalance.lookup("#balanceLabel");
        try {
            Map<String, String> currencies = manager.getSpotInfo(1);;
            for (Map.Entry<String, String> entry : currencies.entrySet()) {
                balance.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
        } catch (Exception e) {
            lblData.setText("Error to connect and get balance");
            return;
        }
        lblData.setText("" + balance);
    }

    @FXML
    public void logoutBinance() throws Exception {
        service.deleteApi(1);
        pageController.switchToLogin();
    }
}
