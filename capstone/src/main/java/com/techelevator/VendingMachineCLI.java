package com.techelevator;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineCLI {

    private Display display = new Display();
    private FileInput secondScreen = new FileInput();
    private List<Item> listOfItems = secondScreen.startupInventory();
    private Transactions transactions = new Transactions();
    private UserInput userInput = new UserInput();
    private String firstScreenUserInput = "1";

    public void run() {
        display.firstScreen();                                          //need to make loop if 1,2,3 not given
        String userInputResponse = userInput.customerSelection();       //asks for user input
        while (userInputResponse.equals("1") && firstScreenUserInput.equals("1")) { //will loop until leave first screen. second condition makes it work
            firstScreen(userInputResponse);

        }
        while (firstScreenUserInput.equals("2")||userInputResponse.equals("2")) {              //will loop until leave purchase screen
            secondScreen();
        }
    }

    public static void main(String[] args) {
        VendingMachineCLI cli = new VendingMachineCLI();
        cli.run();
    }

    private void firstScreen(String userInputResponse) {            //first screen from readme. Need make loop if value other than 1,2,3 is selected
        if (userInputResponse.equals("1")) {
            for (Item item : listOfItems) {
                System.out.printf("%s) %s: $%s - %s left\n", item.getSlotLocation(), item.getName(), item.getPrice(), item.getInventory());
            }
        } else if (userInputResponse.equals("2")) {
            System.out.println("Current Money Provided: " + transactions.getCurrentBalance());
            display.purchaseScreen();

        } else if (userInputResponse.equals("3")) {
            System.out.println("Thank you, come again :)");
            System.exit(0);
        }
        System.out.println("");
        System.out.println("");
        display.firstScreen();
        this.firstScreenUserInput = userInput.customerSelection();  //changes attribute to force break out of first while loop
    }

    private void secondScreen() {            //purchase screen
        display.purchaseScreen();
        String purchaseResponse = userInput.customerSelection();
        if (purchaseResponse.equals("1")) {                         //will let us add money. This one is working
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
        } else if (purchaseResponse.equals("2")) {                  //will send to screen with list of items and ask for items location to choose item.
                                                                    //This is where we can add inventory to track, give message depending on type, and log transactions

            for (Item item : listOfItems) {                         //prints list of items with info
                System.out.printf("%s) %s: $%s - remaining: %s \n", item.getSlotLocation(), item.getName(), item.getPrice(), (item.getInventory()==0)?"SOLD OUT":item.getInventory());
            }
            System.out.println("");
            System.out.println("Please select item alphanumeric location");
            String purchaseChoice = userInput.itemChoice();         //user choice
            for (Item item : listOfItems) {                         //will compare all items in list's slot location to one input by user
                if (item.getSlotLocation().equals(purchaseChoice)) {
                    if (item.getPrice().compareTo(transactions.getCurrentBalance()) > 0) {
                        System.out.println("Please add more money");
                    } else if(item.getInventory()<1){
                        System.out.println("SOLD OUT");
                    }

                    else {
                        System.out.printf("%s costs %s: %s balance remaining\n", item.getName(), item.getPrice(), transactions.getCurrentBalance().subtract(item.getPrice()));
                        transactions.purchase(item.getPrice());
                        item.itemSale();
                        if (item.getSlotLocation().startsWith("A")){
                            System.out.println("Crunch Crunch, Yum!");

                        }else if(item.getSlotLocation().startsWith("B")){
                            System.out.println("Munch Munch, Yum!");
                        }else if(item.getSlotLocation().startsWith("C")){
                            System.out.println("Glug Glug, Yum!");
                        }else{
                            System.out.println("Chew Chew, Yum!");
                        }

                    }
                }
            }
        } else if (purchaseResponse.equals("3")) {
            //this option sets balance to 0, calculates change to give, and return to first screen
            String[] changeName = {"quarters","dimes","nickels"};
            BigDecimal[] change = transactions.change();
            for(int i =0; i<3;i++){

                System.out.println("Your change is: "+ change[i]+" "+changeName[i]);
            }
            firstScreen("");
        }
    }
}
