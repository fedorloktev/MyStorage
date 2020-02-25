package com.company;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javax.swing.*;

public class StorageView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private AnchorPane generalwin;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private Button move;

    @FXML
    private Button sell;

    @FXML
    private Text nameStorage;
    public static String itemName;
    public static String itemPath;
    @FXML
    private Button look;
    private ListView<String> list;
    String s;
    String sL;
    String l2;
    String c;
    int l;
    @FXML

    void initialize() throws Exception{
        Menu menu = new Menu();
        Main main = new Main();
        System.out.println(menu.nameH1);
        nameStorage.setText(menu.nameH1);

        this.list = new ListView();
        this.list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.list.setMaxSize(400, 350);
        this.list.setLayoutX(64.0D);
        this.list.setLayoutY(182.0D);
        this.list.setOpacity(0.43);
        this.generalwin.getChildren().addAll(new Node[]{this.list});

        String fileSeparator = System.getProperty("file.separator");
        String fold="";
        if (menu.nameH1.equals("Вооружение")) fold = "Вооружение";
        else if (menu.nameH1.equals("Аммуниция")) fold = "Аммуниция";
        else if (menu.nameH1.equals("Аксессуары")) fold = "Аксессуары";
        else if (menu.nameH1.equals("Одежда")) fold = "Одежда";
        main.setUrlPath();
        File places = new File(main.urlPath+fileSeparator+fold);
        if (!places.exists()) {JOptionPane.showMessageDialog(null,"На складе пока нет товаров!");
            main.callWindow("filesGUI/Menu.fxml", "MyStorage - Меню", 800, 600);
            generalwin.getScene().getWindow().hide();}
        else {
            File[] files = places.listFiles();
////
            if (files.length <= 0) {
                JOptionPane.showMessageDialog(null, "На складе пока нет товаров!");
                try {
                    System.out.println("Right");
                    main.callWindow("filesGUI/Menu.fxml", "MyStorage - Меню", 800, 600);
                    generalwin.getScene().getWindow().hide();
                } catch (Exception e) {
                    generalwin.getScene().getWindow().hide();
                }

            }
            if (places.exists() && files.length > 0) {
                for (int i = 0; i < files.length; ++i) {
                    if (files[i].isFile()) {
                        this.s = files[i].getName();
                        String s1 = this.s;
                        s1 = s1.substring(0, s1.indexOf(46));
                        this.list.getItems().add(s1);
                    }
                }
            }
        }

        // Обработка клавиши просмотра
        look.setOnAction(event -> {
//            this.list.getSelectionModel().getSelectedItems();
            String itemList = "";
            itemList = this.list.getSelectionModel().getSelectedItem();
            System.out.println(itemList+".json");
            itemName = itemList + ".json";
            itemPath = places.toString()+fileSeparator;
            try {
                main.callWindow("filesGUI/viewProd.fxml", "MyStorage - Просмотр товара", 598,526);
            } catch (Exception e) { }
        });

        back.setOnAction(event -> {
            try {
                main.callWindow("filesGUI/Menu.fxml", "MyStorage - Меню", 800, 600);
            } catch (Exception e) {
            } back.getScene().getWindow().hide();
        });

        move.setOnAction(event -> {

            String itemList = "";
            itemList = this.list.getSelectionModel().getSelectedItem();

            itemName = itemList + ".json";
            itemPath = places.toString()+fileSeparator;

            try {
                main.callWindow("filesGUI/changeStorage.fxml", "MyStorage - Изменение склада", 598, 307);
            move.getScene().getWindow().hide();} catch (Exception e) {
            }

        });

        sell.setOnAction(event -> {
            String itemList = "";
            itemList = this.list.getSelectionModel().getSelectedItem();
            System.out.println(itemList+".json");
            itemName = itemList + ".json";
            itemPath = places.toString()+fileSeparator;
            try {
                main.callWindow("filesGUI/sellProd.fxml", "MyStorage - Продажа товара", 598, 307);
                sell.getScene().getWindow().hide();} catch (Exception e) {
            }
        });
    }
}
