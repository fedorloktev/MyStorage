package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main extends Application {
public static String urlPath;
public static String nameS;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    Main main = new Main();
    main.callWindow("filesGUI/Authorization.fxml", "MyStorage - Авторизация", 600, 400);
    }

    // Метод для вызова стандартного окна.
    public void callWindow( String urlFXML, String nameTitle, int width, int height) throws Exception {
        try {
            Stage primaryStage = new Stage();
            Parent root = (Parent) FXMLLoader.load(getClass().getResource(urlFXML));
            primaryStage.setTitle(nameTitle);
            primaryStage.setScene(new Scene(root, width, height));
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ошибка открытия окна");
        }
    }

    // Метод добавления продукта
    public void createProd(String nameStorage, String nameProduct, String articleProd, double priceBuy) {
        String fileSeparator = System.getProperty("file.separator");

        File foldJsonProd = new File("ProductsMyStorage");
        if (!foldJsonProd.exists()){foldJsonProd.mkdir();}
        File foldJsonProdSt = new File("ProductsMyStorage"+fileSeparator+nameStorage);
        if (!foldJsonProdSt.exists()){foldJsonProdSt.mkdir();}

        String nameStor = nameStorage.trim();
        String nameProd = nameProduct.trim();
        String articProd = articleProd.trim();

        // Ограничение на спецсимволы
        if (nameProd.contains("\"") || nameStor.contains("\"") || articProd.contains("\"")) {
            JOptionPane.showMessageDialog(null, "Используйте символ 'текст' вместо кавычек!");
        }else{
        String nl = String.format("%n"); // Переход на новую строку универсальный

        File prodJson = new File(foldJsonProdSt+fileSeparator+nameProd+".json");
        try(FileWriter writer = new FileWriter(prodJson, false)) {
            writer.append("{");
            writer.append(nl);
            writer.write("   \"storage_name\" : "+"\""+nameStor+"\""+","+nl);
            writer.write("   \"articul_prod\" : "+"\""+articProd+"\""+","+nl);
            writer.write("   \"product_name\" : "+"\""+nameProduct+"\""+","+nl);
            writer.write("   \"storage_price_buy\" : "+"\""+priceBuy+"\""+","+nl);
            writer.write("   \"storage_price_sell\" : "+"\""+"NonPrice"+"\""+nl);
            writer.append("}");
            writer.flush();
            writer.close();

            JOptionPane.showMessageDialog(null, "Товар успешно добавлен на склад!");
        }catch(IOException ex){JOptionPane.showMessageDialog(null,"Товар не может быть добавлен");}
    }}


    String setUrlPath() {
        String fileSeparator = System.getProperty("file.separator");
        File foldJsonProdSt = new File("ProductsMyStorage");
//        System.out.println(nameS);
        urlPath = foldJsonProdSt.toString();
    return urlPath;}

}
