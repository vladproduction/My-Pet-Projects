package com.vladproduction.bankTransactions.caseD_usingInterface;



import java.io.File;
import java.util.List;

import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;

public class BaseBankAnalyzer {

    File fileCsv = new File("src/main/resources/transactions.csv");
    File fileJson = new File("src/main/resources/transactions.json");
    Parser parserCsv = new CsvParserImpl();
    Parser parserJson = new JsonParserImpl();
    List<BankTransaction> bankTransactions = parserJson.loadFromFile(fileJson);
    BankProcessor processor = new BankProcessor(bankTransactions);



    public void analyze(){
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
