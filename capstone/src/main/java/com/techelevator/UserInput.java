package com.techelevator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserInput {

    public String customerSelection() {
        Scanner userInput = new Scanner(System.in);
        String customerResponse = userInput.nextLine();
        boolean customerResponseValid = true;

        if (customerResponse.equals("1") || customerResponse.equals("2") || customerResponse.equals("3")) {
            customerResponseValid = true;
        } else {
            customerResponseValid = false;
            System.out.println("Error: Please enter (1), (2), or (3)");
        }
        return customerResponse;

    }

    public BigDecimal moneyInput() {
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
                return BigDecimal.ZERO;
        }
    }

    public String moreMoneyInput() {
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

    public String itemChoice() {
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
            System.out.println(customerResponse);
            return customerResponse;
        }

    }



}




