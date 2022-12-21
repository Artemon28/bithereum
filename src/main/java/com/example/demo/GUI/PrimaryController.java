package com.example.demo.GUI;

import com.example.demo.DemoApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class PrimaryController {
    @FXML
    public void addNewUser(ActionEvent event){

    }

    @FXML
    TextField username;

    @FXML
    AnchorPane binBalance;

    @FXML
    public void exitBinUser(ActionEvent event) throws Exception {
        DemoApplication.deleteUser(1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/binLogin.fxml"));
        Pane registerPane = loader.load();
        binBalance.getChildren().clear();
        binBalance.getChildren().add(registerPane);
    }
}