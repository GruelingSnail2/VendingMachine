package com.techelevator;

import java.math.BigDecimal;
import java.math.MathContext;

public class Transactions {
    //this class keeps track of all the money going into and out of the machine
    private BigDecimal currentBalance = new BigDecimal(0.00);


    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void deposit(BigDecimal deposit) { //used to add money to current balance when user feeds money
        this.currentBalance = currentBalance.add(deposit);
    }

    public void purchase(BigDecimal purchase) { //used to subtract money to current balance when user buys something
        this.currentBalance = currentBalance.subtract(purchase);
    }

    public BigDecimal[] change() { //gives out change in quarters, nickels, and dimes when user is finished with their purchase
        MathContext scale = new MathContext(2);
        BigDecimal quarter = new BigDecimal(0.25, scale);
        BigDecimal dime = new BigDecimal(0.10, scale);
        BigDecimal nickel = new BigDecimal(0.05, scale);
        BigDecimal[] coins = {quarter,dime,nickel};
        BigDecimal[] change = new BigDecimal[3];
        BigDecimal[] changeMath = new BigDecimal[2];

        for(int i =0; i<change.length; i++){
            changeMath = currentBalance.divideAndRemainder(coins[i]);
            change[i] = changeMath[0];
            currentBalance = changeMath[1];
        }

        return change;
    }
}
