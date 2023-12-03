package org.patterns.example;

import java.util.List;

@SuppressWarnings("StringConcatenationInLoop")
public class Customer {
    private final String name;
    private final List<Rental> rentals;

    public Customer(String name, List<Rental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }


    public String getName() {
        return name;
    }

    public String statement() {
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        for (Rental each : rentals) {
            double thisAmount = each.getAmount();
            frequentRenterPoints += each.getBonus();
            //show figures for this rental
            result.append("\t").append(each.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }
        //add footer lines
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }


}