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
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DemoApplication extends Application {


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
//        makeOrder();
//        getAllOrders();
        Application.launch(args);
    }

    public static Map<String, String> getBalance(int id) throws Exception {
        return manager.getSpotInfo(id);
    }

    public static UserApi getUser(int id) throws Exception {
        return apiService.findApiByUserId(id);
    }

    public static boolean isExist(String key, String secretKey) throws Exception {
        return manager.isUserExist(key, secretKey);
    }

    public static ArrayList<String> getExchangeInfo() throws Exception {
//        System.out.println(manager.getExchangeInfo());
        return manager.getExchangeInfo();
    }

    public static List<String> getAllOrders(String symbol) throws Exception{
        UserApi user = apiService.findApiByUserId(1);
        String key = user.getBinanceKey();
        String secretKey = user.getBinanceSecretKey();
        return manager.allOrders(key, secretKey, symbol);
    }

    public static void makeOrder(String symbol, String orderType, String qty) throws Exception{
        UserApi user = apiService.findApiByUserId(1);
        String key = user.getBinanceKey();
        String secretKey = user.getBinanceSecretKey();
        manager.makeOrder(key, secretKey, symbol, orderType, "MARKET", qty);
    }

    public static void addUser(String key, String secretKey){
        apiService.addApi(new UserApi(1L, key, secretKey, new User()));
    }

    public static void deleteUser(int id){
        apiService.deleteApi(id);
    }

    private Scene scene;

    public void start(Stage stage) throws IOException {
        Parent parent = loadFXML("primary");
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/" + fxml + ".fxml"));
        fxmlLoader.setLocation(getClass().getResource("/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
}

//--module-path "\path\to\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml
