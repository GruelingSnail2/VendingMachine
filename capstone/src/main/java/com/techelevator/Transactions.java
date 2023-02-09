package com.techelevator;

import java.math.BigDecimal;

public class Transactions {
   private BigDecimal currentBalance = new BigDecimal(0.00);

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void deposit(BigDecimal deposit){
        this.currentBalance.add(deposit);
    }

    public void purchase(BigDecimal purchase){
        this.currentBalance.subtract(purchase);

    }
}
