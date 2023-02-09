package com.techelevator;

import java.math.BigDecimal;

public class Transactions {
    //this class will keep track of balance of the vending machine's money
   private BigDecimal currentBalance = new BigDecimal(0.00);


    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void deposit(BigDecimal deposit){
        this.currentBalance=currentBalance.add(deposit);

    }

    public void purchase(BigDecimal purchase){
        this.currentBalance=currentBalance.subtract(purchase);

    }
}
