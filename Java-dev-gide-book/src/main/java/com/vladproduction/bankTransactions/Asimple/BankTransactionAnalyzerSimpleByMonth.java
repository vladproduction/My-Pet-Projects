package com.vladproduction.bankTransactions.Asimple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//Keep It Short and Simple — KISS

public class BankTransactionAnalyzerSimpleByMonth {
    public static void main(final String... args) throws IOException {

        final String filePath = "src/main/resources/transactions.csv";
        final List<List<String>> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            double total = 0D;
            final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                final LocalDate date = LocalDate.parse(values[0], DATE_PATTERN);
                data.add(Arrays.asList(values));
                if(date.getMonth() == Month.JANUARY){
                    final double amount = Double.parseDouble(values[1]);
                    total += amount;
                }
            }
            System.out.println("The total for all transactions in January is " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("---------------------");
        // Print the data from the CSV file
        System.out.println("data in January from transactions.csv file:");
        for (List<String> row : data) {
            for (String value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }

    }
}

