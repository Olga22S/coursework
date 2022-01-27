package ru.skypro.employeebook;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class EmployeeBook {

    private static Employee[] employees;

    static {
        employees = new Employee[10];
    }

    public void addEmployee(Employee employee) {
        int i = 0;
        while (i < employees.length && !(isNull(employees[i]))) {
            i++;
        }
        if (i != employees.length) {
            employees[i] = employee;
        }
    }

    public void deleteEmployee(Employee employee) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId() == employee.getId()) {
                employees[i] = null;
            }
        }
    }

    public void printEmployeesByDepartment() {
        Map<Integer, List<Employee>> employeesByDepartment = Arrays.stream(employees)
                .collect(Collectors.groupingBy(Employee::getDepartment));
        for (Map.Entry<Integer, List<Employee>> item : employeesByDepartment.entrySet()) {
            System.out.println("Department: " + item.getKey());
            for (Employee employee : item.getValue()) {
                System.out.println(employee.getId() + ", " + employee.getFullName() + ", " + employee.getSalary());
            }
        }
    }

    public void upgradeEmployeeDepartment(String fullName, int dep) {
        Arrays.stream(employees)
                .filter(s -> s.getFullName().equals(fullName))
                .findFirst().get().setDepartment(dep);
    }

    public void upgradeEmployeeSalary(String fullName, double salary) {
        Arrays.stream(employees)
                .filter(s -> s.getFullName().equals(fullName))
                .findFirst().get().setSalary(salary);
    }

    public void printEmployees() {
        Arrays.stream(employees).forEach(s -> System.out.println(s));
    }

    public void printEmployeesNames() {
        Arrays.stream(employees).forEach(s -> System.out.println(s.getFullName()));
    }

    public double getAverageSalary() {
        return getMonthlyExpenses() / employees.length;
    }

    public double getMonthlyExpenses() {
        return Arrays.stream(employees)
                .mapToDouble(o -> o.getSalary()).sum();
    }

    public Employee getMinSalaryEmployee() {
        return Arrays.stream(employees)
                .min(Comparator.comparingDouble(Employee::getSalary)).get();
    }

    public Employee getMaxSalaryEmployee() {
        return Arrays.stream(employees)
                .max(Comparator.comparingDouble(Employee::getSalary)).get();
    }

    public void makeSalaryIndexing(int indexingValue) {
        Arrays.stream(employees)
                .forEach(s -> s.setSalary(s.getSalary() + ((double) indexingValue / 100 * s.getSalary())));
    }

    public Employee getDepartmentMinSalaryEmployee(int dep) {
        return Arrays.stream(employees)
                .filter(s -> s.getDepartment() == dep)
                .min(Comparator.comparingDouble(Employee::getSalary)).get();
    }

    public Employee getDepartmentMaxSalaryEmployee(int dep) {
        return Arrays.stream(employees)
                .filter(s -> s.getDepartment() == dep)
                .max(Comparator.comparingDouble(Employee::getSalary)).get();
    }

    public double getDepartmentMonthlyExpenses(int dep) {
        return Arrays.stream(employees)
                .filter(s -> s.getDepartment() == dep)
                .mapToDouble(o -> o.getSalary()).sum();
    }

    public double getDepartmentAverageSalary(int dep) {
        double monthlyExpenses = getDepartmentMonthlyExpenses(dep);
        int employeeNumbers = (int) Arrays.stream(employees)
                .filter(s -> s.getDepartment() == dep).count();
        return monthlyExpenses / employeeNumbers;
    }

    public void makeDepartmentSalaryIndexing(int indexingValue, int dep) {
        Arrays.stream(employees)
                .filter(s -> s.getDepartment() == dep)
                .forEach(s -> s.setSalary(s.getSalary() + ((double) indexingValue / 100 * s.getSalary())));
    }

    public void printDepartmentEmployees(int dep) {
        Arrays.stream(employees)
                .filter(s -> s.getDepartment() == dep)
                .forEach(s -> printEmployee(s));
    }

    public void printEmployeesWhoseSalaryLessThanNumber(double num) {
        Arrays.stream(employees)
                .filter(s -> s.getSalary() < num)
                .forEach(s -> printEmployee(s));
    }

    public void printEmployeesWhoseSalaryMoreThanNumber(double num) {
        Arrays.stream(employees)
                .filter(s -> s.getSalary() >= num)
                .forEach(s -> printEmployee(s));
    }

    private void printEmployee(Employee employee) {
        System.out.print("id= " + employee.getId());
        System.out.print(", fullName= " + employee.getFullName());
        System.out.println(", salary=" + employee.getSalary());
    }

}
