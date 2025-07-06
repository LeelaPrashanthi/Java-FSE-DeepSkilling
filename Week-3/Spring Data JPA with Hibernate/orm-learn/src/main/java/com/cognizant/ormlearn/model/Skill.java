package com.cognizant.ormlearn.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "skill")
public class Skill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sk_id")
    private int id;
    
    @Column(name = "sk_name")
    private String name;
    
    // Many-to-Many relationship with Employee
    @ManyToMany(mappedBy = "skillList")
    private Set<Employee> employeeList;
    
    // Default constructor
    public Skill() {
    }
    
    // Constructor with name
    public Skill(String name) {
        this.name = name;
    }
    
    // Constructor with all fields
    public Skill(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Set<Employee> getEmployeeList() {
        return employeeList;
    }
    
    public void setEmployeeList(Set<Employee> employeeList) {
        this.employeeList = employeeList;
    }
    
    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
} 