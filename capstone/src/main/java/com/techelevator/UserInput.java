package com.techelevator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInput {

    //this class deals with any user interactions (input or output)

    public String customerSelection() { //used for first two screens
        Scanner userInput = new Scanner(System.in);
        String customerResponse = userInput.nextLine();

        if (customerResponse.equals("1") || customerResponse.equals("2") || customerResponse.equals("3")) {
            return customerResponse;
        } else {
            System.out.println("Error: Please enter (1), (2), or (3)");
        }
        return customerResponse;
    }

    public BigDecimal moneyInput() { //lets user add money to balance
        Scanner userInput = new Scanner(System.in);
        String customerResponse = userInput.nextLine();
        BigDecimal five = BigDecimal.valueOf(5.00);
        BigDecimal twenty = BigDecimal.valueOf(20.00);
        switch (customerResponse) {
            case "1":
                return BigDecimal.ONE;
            case "5":
                return five;
            case "10":
                return BigDecimal.TEN;
            case "20":
                return twenty;
            default:
                System.out.println("Please enter in a number/valid response");
                return BigDecimal.ZERO;
        }
    }

    public String moreMoneyInput() { //asks if user wants to add more money
        Scanner userInput = new Scanner(System.in);
        String customerResponse = userInput.nextLine();
        if (customerResponse.equals("Y") || customerResponse.equals("y")) {
            return "Y";
        } else if (customerResponse.equals("N") || customerResponse.equals("n")) {
            return "N";
        } else {
            System.out.println("Please give a valid response");
            return "A";
        }
    }

    public String itemChoice() { //lets user choose snack
        Scanner userInput = new Scanner(System.in);
        String customerResponse = userInput.nextLine();
        List<String> goodResponses = new ArrayList<>();
        goodResponses.add("A1");
        goodResponses.add("A2");
        goodResponses.add("A3");
        goodResponses.add("A4");
        goodResponses.add("B1");
        goodResponses.add("B2");
        goodResponses.add("B3");
        goodResponses.add("B4");
        goodResponses.add("C1");
        goodResponses.add("C2");
        goodResponses.add("C3");
        goodResponses.add("C4");
        goodResponses.add("D1");
        goodResponses.add("D2");
        goodResponses.add("D3");
        goodResponses.add("D4");

        if (!goodResponses.contains(customerResponse)) {
            System.out.println("Item does not exist");
            return "";
        } else {
            return customerResponse;
        }
    }

    public void firstScreen() {
        System.out.println("(1) Display Vending Machine Items");
        System.out.println("(2) Purchase");
        System.out.println("(3) Exit");
    }

    public void purchaseScreen() {

        System.out.println("");

        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");

    }

    public void feedMoneyScreen() {
        System.out.println("Enter bill amount: 1, 5, 10, or 20");
    }

    public void moreMoneyScreen() {
        System.out.println("Would you like to add another bill? (Y/N)");
    }


}




