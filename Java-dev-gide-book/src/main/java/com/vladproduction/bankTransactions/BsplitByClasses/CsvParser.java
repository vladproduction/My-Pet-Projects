package com.vladproduction.bankTransactions.BsplitByClasses;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


public class CsvParser {
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public List<BankTransaction> loadFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .map(this::lineFromCSV)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


//    public List<BankTransaction> loadFromFile(File file) {
//        List<BankTransaction> result = new ArrayList<>();
//
//            try(BufferedReader reader = new BufferedReader(new FileReader(file))){
//                String s = reader.readLine();
//                BankTransaction bankTransaction = lineFromCSV(s);
//                result.add(bankTransaction);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//
//        return result;
//    }

    private BankTransaction lineFromCSV(String line){
        String[] columns = line.split(",");

        LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        double amount = Double.parseDouble(columns[1]);
        String description = columns[2];

        return new BankTransaction(date, amount, description);
    }

}
