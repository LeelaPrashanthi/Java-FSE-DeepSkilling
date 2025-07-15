package com.example.employeeservice.dao;

import org.springframework.stereotype.Repository;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentDao {
    public static ArrayList<String> DEPARTMENT_LIST;

    @Resource(name = "departmentList")
    private ArrayList<String> departmentList;

    public DepartmentDao() {}

    @jakarta.annotation.PostConstruct
    public void init() {
        DEPARTMENT_LIST = departmentList;
    }

    public ArrayList<String> getAllDepartments() {
        return DEPARTMENT_LIST;
    }
} 