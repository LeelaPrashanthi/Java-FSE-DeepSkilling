package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    // Basic CRUD operations are automatically provided by JpaRepository
    // Additional custom query methods can be added here as needed
    
    /**
     * Get all permanent employees using HQL
     * Basic HQL query without fetch optimization
     */
    @Query(value="SELECT e FROM Employee e WHERE e.permanent = 1")
    List<Employee> getAllPermanentEmployees();
    
    /**
     * Get all permanent employees using HQL with fetch optimization
     * Using fetch to populate department and skill data in single query
     */
    @Query(value="SELECT e FROM Employee e left join fetch e.department d left join fetch e.skillList WHERE e.permanent = 1")
    List<Employee> getAllPermanentEmployeesWithFetch();
    
    /**
     * Get average salary using HQL
     */
    @Query(value="SELECT AVG(e.salary) FROM Employee e")
    Double getAverageSalary();
    
    /**
     * Get average salary by department using HQL
     */
    @Query(value="SELECT AVG(e.salary) FROM Employee e where e.department.id = :id")
    Double getAverageSalary(@Param("id") int id);
    
    /**
     * Get all employees using Native Query
     */
    @Query(value="SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();
} 