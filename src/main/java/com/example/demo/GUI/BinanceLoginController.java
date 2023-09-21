package com.example.demo.GUI;

import com.example.demo.DemoApplication;
import com.example.demo.Entities.User;
import com.example.demo.Entities.UserApi;
import com.example.demo.connection.impl.BinanceManager;
import com.example.demo.services.BinanceApiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BinanceLoginController {

    @Autowired
    BinanceApiService apiService;
    @Autowired
    BinanceManager manager;
    @Autowired
    BinancePageController pageController;
    public TextField binKeyId;
    public TextField secretBinKeyId;

    @FXML
    public void login(ActionEvent event) throws Exception {
        String key = binKeyId.getText();
        String secretKey = secretBinKeyId.getText();
        if (manager.isUserExist(key, secretKey)){
            if (apiService.findApiByUserId(1) == null)
                apiService.addApi(new UserApi(key, secretKey, new User()));
            pageController.switchToBalance();
        } else {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("No such user at binance!");
            a.show();
            binKeyId.clear();
            secretBinKeyId.clear();
        }
    }
}
