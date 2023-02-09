package com.techelevator;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineCLI {

    private Display display = new Display();
    private FileInput secondScreen = new FileInput();
    private List<Item> listOfItems = secondScreen.startupInventory();
    private Transactions transactions = new Transactions();
    private UserInput userInput = new UserInput();

    public void run() {
        // entry point for the vending
        display.firstScreen();
        String userInputResponse = userInput.customerSelection(); //asks for user input
        while (userInputResponse.equals("1")) { //will loop until leave first screen
            firstScreen(userInputResponse);

        }
        while (userInputResponse.equals("2")) { //will loop until leave purchase screen
            secondScreen(userInputResponse);
        }
    }

    public static void main(String[] args) {
        VendingMachineCLI cli = new VendingMachineCLI();
        cli.run();
    }

    private void firstScreen(String userInputResponse) { //first screen from readme. I think this is done
        if (userInputResponse.equals("1")) {
            for (Item item : listOfItems) {
                System.out.printf("%s) %s: $%s - %s left\n", item.getSlotLocation(), item.getName(), item.getPrice(), item.getInventory());
            }
            System.out.println("");
            System.out.println("");
            display.firstScreen();
            userInputResponse = userInput.customerSelection();

        } else if (userInputResponse.equals("2")) {
            System.out.println("Current Money Provided: " + transactions.getCurrentBalance());
            display.purchaseScreen();


        } else if (userInputResponse.equals("3")) {
            System.out.println("Thank you, come again :)");
            System.exit(0);
        }
    }

    private void secondScreen(String purchaseResponse) { //purchase screen
        display.purchaseScreen();
        purchaseResponse= userInput.customerSelection();
        if (purchaseResponse.equals("1")) {  //will let us add money
            String continueAddingMoney = "";
            while (!continueAddingMoney.equals("N")) {
                System.out.println("Current Money Provided: " + transactions.getCurrentBalance());
                display.feedMoneyScreen();
                transactions.deposit(userInput.moneyInput());
                System.out.println("Current Money Provided: " + transactions.getCurrentBalance());
                display.moreMoneyScreen();
                continueAddingMoney = userInput.moreMoneyInput();
            }
            System.out.println("You have entered: $" + transactions.getCurrentBalance());
        } else if (purchaseResponse.equals("2")) {  //will send to screen with list of items and ask for items location to choose item
            System.out.println("Please select item alphanumeric location");
            for (Item item : listOfItems) {  //prints list of items with info
                System.out.printf("%s) %s: $%s - %s left\n", item.getSlotLocation(), item.getName(), item.getPrice(), item.getInventory());
            }
            String purchaseChoice = userInput.itemChoice();  //user choice
            for (Item item : listOfItems) { //will compare all items in list's slot location to one input by user
                if (item.getSlotLocation().equals(purchaseChoice)) {
                    if(item.getPrice().compareTo(transactions.getCurrentBalance())>0){
                        System.out.println("Please add more money");
                    } else {
                        System.out.printf("%s costs %s: %s balance remaining", item.getName(), item.getPrice(), transactions.getCurrentBalance().subtract(item.getPrice()));
                        transactions.purchase(item.getPrice());
                    }
                }
            }
        } else if (purchaseResponse.equals("3")){

        }
    }
}
