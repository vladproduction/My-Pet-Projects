package com.vladproduction.bankTransactions.CwithProcessor;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankProcessor {

    private List<BankTransaction> transactions;

    public BankProcessor(List<BankTransaction> transactions) {
        this.transactions = transactions;
    }

    public double calculateTotalTransactionsAmount() {
        double total = 0d;
        for (BankTransaction transaction: transactions) {
            total += transaction.getAmount();
        }
        return total;
    }
    public List<BankTransaction> transactionsByMonth(Month month){
        List<BankTransaction> transactionsByMonth = new ArrayList<>();
        for (BankTransaction transaction: transactions) {
            if(transaction.getDate().getMonth() == month){
                transactionsByMonth.add(transaction);
            }
        }
        return transactionsByMonth;
    }
    public double calculateTransactionsAmountByMonth(Month month){
                double total = 0d;
        for (BankTransaction item: transactions) {
            if(item.getDate().getMonth() == month){
                total += item.getAmount();
            }
        }
        return total;
    }

    public double calculateTotalByDescription(String category){
        double total = 0d;
        for (BankTransaction transaction: transactions) {
            if(transaction.getDescription().equals(category)){
                total += transaction.getAmount();
            }
        }
        return total;
    }
}
