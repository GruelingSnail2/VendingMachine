package com.techelevator;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineCLI {

    private FileInput secondScreen = new FileInput();
    private List<Item> listOfItems = secondScreen.startupInventory();
    private Transactions transactions = new Transactions();
    private UserInput userInput = new UserInput();

    public static void main(String[] args) {
        VendingMachineCLI cli = new VendingMachineCLI();
        cli.run();
    }

    public void run() { //entry point will send to here
        firstScreen();
    }

    private void firstScreen() { //first screen that is seen by user that lets them list the items for sale, purchase something, or exit the vending machine
        boolean isOnFirstScreen = true;
        while (isOnFirstScreen) { //loops on first screen until 3 is entered by user, signifying leaving the vending machine
            userInput.firstScreen();
            String userInputResponse = userInput.customerSelection();
            if (userInputResponse.equals("1")) {
                printListOfItems();
            } else if (userInputResponse.equals("2")) {
                secondScreen();
            } else if (userInputResponse.equals("3")) {
                isOnFirstScreen = false;
            } else if (userInputResponse.equals("4")) { //secret sales report option for owner of vending machine. prints to .txt file
                BigDecimal totalSales = BigDecimal.ZERO;
                for (Item item : listOfItems) {
                    secondScreen.salesReport(item.getName(), 5 - item.getInventory());
                    BigDecimal itemsSold = new BigDecimal(5 - item.getInventory());
                    totalSales = totalSales.add(item.getPrice().multiply(itemsSold));
                }
                secondScreen.totalSales(totalSales.setScale(2));
            }
        }
        System.out.println("Thank you, please come again soon :)");
    }

    private void secondScreen() {
        boolean isOnSecondScreen = true;
        while(isOnSecondScreen) {  //purchase screen. will loop until done, then will send back to first screen
            userInput.purchaseScreen();
            String purchaseResponse = userInput.customerSelection();
            if (purchaseResponse.equals("1")) {
                feedMoney();
            } else if (purchaseResponse.equals("2")) {
                buySomething();
            } else if (purchaseResponse.equals("3")) {
                endSession();
                isOnSecondScreen=false;
            }
        }
    }

    private void feedMoney(){  //allows user to add money to balance
        String continueAddingMoney = "";
        while (!continueAddingMoney.equals("N")) {  //loops until user indicates they no longer want to add money
            System.out.println("Current Money Provided: $" + transactions.getCurrentBalance().setScale(2));
            userInput.feedMoneyScreen();
            BigDecimal moneyIn = userInput.moneyInput();
            transactions.deposit(moneyIn);
            System.out.println("Current Money Provided: $" + transactions.getCurrentBalance().setScale(2));
            userInput.moreMoneyScreen();
            continueAddingMoney = userInput.moreMoneyInput();
            secondScreen.log("FEED MONEY", moneyIn, transactions.getCurrentBalance());
        }
        System.out.println("You have entered: $" + transactions.getCurrentBalance().setScale(2));
    }

    private void buySomething(){ //allows user to choose item to purchase
        printListOfItems();
        System.out.println("Please select item alphanumeric location");
        String purchaseChoice = userInput.itemChoice();
        for (Item item : listOfItems) { //loops through list of items and compares user input to the location for each item
            if (item.getSlotLocation().equals(purchaseChoice)) {
                if (item.getPrice().compareTo(transactions.getCurrentBalance()) > 0) { //checks if the user has added enough money to purchase the item
                    System.out.println("Please add more money");
                } else if (item.getInventory() < 1) {  //checks if machine has enough inventory to sell to user
                    System.out.println("SOLD OUT");
                } else {
                    System.out.printf("%s costs %s: %s balance remaining\n", item.getName(), item.getPrice(), transactions.getCurrentBalance().subtract(item.getPrice()));
                    transactions.purchase(item.getPrice());
                    item.itemSale();
                    //prints out appropriate message based on item type
                    if (item.getSlotLocation().startsWith("A")) {
                        System.out.println("Crunch Crunch, Yum!");
                    } else if (item.getSlotLocation().startsWith("B")) {
                        System.out.println("Munch Munch, Yum!");

                    } else if (item.getSlotLocation().startsWith("C")) {
                        System.out.println("Glug Glug, Yum!");
                    } else {
                        System.out.println("Chew Chew, Yum!");
                    }
                    secondScreen.log(item.getName(), item.getPrice(), transactions.getCurrentBalance());
                }
            }
        }
    }

    private void endSession(){  //will return change to user when they are done and will send them back to the first screen
        String[] changeName = {"quarters", "dimes", "nickels"};
        BigDecimal changeToGive = transactions.getCurrentBalance();
        BigDecimal[] change = transactions.change();
        for (int i = 0; i < 3; i++) {
            System.out.println("Your change is: " + change[i].setScale(0) + " " + changeName[i]);
        }
        secondScreen.log("GIVE CHANGE", changeToGive, transactions.getCurrentBalance());
    }

    private void printListOfItems(){  //used whenever printing list of items
        for (Item item : listOfItems) {
            System.out.printf("%s) %s: $%s - Remaining: %s\n", item.getSlotLocation(), item.getName(), item.getPrice(), item.getInventory());
        }
        System.out.println("");
        System.out.println("");
    }
}

