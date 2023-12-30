package com.vladproduction.bankTransactions.DusingInterface;

import java.time.LocalDate;
import java.util.Objects;

public class BankTransaction {
    private final LocalDate date;
    private final double amount;
    private final String description;

    public BankTransaction(LocalDate date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public LocalDate getDate() {
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
        BankTransaction that = (BankTransaction) object;
        return Double.compare(that.amount, amount) == 0 &&
                date.isEqual(that.date) &&
                description.equals(that.description);

    }

    @Override
    public int hashCode(){
        return Objects.hash(date, amount, description);
    }
}
