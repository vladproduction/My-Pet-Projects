package org.solution2;


import org.solution2.exporter.CustomerExporter;
import org.solution2.exporter.CustomerExporterFactory;
import org.solution2.exporter.CustomerExporterType;
import org.solution2.rent.RentProcessor;
import org.solution2.repository.MovieRepository;
import org.solution2.repository.PartMatchCategoryMovieRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class MainSolution2 {
    public static void main(String[] args) {
        Set<String> categories = new HashSet<>();
        categories.add("Action");
        categories.add("Drama");
        categories.add("Comedy");
        Set<String> categories2 = new HashSet<>();
        categories.add("Action");
        categories.add("Drama");
        Set<String> categories3 = new HashSet<>();
        categories3.add("Family");
        categories3.add("Drama");
        Set<String> categories4 = new HashSet<>();
        categories4.add("Family");
        categories4.add("Comedy");
        categories4.add("Fantasy");

        Map<String, String> metaData = new HashMap<>();
        metaData.put("duration", "3 hs");
        metaData.put("Country production", "UK");

        MyMovie dieHart = new MyMovie("Die Hart", categories);
        MyMovie dieHart2 = new MyMovie("Die Hart2", categories2);
        MyMovie titanic = new MyMovie("Titanic", categories3);
        MyMovie garryPotter4 = new MyMovie("Garry Potter-4", categories4, metaData);

        MovieRepository partMatch = new PartMatchCategoryMovieRepository();
        partMatch.add(dieHart);
        partMatch.add(dieHart2);
        partMatch.add(titanic);
        partMatch.add(garryPotter4);

        ShopCard shopCardTest = new ShopCard();
        MyCustomer customer = new MyCustomer("TestCustomer", shopCardTest);
        RentProcessor rentProcessor = new RentProcessor(partMatch);
        rentProcessor.makeRent("Die Hart", customer, 4);
        rentProcessor.makeRent("Garry Potter-4", customer, 2);
        rentProcessor.makeRent("Titanic", customer, 5);
        rentProcessor.makeRent("Die Hart2", customer, 1);

        CustomerExporter customerExporter = CustomerExporterFactory.getCustomerExporter(CustomerExporterType.TEXT);
        String receiptText = customerExporter.export(customer);
        System.out.println(receiptText);

        System.out.println("-------------------------------");
        customerExporter = CustomerExporterFactory.getCustomerExporter(CustomerExporterType.HTML);
        String receiptHTML = customerExporter.export(customer);
        System.out.println(receiptHTML);

    }
}
