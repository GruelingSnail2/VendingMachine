package com.techelevator;

import java.math.BigDecimal;

public class Item {
    //this class is the blueprint for the item object. it holds the various attributes of the item and lets us access them as needed.
    //attributes of item
    private String name;
    private BigDecimal price;
    private String slotLocation;
    private String type;
    private int inventory = 5;



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

    public String getType() {
        return type;
    }

    public int getInventory() {

        return inventory;}



    //setters
    //these may be removed later but maybe not not sure
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public void setInventory(int inventory){
        this.inventory=inventory;
    }
    public void itemSale(){
        inventory= inventory-1;
    }

    //constructor
    public Item(String name, BigDecimal cost, String slotLocation, String type){
        this.name=name;
        this.price =cost;
        this.slotLocation=slotLocation;
        this.type = type;
    }




}
