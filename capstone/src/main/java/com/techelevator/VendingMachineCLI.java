package com.techelevator;

public class VendingMachineCLI {

	public void run() {
		// entry point for the vending
		Display display = new Display();
		display.firstScreen();
		FileInput secondScreen = new FileInput();
		secondScreen.startupInventory();
	}

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}
}
