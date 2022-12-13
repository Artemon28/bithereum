package com.example.demo.GUI;

import com.example.demo.DemoApplication;
import javafx.application.Application;
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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

public class startSceneController extends Application {
    @FXML
    AnchorPane authPane;
    private Parent root;
    private Stage stage;
    private Scene scene;


    @Override
    public void start(Stage stage) throws IOException {
//        try{
//            Parent root = FXMLLoader.load(startSceneController.class.getResource("primary.fxml"));
//            Scene scene1 = new Scene(root);
//            stage.setScene(scene1);
//            stage.show();
//        } catch (Exception e){
//            e.printStackTrace();
//        }


        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
        SpringApplication.run(DemoApplication.class);
    }

    void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private Parent loadFXML(String fxml) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(startSceneController.class.getResource("/primary.fxml"));
        fxmlLoader.setLocation(getClass().getResource("/primary.fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


