package com.company;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;

public class sellProd {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button sellButton;

    @FXML
    private AnchorPane generalwin;

    @FXML
    private Button exitButton;

    @FXML
    private TextField price;

    @FXML
    private TextField count;

    @FXML
    void initialize() {
        reportConstructor rep = new reportConstructor();
        Main main = new Main();
        exitButton.setOnAction(event -> {
            try{ main.callWindow("filesGUI/StorageView.fxml", "MyStorage - Просмотр склада", 800,600);
                exitButton.getScene().getWindow().hide(); } catch (IOException e) { } catch (Exception e) { }
        });

        sellButton.setOnAction(event -> {
        double pr;
        double pr2;
        String prF = price.getText().trim();
        String prF2 = count.getText().trim();
        try{
            if ((prF.toLowerCase().contains(",") || prF2.toLowerCase().contains(","))) prF = price.getText().replaceAll(",",".");
            prF2 = count.getText().replaceAll(",",".");

            pr = Double.parseDouble(prF);
            pr2 = Double.parseDouble(prF2);
            if(!price.getText().isEmpty() || !count.getText().isEmpty()) {
                // Место для вставки строки в отчет о продаже!
                StorageView sv = new StorageView(); // itemName поле - название файла, itemPath - путь к файлу
                String file = sv.itemPath.toLowerCase()+sv.itemName.toLowerCase();

                JSONParser parser = new JSONParser();
                Object obj = null;
                try {
                    obj = parser.parse(new FileReader(file));
                } catch (IOException e) {
                } catch (ParseException e) {
                }
                JSONObject jsonObject = (JSONObject) obj;
                JOptionPane.showMessageDialog(null, "Продажа совершена успешно!");
                rep.reportSell(jsonObject.get("articul_prod").toString(), jsonObject.get("storage_name").toString(), jsonObject.get("product_name").toString(),pr, pr2);
            }
            else JOptionPane.showMessageDialog(null, "Заполните все поля!");
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "При заполнении указывайте только числовые значения и заполните все поля!!");} catch (IOException e) {
            e.printStackTrace();
        }
        });
    }
}
