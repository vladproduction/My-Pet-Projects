package com.vladproduction.bankTransactions.jsonTester;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionParser {

    private static final ObjectMapper mapper = new ObjectMapper();
    public List<BankTransactionTester> loadFromFile(File file) {
        try {
            BankTransactionTester[] transactionsArray = mapper.readValue(file, BankTransactionTester[].class);
            List<BankTransactionTester> transactionList = new ArrayList<>();
            for (BankTransactionTester transaction : transactionsArray) {
                transactionList.add(transaction);
            }
            return transactionList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        File fileJson = new File("src/main/resources/transactions.json");

        TransactionParser parser = new TransactionParser();
        List<BankTransactionTester> transactions = parser.loadFromFile(fileJson);

        for (BankTransactionTester transaction : transactions) {
            System.out.println("Date: " + transaction.getDate());
            System.out.println("Amount: " + transaction.getAmount());
            System.out.println("Description: " + transaction.getDescription());
            System.out.println();
        }
    }
}


