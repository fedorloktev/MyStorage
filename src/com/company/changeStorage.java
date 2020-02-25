package com.company;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;

public class changeStorage {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane generalwin;

    @FXML
    private MenuItem aksess2;

    @FXML
    private Text nowStor;

    @FXML
    private Button exitButton;

    @FXML
    private MenuItem gun2;

    @FXML
    private Button moveButton;

    @FXML
    private MenuItem ammo2;

    @FXML
    private MenuItem suit2;

    @FXML
    private MenuButton newStor;

    public static String nameStorageWas;
    public static String nameStorageWill;
    @FXML
    void initialize() {
        Main main = new Main();
        exitButton.setOnAction(event -> {
            try{ main.callWindow("filesGUI/StorageView.fxml", "MyStorage - Просмотр склада", 800,600);
                exitButton.getScene().getWindow().hide(); } catch (IOException e) { } catch (Exception e) { }
        });

        gun2.setOnAction(e -> {nameStorageWill = gun2.getText(); newStor.setText(gun2.getText());});
        ammo2.setOnAction(e -> {nameStorageWill = ammo2.getText(); newStor.setText(ammo2.getText());});
        aksess2.setOnAction(e -> {nameStorageWill = aksess2.getText();newStor.setText(aksess2.getText());});
        suit2.setOnAction(e -> {nameStorageWill = suit2.getText();newStor.setText(suit2.getText());});

        StorageView sv = new StorageView(); // itemName поле - название файла, itemPath - путь к файлу
        String file = sv.itemPath.toLowerCase()+sv.itemName.toLowerCase();
        System.out.println("Путь к файлу при первом заходе в переменную: "+file);
        // Парсин JSON файла товара
            JSONParser parser = new JSONParser();
            Object obj = null;
            try {
                obj = parser.parse(new FileReader(file));
            } catch (IOException e) {
            } catch (ParseException e) {
            }
            JSONObject jsonObject = (JSONObject) obj;
            // Текущий склад у товара
            nameStorageWas = jsonObject.get("storage_name").toString();
            nowStor.setText(nameStorageWas);


        moveButton.setOnAction(event -> {
            reportConstructor rep = new reportConstructor();
                if (nameStorageWas.equals(nameStorageWill))
                    JOptionPane.showMessageDialog(null, "Указаны одинаковые склады!");
                else {
                    File fileDel = new File(file);
                    System.gc();
                    fileDel.delete();
                    System.gc();
                // Замена склада товара
                    main.createProd(nameStorageWill,jsonObject.get("product_name").toString(),jsonObject.get("articul_prod").toString(),jsonObject.get("storage_price_buy").hashCode());
                   // Строка добавления отчета

                    try {
                        rep.reportChange(jsonObject.get("articul_prod").toString(), jsonObject.get("product_name").toString(), nameStorageWas.toString(), nameStorageWill.toString() );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Удаление старого
                    System.gc();
                    fileDel.delete();
                    System.gc();
                    System.out.println("Файл удалился по пути: "+fileDel.toString());
                    try{ main.callWindow("filesGUI/StorageView.fxml", "MyStorage - Просмотр склада", 800,600);
                        moveButton.getScene().getWindow().hide(); } catch (IOException e) { } catch (Exception e) {
                        System.out.println("Ошибка вывода");
                    }
                    moveButton.getScene().getWindow().hide();
                }


        });
    }


}
