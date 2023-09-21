package com.example.demo.GUI;

import com.example.demo.DemoApplication;
import com.example.demo.connection.impl.BitMexManager;
import com.example.demo.services.BitmexApiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public class BitmexBalancePageController {

    @Autowired
    BitmexApiService service;

    @Autowired
    BitMexManager manager;

    public Label balanceLabelBitmex;
    @FXML
    ChoiceBox<String> buyWhatBitmex;

    @FXML
    ChoiceBox<String> orderType;

    @FXML
    TextField amount;

    private final String[] types = {"BUY", "SELL"};

    public VBox bitmexBalance;
    @Autowired
    BitmexPageController pageController;

    @FXML
    public void logoutBitmex(ActionEvent event) throws Exception {
        service.deleteApi(1);
        pageController.switchToLogin();
    }


    @FXML
    public void initialize(){
        orderType.getItems().addAll(types);
        ArrayList<String> curs = new ArrayList<>();

        StringBuilder balance = new StringBuilder("Your top currencies is\n");
        Label lblData = (Label) balanceLabelBitmex.lookup("#balanceLabelBitmex");
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

    @FXML
    public void makeOrder(){
        try {

        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText(e.getMessage());
            a.show();
        }
    }

    @FXML
    public void getAllOrders(){
        if (buyWhatBitmex.getValue() == null){
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
        Label lblData = (Label) balanceLabelBitmex.lookup("#balanceLabelBitmex");
        try {
            Map<String, String> currencies = manager.getSpotInfo(1);
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
