package com.example.demo.GUI;

import com.example.demo.DemoApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Map;

public class PrimaryController {
    @FXML
    public void addNewUser(ActionEvent event){

    }

    @FXML
    TextField username;

    @FXML
    VBox binBalance;

    @FXML
    public void exitBinUser(ActionEvent event) throws Exception {
        DemoApplication.deleteUser(1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/binLogin.fxml"));
        Pane registerPane = loader.load();
        binBalance.getChildren().clear();
        binBalance.getChildren().add(registerPane);
    }


    @FXML
    ChoiceBox<String> buyWhat;

    @FXML
    ChoiceBox<String> orderType;

    @FXML
    TextField amount;

    private final String[] types = {"BUY", "SELL"};

    @FXML
    public void initialize(){
        orderType.getItems().addAll(types);
        ArrayList<String> curs = new ArrayList<>();
        try {
            curs = DemoApplication.getExchangeInfo();
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText(e.getMessage());
            a.show();
        }
        buyWhat.getItems().addAll(curs);
    }

    @FXML
    public void makeOrder(){
        try {
            DemoApplication.makeOrder(buyWhat.getValue(), orderType.getValue(), amount.getText());
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
            orders.setText(DemoApplication.getAllOrders(buyWhat.getValue()).toString());
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
            Map<String, String> currencies = DemoApplication.getBalance(1);
            for (Map.Entry<String, String> entry : currencies.entrySet()) {
                balance.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
        } catch (Exception e) {
            lblData.setText("Error to connect and get balance");
            return;
        }
        lblData.setText("" + balance);
    }
}