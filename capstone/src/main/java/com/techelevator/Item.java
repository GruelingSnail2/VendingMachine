package com.techelevator;

import java.math.BigDecimal;

public class Item {
    private String name;
    private BigDecimal price;
    private String slotLocation;
    private String type;
    private int inventory = 5; //automatically sets inventory to 5 at machine startup


    //getters
    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public int getInventory() {
        return inventory;
    }

    //updates current inventory
    public void itemSale() {
        inventory = inventory - 1;
    }

    //constructor
    public Item(String name, BigDecimal cost, String slotLocation, String type) {
        this.name = name;
        this.price = cost;
        this.slotLocation = slotLocation;
        this.type = type;
    }


}
