package com.techelevator;

import com.sun.source.tree.UsesTree;

import java.math.BigDecimal;
import java.math.MathContext;

public class Transactions {
    //this class will keep track of balance of the vending machine's money
    private BigDecimal currentBalance = new BigDecimal(0.00);


    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void deposit(BigDecimal deposit) {
        this.currentBalance = currentBalance.add(deposit);

    }

    public void purchase(BigDecimal purchase) {
        this.currentBalance = currentBalance.subtract(purchase);

    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public BigDecimal[] change() {
        MathContext scale = new MathContext(2);
        BigDecimal quarter = new BigDecimal(0.25, scale);
        BigDecimal dime = new BigDecimal(0.10, scale);
        BigDecimal nickel = new BigDecimal(0.05, scale);

        BigDecimal[] change = new BigDecimal[3];
        BigDecimal[] idunno = new BigDecimal[2];
        idunno = currentBalance.divideAndRemainder(quarter);

        change[0] = idunno[0];
        currentBalance = idunno[1];

        idunno = currentBalance.divideAndRemainder(dime);

        change[1] = idunno[0];
        currentBalance = idunno[1];
        idunno = currentBalance.divideAndRemainder(nickel);

        change[2] = idunno[0];
        setCurrentBalance(BigDecimal.ZERO);

        return change;
    }
}
