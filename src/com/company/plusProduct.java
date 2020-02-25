package com.company;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.swing.*;

public class plusProduct {


    @FXML
    private Button add;

    @FXML
    private MenuItem gun;

    @FXML
    private TextField nameProd;

    @FXML
    private MenuItem ammo;

    @FXML
    private MenuItem aksess;

    @FXML
    private MenuButton listStorage;

    @FXML
    private TextField price;

    @FXML
    private Text invalid;

    @FXML
    private TextField articProd;

    @FXML
    private Button back;

    @FXML
    private MenuItem suit;
    String nameStorage = "non";
    @FXML
    void initialize(){
        gun.setOnAction(e -> {nameStorage = gun.getText(); listStorage.setText(gun.getText());});
        ammo.setOnAction(e -> {nameStorage = ammo.getText(); listStorage.setText(ammo.getText());});
        aksess.setOnAction(e -> {nameStorage = aksess.getText();listStorage.setText(aksess.getText());});
        suit.setOnAction(e -> {nameStorage = suit.getText();listStorage.setText(suit.getText());});

        back.setOnAction(event -> {
            Main main = new Main();
            try {
                System.out.println("Right");
                main.callWindow("filesGUI/Menu.fxml", "MyStorage - Меню", 800, 600);
            } catch (Exception e) {
            } back.getScene().getWindow().hide();
        });
        add.setOnAction(event -> {
            Main main = new Main();
            reportConstructor rep = new reportConstructor();
            double pr;
            String prF = price.getText().trim();
            try{
                if ((prF.toLowerCase().contains(","))) prF = price.getText().replaceAll(",",".");
                pr = Double.parseDouble(prF);
                if(!nameStorage.equals("non") && !nameProd.getText().isEmpty() && !articProd.getText().isEmpty()) {
                    main.createProd(nameStorage, nameProd.getText(), articProd.getText(), pr);

                    // Строка заполнения отчета о покупках:
                    rep.reportBuy(articProd.getText(), nameStorage, nameProd.getText(), pr);
                }
                else JOptionPane.showMessageDialog(null, "Заполните все поля!");
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Похоже, информация в поле стоимости некорректна!");} catch (IOException e) {

            }
        });

    }
}
