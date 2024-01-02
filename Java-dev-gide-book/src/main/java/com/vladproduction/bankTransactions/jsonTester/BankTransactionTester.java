package com.vladproduction.bankTransactions.jsonTester;

import java.util.Objects;

public class BankTransactionTester {

    private String date;
    private double amount;
    private String description;

    public BankTransactionTester() {

    }

    public BankTransactionTester(String date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[ " + date +
                ", " + amount +
                ", " + description + " ]";
    }

    @Override
    public boolean equals(Object object){
        if(this == object){
            return true;
        }
        if(object == null || object.getClass() != object.getClass()){
            return false;
        }
        BankTransactionTester that = (BankTransactionTester) object;
        return Double.compare(that.amount, amount) == 0 &&
                date.equals(that.date) &&
                description.equals(that.description);

    }

    @Override
    public int hashCode(){
        return Objects.hash(date, amount, description);
    }
}
