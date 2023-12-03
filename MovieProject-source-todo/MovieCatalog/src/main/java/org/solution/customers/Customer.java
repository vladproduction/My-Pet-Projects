package org.solution.customers;

public class Customer {

    private String name;
    private String email;

    public CustomerType customerType;

    public enum CustomerType {
        PERMANENT, IMPERMANENT
    }

    public Customer(String name, String email, CustomerType customerType) {
        this.name = name;
        this.email = email;
        this.customerType = customerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    @Override
    public String toString() {
        return name +" "+" "+ email +" "+ customerType;
    }
}
