package com.vladproduction.bankTransactions.BsplitByClasses;

import java.io.File;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws Exception {

        File file = new File("src/main/resources/transactions.csv");
        CsvParser parser = new CsvParser();
        List<BankTransaction> bankTransactions = parser.loadFromFile(file);

        //using object from base class to concrete tasks:
        System.out.println("The total for all transactions is " + calculateTotalAmount(bankTransactions));
        System.out.println("Transactions in JANUARY " + transactionsByMonth(bankTransactions, Month.JANUARY));
        System.out.println("Transactions in FEBRUARY " + transactionsByMonth(bankTransactions, Month.FEBRUARY));
        System.out.println("Transactions Amount in JANUARY " + calculateAmountByMonth(bankTransactions, Month.JANUARY));
        System.out.println("Transactions Amount in FEBRUARY " + calculateAmountByMonth(bankTransactions, Month.FEBRUARY));
    }

    //concrete tasks:
    private static double calculateTotalAmount(List<BankTransaction> bankTransactions) {
        double total = 0d;
        for (BankTransaction transaction: bankTransactions) {
            total += transaction.getAmount();
        }
        return total;
    }
    private static List<BankTransaction> transactionsByMonth(List<BankTransaction> transactions, Month month){
        List<BankTransaction> transactionsByMonth = new ArrayList<>();
        for (BankTransaction transaction: transactions) {
            if(transaction.getDate().getMonth() == month){
                transactionsByMonth.add(transaction);
            }
        }
        return transactionsByMonth;
    }
    private static double calculateAmountByMonth(List<BankTransaction> bankTransactions, Month month){
        double total = 0d;
        for (BankTransaction item: bankTransactions) {
            if(item.getDate().getMonth() == month){
                total += item.getAmount();
            }
        }
        return total;
    }
}
