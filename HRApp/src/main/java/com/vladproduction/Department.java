package com.vladproduction;

import java.util.Arrays;

public class Department {

    private String name;
    private Employee[] employees = new Employee[10];
    private int lastAddedEmployeeIndex = -1;


    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department: " + name;
    }

    /**
     * incrementing last added index and add employee to the array;
     * by adding consider the length of the array;
     * stop adding once capacity is full;
     * */
    public void addEmployee(Employee employee){
        if(lastAddedEmployeeIndex < employees.length){
            lastAddedEmployeeIndex++;
            employees[lastAddedEmployeeIndex] = employee;
        }
    }

    /**
     * return array with the exact number of employees associated to concrete department;
     * copy reference from employees array to this new array and return it;
     * invoker always gets an array that is already trimmed to exact required size;
     * */
    public Employee[] getEmployees(){
        Employee[] actualEmployees = new Employee[lastAddedEmployeeIndex + 1];
        for (int i = 0; i < actualEmployees.length; i++) {
            actualEmployees[i] = employees[i];
        }
        return actualEmployees;
    }

    /**
     * variable lastAddedEmployeeIndex indicates last valid position in employees array;
     * array indexing starts at zero, need to add one to get number of elements;
     * */
    public int getEmployeeCount(){

        return lastAddedEmployeeIndex + 1;
    }

    /**
     * get Employee by ID method return object associated to this department by specific id;
     * */
    public Employee getEmployeeByID(int id){
        for (Employee employee : employees) {
            if(employee != null){
                if(employee.getID() == id){
                    return employee;
                }
            }
        }
        return null;
    }

    /**
     * method to return total salary of the employees by department;
     * starting from position '0' and iterating to element indicated as lastAddedEmployeeIndex;
     * */
    public double getTotalSalary(){
        double totalSalary = 0.0;
        for (int i = 0; i <= lastAddedEmployeeIndex; i++) {
            totalSalary += employees[i].getSalary();

        }
        return totalSalary;
    }

    /**
     * method to calculate average salary;
     * */
    public double getAverageSalary(){
        if(lastAddedEmployeeIndex > -1){
            return getTotalSalary() / (lastAddedEmployeeIndex + 1);
        }
        return 0.0;
    }
}


















