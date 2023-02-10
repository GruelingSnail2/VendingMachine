package com.techelevator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class VendingMachineCLI {

    private Display display = new Display();
    private FileInput secondScreen = new FileInput();
    private List<Item> listOfItems = secondScreen.startupInventory();
    private Transactions transactions = new Transactions();
    private UserInput userInput = new UserInput();

    public void run() {
        display.firstScreen();
        String userInputResponse = userInput.customerSelection();
        firstScreen(userInputResponse);
    }

    public static void main(String[] args) {
        VendingMachineCLI cli = new VendingMachineCLI();
        cli.run();
    }

    private void firstScreen(String userInputResponse) {            //first screen from readme. Need make loop if value other than 1,2,3 is selected
        boolean isOnFirstScreen = true;
        while (isOnFirstScreen) {
            if (userInputResponse.equals("1")) {  //this is working correctly
                for (Item item : listOfItems) {
                    System.out.printf("%s) %s: $%s - Remaining: %s\n", item.getSlotLocation(), item.getName(), item.getPrice(), item.getInventory());
                }
                System.out.println("");
                System.out.println("");
                display.firstScreen();
                userInputResponse = userInput.customerSelection();
            } else if (userInputResponse.equals("2")) {  //this is working correctly
                isOnFirstScreen = false;
            } else if (userInputResponse.equals("3")) {  //this is working
                System.out.println("Thank you, come again :)");
                secondScreen.endSession();
                System.exit(0);
            } else if (userInputResponse.equals("4")) {  //this is working
                BigDecimal totalSales = BigDecimal.ZERO;
                for (Item item : listOfItems) {
                    secondScreen.salesReport(item.getName(), 5 - item.getInventory());
                    BigDecimal itemsSold = new BigDecimal(5 - item.getInventory());
                    totalSales = totalSales.add(item.getPrice().multiply(itemsSold));
                }
                secondScreen.totalSales(totalSales.setScale(2));
                userInputResponse=userInput.customerSelection();
            }
        }
        System.out.println("Current Money Provided: " + transactions.getCurrentBalance());
        secondScreen();
    }

    private void secondScreen() {
        display.purchaseScreen();
        String purchaseResponse = userInput.customerSelection();
        boolean isOnSecondScreen = true;
        while(isOnSecondScreen) {
            if (purchaseResponse.equals("1")) {
                feedMoney();
            } else if (purchaseResponse.equals("2")) {
                buySomething();

            } else if (purchaseResponse.equals("3")) {
                endSession();
                isOnSecondScreen=false;
            }
        }
        display.firstScreen();
        purchaseResponse= userInput.customerSelection();
        firstScreen(purchaseResponse);
    }

    private void feedMoney(){
        String continueAddingMoney = "";
        while (!continueAddingMoney.equals("N")) {  //will run until user enters "n" or "N"
            System.out.println("Current Money Provided: $" + transactions.getCurrentBalance().setScale(2));
            display.feedMoneyScreen();
            BigDecimal moneyIn = userInput.moneyInput();
            transactions.deposit(moneyIn);
            System.out.println("Current Money Provided: $" + transactions.getCurrentBalance().setScale(2));
            display.moreMoneyScreen();
            continueAddingMoney = userInput.moreMoneyInput();
            secondScreen.log("FEED MONEY", moneyIn, transactions.getCurrentBalance());
        }
        System.out.println("You have entered: $" + transactions.getCurrentBalance().setScale(2));
    }

    private void buySomething(){
        for (Item item : listOfItems) {                         //prints list of items with info
            System.out.printf("%s) %s: $%s - remaining: %s \n", item.getSlotLocation(), item.getName(), item.getPrice(), (item.getInventory() == 0) ? "SOLD OUT" : item.getInventory());
        }
        System.out.println("");
        System.out.println("Please select item alphanumeric location");
        String purchaseChoice = userInput.itemChoice();         //user choice
        for (Item item : listOfItems) {                         //will compare all items in list's slot location to one input by user
            if (item.getSlotLocation().equals(purchaseChoice)) {
                if (item.getPrice().compareTo(transactions.getCurrentBalance()) > 0) {
                    System.out.println("Please add more money");
                } else if (item.getInventory() < 1) {
                    System.out.println("SOLD OUT");
                } else {
                    System.out.printf("%s costs %s: %s balance remaining\n", item.getName(), item.getPrice(), transactions.getCurrentBalance().subtract(item.getPrice()));
                    transactions.purchase(item.getPrice());
                    item.itemSale();
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

    private void endSession(){
        String[] changeName = {"quarters", "dimes", "nickels"};
        BigDecimal changeToGive = transactions.getCurrentBalance();
        BigDecimal[] change = transactions.change();
        for (int i = 0; i < 3; i++) {
            System.out.println("Your change is: " + change[i].setScale(0) + " " + changeName[i]);
        }
        secondScreen.log("GIVE CHANGE", changeToGive, transactions.getCurrentBalance());
    }
}

