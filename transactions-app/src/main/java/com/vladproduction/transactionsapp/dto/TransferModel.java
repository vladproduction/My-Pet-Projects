package com.vladproduction.transactionsapp.dto;

import com.vladproduction.transactionsapp.model.Account;

import java.util.Objects;

/**
 * Created by vladproduction on 15-Mar-24
 */

public class TransferModel {

    private Account from;
    private Account to;
    private double amount;


    public TransferModel(Account from, Account to, double amount) {
        super();
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferModel that = (TransferModel) o;
        return Double.compare(amount, that.amount) == 0 &&

                Objects.equals(from, that.from) &&
                Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, amount);
    }

    @Override
    public String toString() {
        return "TransferModel{" +
                "from=" + from +
                ", to=" + to +
                ", amount=" + amount +
                '}';
    }
}
