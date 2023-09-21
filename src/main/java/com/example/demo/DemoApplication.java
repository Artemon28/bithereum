package com.example.demo;

import com.example.demo.Entities.User;
import com.example.demo.Entities.UserApi;
import com.example.demo.GUI.SecondaryController;
import com.example.demo.GUI.loadController;
import com.example.demo.connection.impl.BinanceManager;
import com.example.demo.connection.impl.BitMexManager;
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
    public static enum managerType {
        BINANCE,
        BITMEX;
    }

    private UserService service;

    private static ApiService apiService;

    private static BinanceManager manager;
    private static BitMexManager bitMexManager;

    public static void main(String[] args) throws Exception {
        UserInfoRepositoryImpl dbInfo = new UserInfoRepositoryImpl("test.db");
        UserApiRepositoryImpl dbApi = new UserApiRepositoryImpl("test.db");
        dbInfo.createNewDatabase();
        dbApi.createNewDatabase();
        apiService = new ApiService(dbApi);
        manager = new BinanceManager(apiService);
        bitMexManager = new BitMexManager( apiService );
//        makeOrder();
//        getAllOrders();
        Application.launch(args);
    }

    public static Map<String, String> getBalance(int id, managerType type) throws Exception {
        switch ( type ) {
            case BINANCE: {
                return manager.getSpotInfo( id );
            }
            case BITMEX: {
                return bitMexManager.getSpotInfo( id );
            }
            default:
                throw new Exception("new managerType");
        }
    }

    public static UserApi getUser(int id) throws Exception {
        return apiService.findApiByUserId(id);
    }

    public static boolean isExist(String key, String secretKey, managerType type ) throws Exception {
        switch ( type ) {
            case BINANCE: {
                System.out.println( "BINANCE");
                return manager.isUserExist(key, secretKey);
            }
            case BITMEX: {
                System.out.println("BITMEX");
                return bitMexManager.isUserExist(key, secretKey);
            }
            default:
                throw new Exception("new managerType");
        }
    }

    public static ArrayList<String> getExchangeInfo( managerType type ) throws Exception {
//        System.out.println(manager.getExchangeInfo());
        switch ( type ) {
            case BINANCE: {
                return manager.getExchangeInfo();
            }
            case BITMEX: {
                return bitMexManager.getExchangeInfo();
            }
            default:
                throw new Exception("new managerType");
        }
    }

    public static List<String> getAllOrders(String symbol, managerType type ) throws Exception{
        UserApi user = apiService.findApiByUserId(1);
        String key = user.getKey();
        String secretKey = user.getSecretKey();
        switch ( type ) {
            case BINANCE: {
                return manager.allOrders( key, secretKey, symbol );
            }
            case BITMEX: {
                return bitMexManager.allOrders( key, secretKey, symbol );
            }
            default:
                throw new Exception("new managerType");
        }
    }

    public static void makeOrder(String symbol, String orderType, String qty, managerType type ) throws Exception{
        UserApi user = apiService.findApiByUserId(1);
        String key = user.getKey();
        String secretKey = user.getSecretKey();
        switch ( type ) {
            case BINANCE: {
                manager.makeOrder(key, secretKey, symbol, orderType, "MARKET", qty);
                break;
            }
            case BITMEX: {
                bitMexManager.makeOrder(key, secretKey, symbol, orderType, "MARKET", qty);
                break;
            }
        }
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
