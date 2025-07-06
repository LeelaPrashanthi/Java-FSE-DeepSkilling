package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    /**
     * Get all employees
     */
    @Transactional
    public List<Employee> getAllEmployees() {
        LOGGER.info("Fetching all employees");
        return employeeRepository.findAll();
    }
    
    /**
     * Get employee by ID
     */
    @Transactional
    public Employee get(int id) {
        LOGGER.info("Finding employee by id: {}", id);
        Optional<Employee> result = employeeRepository.findById(id);
        if (result.isPresent()) {
            Employee employee = result.get();
            LOGGER.info("Found employee: {}", employee);
            return employee;
        } else {
            LOGGER.warn("Employee not found with id: {}", id);
            return null;
        }
    }
    
    /**
     * Save employee
     */
    @Transactional
    public Employee save(Employee employee) {
        LOGGER.info("Saving employee: {}", employee);
        Employee savedEmployee = employeeRepository.save(employee);
        LOGGER.info("Employee saved successfully: {}", savedEmployee);
        return savedEmployee;
    }
    
    /**
     * Get all permanent employees using HQL
     */
    @Transactional
    public List<Employee> getAllPermanentEmployees() {
        LOGGER.info("Fetching all permanent employees using HQL");
        List<Employee> employees = employeeRepository.getAllPermanentEmployees();
        LOGGER.info("Found {} permanent employees", employees.size());
        return employees;
    }
    
    /**
     * Get all permanent employees using HQL with fetch optimization
     */
    @Transactional
    public List<Employee> getAllPermanentEmployeesWithFetch() {
        LOGGER.info("Fetching all permanent employees using HQL with fetch optimization");
        List<Employee> employees = employeeRepository.getAllPermanentEmployeesWithFetch();
        LOGGER.info("Found {} permanent employees with optimized query", employees.size());
        return employees;
    }
    
    /**
     * Get average salary using HQL
     */
    @Transactional
    public double getAverageSalary() {
        LOGGER.info("Calculating average salary using HQL");
        Double averageSalary = employeeRepository.getAverageSalary();
        if (averageSalary == null) {
            LOGGER.info("No employees found, average salary is 0.0");
            return 0.0;
        }
        LOGGER.info("Average salary: {}", averageSalary);
        return averageSalary;
    }
    
    /**
     * Get average salary by department using HQL
     */
    @Transactional
    public double getAverageSalary(int departmentId) {
        LOGGER.info("Calculating average salary for department ID: {}", departmentId);
        Double averageSalary = employeeRepository.getAverageSalary(departmentId);
        if (averageSalary == null) {
            LOGGER.info("No employees found in department {}, average salary is 0.0", departmentId);
            return 0.0;
        }
        LOGGER.info("Average salary for department {}: {}", departmentId, averageSalary);
        return averageSalary;
    }
    
    /**
     * Get all employees using Native Query
     */
    @Transactional
    public List<Employee> getAllEmployeesNative() {
        LOGGER.info("Fetching all employees using Native Query");
        List<Employee> employees = employeeRepository.getAllEmployeesNative();
        LOGGER.info("Found {} employees using native query", employees.size());
        return employees;
    }
} 