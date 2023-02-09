package com.techelevator;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineCLI {

	public void run() {
		// entry point for the vending
		Display display = new Display();
		display.firstScreen();
		FileInput secondScreen = new FileInput();
		List<Item> listOfItems= secondScreen.startupInventory();
		Transactions transactions = new Transactions();

		UserInput userInput = new UserInput();

		String userInputResponse = userInput.customerSelection();

		if(userInputResponse.equals("1")){
			for(Item item: listOfItems) {
				System.out.printf("%s) %s: $%s - %s left\n",item.getSlotLocation(),item.getName(),item.getPrice(),item.getInventory());
			}

		}else if(userInputResponse.equals("2")){
			System.out.println("Current Money Provided: "+transactions.getCurrentBalance());
			display.purchaseScreen();


		}else if(userInputResponse.equals("3")){
			System.out.println("Thank you, come again :)");
			System.exit(0);
		}



		String purchaseResponse = userInput.customerSelection();
		if(purchaseResponse.equals("1")){
			System.out.println("Current Money Provided: "+transactions.getCurrentBalance());
			display.feedMoneyScreen();

			BigDecimal moneyEntered = new BigDecimal(Double.parseDouble(userInput.moneyInput()));
			transactions.deposit(moneyEntered);
			display.moreMoneyScreen();

		}




	}

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}
}
