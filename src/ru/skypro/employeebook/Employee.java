package ru.skypro.employeebook;

public class Employee {

    private static int employeeCounter;
    private final int id;
    private final String fullName;
    private int department;
    private double salary;

    static {
        employeeCounter = 1;
    }

    public Employee(String fullName, int department, double salary) {
        id = employeeCounter;
        employeeCounter++;
        this.fullName = fullName;
        this.department = department;
        this.salary = salary;
    }

    public static int getEmployeeCounter() {
        return employeeCounter;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "id: " + id + " " + fullName + ", department= " + department + ", salary= " + salary;
    }
}
