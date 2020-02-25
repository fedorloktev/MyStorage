package com.company;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.swing.*;

public class Authorization {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField password;

    @FXML
    private Button signIn;

    @FXML
    private Text invalid;

    @FXML
    private TextField login;

    @FXML
    void initialize() {
       signIn.setOnAction(event -> {
           String log = login.getText(); String pass = password.getText();
           if (log.equals("admin") && pass.equals("admin") || log.equals("staff") && pass.equals("admin")) {
               Main main = new Main();
               try {
                   main.callWindow("filesGUI/Menu.fxml", "MyStorage - Меню", 800, 600);
               } catch (Exception e) {
               } signIn.getScene().getWindow().hide();
           } else invalid.setVisible(true);
       });

    }
}

