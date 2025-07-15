package com.example.employeeservice.dao;

import com.example.employeeservice.model.Employee;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDao {
    public static ArrayList<Employee> EMPLOYEE_LIST;

    @Resource(name = "employeeList")
    private ArrayList<Employee> employeeList;

    public EmployeeDao() {}

    @jakarta.annotation.PostConstruct
    public void init() {
        EMPLOYEE_LIST = employeeList;
    }

    public ArrayList<Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }

    public Employee getEmployeeById(int id) {
        return EMPLOYEE_LIST.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst()
                .orElse(null);
    }
} 