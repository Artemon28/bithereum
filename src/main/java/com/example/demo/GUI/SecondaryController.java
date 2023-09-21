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
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Component
public class SecondaryController {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Button close_button;
    @FXML
    private Button collapse_button;
    @FXML
    private HBox title_bar;

    @FXML
    private void initialize() throws IOException {
        try {
            initStart();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initStart() throws Exception {

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