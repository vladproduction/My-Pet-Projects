package com.vladproduction.bankTransactions.caseC_withProcessorClass;


import java.io.File;
import java.util.List;

import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;

public class Main {

    public static void main(String[] args) throws Exception {

        File file = new File("src/main/resources/transactions.csv");
        CsvParser parser = new CsvParser();
        List<BankTransaction> bankTransactions = parser.loadFromFile(file);
        BankProcessor processor = new BankProcessor(bankTransactions);

        collectSummary(processor);

    }

    private static void collectSummary(BankProcessor processor) {
        System.out.println("Amount of Total Transactions: " + processor.calculateTotalTransactionsAmount());
        System.out.println("Transactions in JANUARY: " + processor.transactionsByMonth(JANUARY));
        System.out.println("Transactions in FEBRUARY: " + processor.transactionsByMonth(FEBRUARY));
        System.out.println("Amount by month (JANUARY): " + processor.calculateTransactionsAmountByMonth(JANUARY));
        System.out.println("Amount by month (FEBRUARY): " + processor.calculateTransactionsAmountByMonth(FEBRUARY));
        System.out.println("\tTotal Salary: " + processor.calculateTotalByDescription("Salary"));
        System.out.println("\tTotal Tesco: " + processor.calculateTotalByDescription("Tesco"));
        System.out.println("\tTotal Rent: " + processor.calculateTotalByDescription("Rent"));
        System.out.println("\tTotal Cinema: " + processor.calculateTotalByDescription("Cinema"));
        System.out.println("\tTotal Royalties: " + processor.calculateTotalByDescription("Royalties"));

    }


}
