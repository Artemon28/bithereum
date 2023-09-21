package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.example.demo.Entities.User;
import com.example.demo.Entities.UserApi;
import com.example.demo.GUI.SecondaryController;
import com.example.demo.GUI.loadController;
import com.example.demo.connection.impl.BinanceManager;
import com.example.demo.connection.impl.BitMexManager;
import com.example.demo.repositories.UserApiBitMexRepositoryImpl;
import com.example.demo.repositories.UserApiRepository;
import com.example.demo.repositories.UserApiBinanceRepositoryImpl;
import com.example.demo.repositories.UserInfoRepositoryImpl;
import com.example.demo.services.UserService;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoApplication extends Application {

    public ConfigurableApplicationContext springContext;

    public static void main(String[] args) throws Exception {
//        makeOrder();
//        getAllOrders();
        Application.launch(args);
    }

    private Scene scene;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(DemoApplication.class);
        Parent parent = loadFXML("primary");
        scene = new Scene(parent);
        //springContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    }

    public void start(Stage stage) throws IOException {
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }

    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/" + fxml + ".fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        fxmlLoader.setLocation(getClass().getResource("/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
    @Override
    public void stop() throws Exception {
        springContext.close();
    }
}

//--module-path "\path\to\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml
