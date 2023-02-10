package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
                listOfItems.add(new Item(productName,priceOutput,slotLocation,type));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listOfItems;
    }

    public void log(){
        String fileName = "log.txt";
        File logFile = new File(fileName);
        try(FileOutputStream out = new FileOutputStream(logFile,true)){
            PrintWriter writer = new PrintWriter(out){
                writer.printf("%s%s%s")
            }
        }


    }


}
