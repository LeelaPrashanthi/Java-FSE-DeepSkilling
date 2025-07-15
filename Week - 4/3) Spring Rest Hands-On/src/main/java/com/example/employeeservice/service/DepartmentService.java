package com.example.employeeservice.service;

import com.example.employeeservice.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;

    public List<String> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }
} 