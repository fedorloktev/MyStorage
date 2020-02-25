package com.company;


import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class reportConstructor {
    // В данном классе будут содержаться методы для выведения репортов в формате HTML
    // ***************
    //****************
    public void Definitions() throws IOException {
        String fileSeparator = System.getProperty("file.separator");
        File foldJsonProd = new File("ProductsMyStorage");
        if (!foldJsonProd.exists()) { foldJsonProd.mkdir(); }
//        FileWriter fwBuy = new FileWriter(foldJsonProd+"reportBUY.html", true);
//        FileWriter fwSell = new FileWriter(foldJsonProd+"reportSELL.html", true);
        FileWriter fwMove = new FileWriter(foldJsonProd+"reportMOVE.html", true);

//    Шапка для файла не востребована, ее заменяет поясняющая кнопка
//        FileWriter repBuy = new FileWriter(foldJsonProd+fileSeparator+"reportBUY.html", true);
//
//
//        repBuy.write("<center><h1> <b> ОТЧЕТ О ЗАКУПКАХ ТОВАРА </b> </h1></center>");
//        repBuy.write("<center><h2> <b> Общая сводка </b> </h2></center>");
//
//        repBuy.flush(); repBuy.close();
//        fwSell.flush(); fwSell.close();
        fwMove.flush(); fwMove.close();
    }

    public void reportBuy(String articul, String storageName, String prodName, double startPrice) throws IOException {
        String fileSeparator = System.getProperty("file.separator");
        File foldJsonProd = new File("ProductsMyStorage");
        Date date = new Date();

        FileWriter repBuy2 = new FileWriter(foldJsonProd+fileSeparator+"reportBUY.html", true);
        repBuy2.write("<h5><u>Дата завоза:</u> "+date.toString());
        repBuy2.write("  ||  <u>Артикул:</u> "+articul);
        repBuy2.write("             ||  <u>Склад:</u> "+storageName);
        repBuy2.write("             ||  <u>Наименование:</u> "+prodName);
        repBuy2.write("             ||  <u>Закупочная цена:</u> "+startPrice);
        repBuy2.write("             ||  <u>Количество:</u> "+" --- ");

        repBuy2.flush();repBuy2.close();
    }

    // Метод вывозда в HTML отчета о продажах
    public void reportSell(String articul, String storageName, String prodName, double sellPrice, double count) throws IOException{
        String fileSeparator = System.getProperty("file.separator");
        File foldJsonProd = new File("ProductsMyStorage");
        Date date = new Date();

        FileWriter repBuy2 = new FileWriter(foldJsonProd+fileSeparator+"reportSELL.html", true);
        repBuy2.write("<h5><u>Дата продажи:</u> "+date.toString());
        repBuy2.write("  ||  <u>Артикул:</u> "+articul);
        repBuy2.write("             ||  <u>Склад:</u> "+storageName);
        repBuy2.write("             ||  <u>Наименование:</u> "+prodName);
        repBuy2.write("             ||  <u>Цена продажи:</u> "+sellPrice);
        repBuy2.write("             ||  <u>Количество:</u> "+"--/"+count);

        repBuy2.flush();repBuy2.close();
    }

    // Метод для отчета изменения склада
    public void reportChange(String articul, String prodName, String storageName, String storageName2) throws IOException {
        String fileSeparator = System.getProperty("file.separator");
        File foldJsonProd = new File("ProductsMyStorage");
        Date date = new Date();

        FileWriter repBuy3 = new FileWriter(foldJsonProd+fileSeparator+"reportCHEN.html", true);
        repBuy3.write("<h5><u>Дата перемещения:</u> "+date.toString());
        repBuy3.write("  ||  <u>Артикул:</u> "+articul);
        repBuy3.write("             ||  <u>Наименование:</u> "+prodName);
        repBuy3.write("             ||  <u>Текущий склад:</u> "+storageName);
        repBuy3.write("             ||  <u>Целевой склад:</u> "+storageName2);

        repBuy3.flush();repBuy3.close();
    }
}