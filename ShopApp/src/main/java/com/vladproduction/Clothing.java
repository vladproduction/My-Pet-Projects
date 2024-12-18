package com.vladproduction;

public class Clothing implements Comparable<Clothing>{

    public static final double MIN_PRICE = 10.0;
    public static final double TAX_RATE = 0.2;
    private String description;
    private double price;
    private String size;

    public Clothing(String description, double price, String aSize) {
        this.description = description;
        this.price = price;
        size = aSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price + (price * TAX_RATE);
    }

    public void setPrice(double price) {
        if (price > MIN_PRICE) {
            this.price = price;
        } else {
            this.price = MIN_PRICE;
        }
//        this.price = (price > MIN_PRICE) ? price : MIN_PRICE;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Description = " + getDescription() + ", Size = " + getSize() + ", Price = " + getPrice();
    }

    @Override
    public int compareTo(Clothing o) {
        return this.description.compareTo(o.description);
    }
}
