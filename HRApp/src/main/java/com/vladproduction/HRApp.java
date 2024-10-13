package com.vladproduction;

public class HRApp {
    public static void main(String[] args) {
        System.out.println("HRApp starts");

        Employee emp1 = new Employee(101, "John", 50000);
        Employee emp2 = new Employee(102, "Bob", 60000);
        System.out.println(emp1);
        System.out.println(emp2);

        Department depEducation = new Department("Education-Dep");
        depEducation.addEmployee(emp1);
        depEducation.addEmployee(emp2);

        Employee[] employees = depEducation.getEmployees();
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        int employeeCount = depEducation.getEmployeeCount();
        System.out.println("employeeCount = " + employeeCount);

        double totalSalary = depEducation.getTotalSalary();
        System.out.println("totalSalary = " + totalSalary);

        double averageSalary = depEducation.getAverageSalary();
        System.out.println("averageSalary = " + averageSalary);

        Employee employeeByID_101 = depEducation.getEmployeeByID(emp1.getID());
        System.out.println("employeeByID_101 = " + employeeByID_101);
        Employee employeeByID_102 = depEducation.getEmployeeByID(emp2.getID());
        System.out.println("employeeByID_102 = " + employeeByID_102);

        //if null
        Employee employeeByID_null_expected = depEducation.getEmployeeByID(1);
        System.out.println("employeeByID_null_expected = " + employeeByID_null_expected);

        System.out.println(depEducation);
    }
}
