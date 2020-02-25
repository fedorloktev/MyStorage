package com.company;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;

import static com.company.Main.urlPath;
import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class Menu {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView gun;

    @FXML
    private ImageView move;

    @FXML
    private ImageView aksess;

    @FXML
    private ImageView ammo;

    @FXML
    private Button suitButton;

    @FXML
    private ImageView sell;

    @FXML
    private ImageView plus;

    @FXML
    private Button aksessButton;

    @FXML
    private Button ammoButton;

    @FXML
    private Button plusButton;

    @FXML
    private ImageView into;

    @FXML
    private Button gunButton;

    @FXML
    private Button docInto;

    @FXML
    private Button docSell;

    @FXML
    private Button docMove;

    @FXML
    private ImageView suit;
    public static String nameH1;
    @FXML
    void initialize() {
        // -> Обработка кнопок
        // кнопки складов
        gun.setOnMouseEntered(event -> { gunButton.setVisible(true);ammoButton.setVisible(false); aksessButton.setVisible(false); suitButton.setVisible(false); });
        gunButton.setOnMouseExited(event -> {gunButton.setVisible(false); ammoButton.setVisible(false); aksessButton.setVisible(false); suitButton.setVisible(false);});
        ammo.setOnMouseEntered(event -> { ammoButton.setVisible(true);gunButton.setVisible(false); aksessButton.setVisible(false); suitButton.setVisible(false);});
        ammoButton.setOnMouseExited(event -> {gunButton.setVisible(false); ammoButton.setVisible(false); aksessButton.setVisible(false); suitButton.setVisible(false);});
        aksess.setOnMouseEntered(event -> { aksessButton.setVisible(true);gunButton.setVisible(false); ammoButton.setVisible(false); suitButton.setVisible(false);});
        aksessButton.setOnMouseExited(event -> {gunButton.setVisible(false); ammoButton.setVisible(false); aksessButton.setVisible(false); suitButton.setVisible(false);});
        suit.setOnMouseEntered(event -> { suitButton.setVisible(true);gunButton.setVisible(false); ammoButton.setVisible(false); aksessButton.setVisible(false); });
        suitButton.setOnMouseExited(event -> {gunButton.setVisible(false); ammoButton.setVisible(false); aksessButton.setVisible(false); suitButton.setVisible(false);});
        // кнопка добавления товара
        plus.setOnMouseEntered(event -> plusButton.setVisible(true));
        plusButton.setOnMouseExited(event -> plusButton.setVisible(false));
        // кнопки отчетности
        into.setOnMouseEntered(event -> { docInto.setVisible(true);docMove.setVisible(false); docSell.setVisible(false);});
        docInto.setOnMouseExited(event -> {docInto.setVisible(false); docMove.setVisible(false); docSell.setVisible(false);});
        sell.setOnMouseEntered(event -> { docInto.setVisible(false);docMove.setVisible(false); docSell.setVisible(true);});
        docSell.setOnMouseExited(event -> {docInto.setVisible(false); docMove.setVisible(false); docSell.setVisible(false);});
        move.setOnMouseEntered(event -> { docInto.setVisible(false);docMove.setVisible(true); docSell.setVisible(false);});
        docMove.setOnMouseExited(event -> {docInto.setVisible(false); docMove.setVisible(false); docSell.setVisible(false);});
        // <- обработчик кнопок

        plusButton.setOnAction(event -> {
            try { selectAction(plusButton); } catch (Exception e) { }
        });
        gunButton.setOnAction(event -> { nameH1 = "Вооружение";
            try {selectAction(gunButton); } catch (Exception e) { }
        });
        aksessButton.setOnAction(event -> { nameH1 = "Аксессуары";
            try {selectAction(aksessButton); } catch (Exception e) { }
        });
        ammoButton.setOnAction(event -> { nameH1 = "Аммуниция";
            try {selectAction(ammoButton); } catch (Exception e) { }
        });
        suitButton.setOnAction(event -> { nameH1 = "Одежда";
            try {selectAction(suitButton); } catch (Exception e) { }
        });
        docInto.setOnAction(event -> {
            try { selectAction(docInto); } catch (Exception e) { }
        });
        docMove.setOnAction(event -> {
            try { selectAction(docMove); } catch (Exception e) { }
        });
        docSell.setOnAction(event -> {
            try { selectAction(docSell); } catch (Exception e) { }
        });
    }

    // Метод вызова окон
    void selectAction(Button b) throws Exception {
        Main main = new Main();
        StorageView sv = new StorageView();


    if (b==plusButton) {main.callWindow("filesGUI/plusProduct.fxml", "MyStorage - Добавление товара", 600,311);
                         b.getScene().getWindow().hide();}
    else if (b==gunButton || b==aksessButton || b==ammoButton || b==suitButton) {
       try{ main.callWindow("filesGUI/StorageView.fxml", "MyStorage - Просмотр склада", 800,600);
        b.getScene().getWindow().hide(); } catch (IOException e) { }
    }
    else if (b==docInto) {
        String file = main.setUrlPath();
        String fileSeparator = System.getProperty("file.separator");
        String url =  file+fileSeparator+"reportBUY.html";
        File file1 = new File(url);
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file1);
        }
    }
    else if (b==docMove) {
        String file = main.setUrlPath();
        String fileSeparator = System.getProperty("file.separator");
        String url =  file+fileSeparator+"reportCHEN.html";
        File file1 = new File(url);
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file1);
        }
    }
    else if (b == docSell) {
        String file = main.setUrlPath();
        String fileSeparator = System.getProperty("file.separator");
        String url =  file+fileSeparator+"reportSELL.html";
        File file1 = new File(url);
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file1);
        }
    }


}}
