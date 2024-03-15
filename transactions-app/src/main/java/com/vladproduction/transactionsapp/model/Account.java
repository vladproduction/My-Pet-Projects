package com.vladproduction.transactionsapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

/**
 * Created by vladproduction on 15-Mar-24
 */

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long accountNumber;
    private double balance;
    private String owner;
    private String last_operation;

    public Account(){
        this.owner = "N/A";
        this.accountNumber = 0;
        this.balance = 0.0;
    }

    public Account(long accountNumber, double balance, String owner) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLast_operation() {
        return last_operation;
    }

    public void setLast_operation(String last_operation) {
        this.last_operation = last_operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountNumber == account.accountNumber && Double.compare(balance, account.balance) == 0 && Objects.equals(id, account.id) && Objects.equals(owner, account.owner) && Objects.equals(last_operation, account.last_operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, balance, owner, last_operation);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", owner='" + owner + '\'' +
                ", last_operation='" + last_operation + '\'' +
                '}';
    }
}
