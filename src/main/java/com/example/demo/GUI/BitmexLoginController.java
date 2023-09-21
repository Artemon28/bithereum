package com.example.demo.GUI;

import com.example.demo.DemoApplication;
import com.example.demo.Entities.User;
import com.example.demo.Entities.UserApi;
import com.example.demo.connection.impl.BinanceManager;
import com.example.demo.connection.impl.BitMexManager;
import com.example.demo.services.BinanceApiService;
import com.example.demo.services.BitmexApiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BitmexLoginController {

    @Autowired
    BitMexManager manager;
    @Autowired
    BitmexApiService service;
    @Autowired
    BitmexPageController pageController;
    public TextField bitMexKeyId;
    public TextField bitMexSecretKeyId;

    @FXML
    public void login(ActionEvent event) throws Exception {
        String key = bitMexKeyId.getText();
        String secretKey = bitMexSecretKeyId.getText();
        if (manager.isUserExist(key, secretKey)){
            if (service.findApiByUserId(1) == null)
                service.addApi(new UserApi(key, secretKey, new User()));
            pageController.switchToBalance();
        } else {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("No such user at binance!");
            a.show();
            bitMexKeyId.clear();
            bitMexSecretKeyId.clear();
        }
    }

}
