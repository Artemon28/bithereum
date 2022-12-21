package com.example.demo;

import com.example.demo.Entities.User;
import com.example.demo.Entities.UserApi;
import com.example.demo.GUI.SecondaryController;
import com.example.demo.GUI.loadController;
import com.example.demo.connection.impl.BinanceManager;
import com.example.demo.repositories.UserApiRepository;
import com.example.demo.repositories.UserApiRepositoryImpl;
import com.example.demo.repositories.UserInfoRepositoryImpl;
import com.example.demo.services.ApiService;
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
import javafx.stage.WindowEvent;
import org.springframework.context.ConfigurableApplicationContext;
import java.io.IOException;

public class DemoApplication extends Application {

    protected ConfigurableApplicationContext springContext;

    private UserService service;

    private static ApiService apiService;

    private static BinanceManager manager;

    public static void main(String[] args) throws Exception {
        UserInfoRepositoryImpl dbInfo = new UserInfoRepositoryImpl("test.db");
        UserApiRepositoryImpl dbApi = new UserApiRepositoryImpl("test.db");
        dbInfo.createNewDatabase();
        dbApi.createNewDatabase();
        apiService = new ApiService(dbApi);
        manager = new BinanceManager(apiService);
        Application.launch(args);
    }

    public static String getBalance(int id) throws Exception {
        return manager.getSpotInfo(id);
    }

    public static UserApi getUser(int id) throws Exception {
        return apiService.findApiByUserId(id);
    }

    public static boolean isExist(String key, String secretKey) throws Exception {
        return manager.isUserExist(key, secretKey);
    }

    public static void addUser(String key, String secretKey){
        apiService.addApi(new UserApi(1L, key, secretKey, new User()));
    }

    public static void deleteUser(int id){
        apiService.deleteApi(id);
    }

    private Scene scene;

    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/" + fxml + ".fxml"));
        fxmlLoader.setLocation(getClass().getResource("/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
}

//--module-path "\path\to\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml
