package com.vladproduction.bankTransactions.Asimple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//Keep It Short and Simple — KISS

public class BankTransactionAnalyzerSimple {
    public static void main(final String... args) throws IOException {

//        final String filePath = "src/main/resources/transactions.csv";
      List<List<String>> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"))) {
            String line;
            double total = 0D;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                data.add(Arrays.asList(values));
                final double amount = Double.parseDouble(values[1]);
                total += amount;
            }
            System.out.println("The total for all transactions is " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("--------------------");
        // Print the data from the CSV file
        System.out.println("data from transactions.csv file:");
        for (List<String> row : data) {
            for (String value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }

    }
}

