package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;



public class viewProd {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane generalwin;

    @FXML
    private Button exitButton;

    @FXML
    private Text articleName;

    @FXML
    private Text prodName;

    @FXML
    private Text priceName;

    @FXML
    private Text storageName;

    @FXML
    void initialize() {
    StorageView sv = new StorageView(); // itemName поле - название файла, itemPath - путь к файлу
        String file = sv.itemPath+sv.itemName;


        try { // Парсин JSON файла товара
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) obj;

            prodName.setText(jsonObject.get("product_name").toString());
            articleName.setText(jsonObject.get("articul_prod").toString());
            priceName.setText(jsonObject.get("storage_price_buy").toString());
            storageName.setText(jsonObject.get("storage_name").toString());

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Файл не найден!");
        } catch (IOException e) {
        } catch (org.json.simple.parser.ParseException e) {}

        exitButton.setOnAction(event -> exitButton.getScene().getWindow().hide());
    }
}
