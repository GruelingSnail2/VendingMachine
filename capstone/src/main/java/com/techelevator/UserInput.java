package com.techelevator;

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

    public String moneyInput() {
        Scanner userInput = new Scanner(System.in);
        String customerResponse = userInput.nextLine();
        switch (customerResponse) {
            case "1":

                return "1";

            case "5":
                return "5";
            case "10":
                return "10";

            case "20":
                return "20";

            default:
                return "0";
        }
    }

    public String moreMoneyInput(){
        Scanner userInput = new Scanner(System.in);
        String customerResponse = userInput.nextLine();
        if(customerResponse.equals("Y") || customerResponse.equals("y")){
            return "Y";
        } else if (customerResponse.equals("N")||customerResponse.equals("n")){
            return "N";
        } else {
            System.out.println("Please give a valid response");
            return "A";
        }
    }



}




