package org.solution.customers;

import java.util.ArrayList;
import java.util.List;

import static org.solution.customers.Customer.CustomerType.IMPERMANENT;
import static org.solution.customers.Customer.CustomerType.PERMANENT;

public class TestCustomer {
    public static void main(String[] args) {

        Customer customer1 = new Customer("AAA","aaa@com.com", PERMANENT);
        Customer customer2 = new Customer("BBB","bbb@com.com", IMPERMANENT);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        int count = 1;
        for (Customer customer:customers) {
            System.out.println(count+")"+customer.toString());
            count++;
        }
    }
}
