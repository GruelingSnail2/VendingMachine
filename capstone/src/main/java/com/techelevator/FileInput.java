package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileInput {

    public List<Item> startupInventory() {
        //input file for step 3
        String filename = "C:\\Users\\Student\\Workspace\\capstone-1-team-7\\capstone\\vendingmachine.csv";
        File inputFile = new File(filename);
        List<Item> listOfItems = new ArrayList<>();
        try (Scanner inputStream = new Scanner(inputFile)) {
            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine();
                String[] lineArr = line.split("\\|");
                String slotLocation = lineArr[0];
                String productName = lineArr[1];
                BigDecimal priceInput = new BigDecimal(Double.parseDouble(lineArr[2]));
                BigDecimal priceOutput = priceInput.setScale(2, RoundingMode.HALF_UP);
                String type = lineArr[3];
                listOfItems.add(new Item(productName, priceOutput, slotLocation, type));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listOfItems;
    }

    public void log(String item,BigDecimal input, BigDecimal balance) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a");
        String text = date.format(formatter);
        String fileName = "log.txt";
        File logFile = new File(fileName);
        try (FileOutputStream out = new FileOutputStream(logFile, true); PrintWriter writer = new PrintWriter(out,true)) {
             writer.printf(" %s %s $%s $%s\n",text,item,input,balance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endSession(){
        String fileName = "log.txt";
        File logFile = new File(fileName);
        try (FileOutputStream out = new FileOutputStream(logFile, true); PrintWriter writer = new PrintWriter(out,true)) {
            writer.println("**********************************************");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salesReport(String name,int numSold) {
//        LocalDateTime date = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a");
//        String text = date.format(formatter);
        String fileName = "salesreport.txt";
        File sales= new File(fileName);
        try (FileOutputStream out = new FileOutputStream(sales, true); PrintWriter writer = new PrintWriter(out,true)) {
            writer.printf(" %s|%d\n",name,numSold);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void totalSales(BigDecimal totalSales){
        String fileName = "salesreport.txt";
        File sales= new File(fileName);
        try (FileOutputStream out = new FileOutputStream(sales, true); PrintWriter writer = new PrintWriter(out,true)) {
            writer.println("");
            writer.printf(" **TOTAL SALES** $%s\n",totalSales.setScale(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
