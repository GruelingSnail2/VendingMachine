package com.techelevator;

public class Display {

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

