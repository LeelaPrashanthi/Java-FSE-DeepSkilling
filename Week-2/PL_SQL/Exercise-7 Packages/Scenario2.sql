-- ============================================
-- ? SCENARIO 2: EmployeeManagement (3 Test Cases)
-- ============================================

-- Setup
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Employees CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Employees (
  EmployeeID NUMBER PRIMARY KEY,
  Name VARCHAR2(100),
  Salary NUMBER,
  Department VARCHAR2(50)
);

-- ? Package Specification
CREATE OR REPLACE PACKAGE EmployeeManagement IS
  PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_salary NUMBER, p_dept VARCHAR2);
  PROCEDURE UpdateDepartment(p_id NUMBER, p_dept VARCHAR2);
  FUNCTION GetAnnualSalary(p_id NUMBER) RETURN NUMBER;
END;
/

-- ? Package Body
CREATE OR REPLACE PACKAGE BODY EmployeeManagement IS
  PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_salary NUMBER, p_dept VARCHAR2) IS
  BEGIN
    INSERT INTO Employees VALUES (p_id, p_name, p_salary, p_dept);
    DBMS_OUTPUT.PUT_LINE('? Hired ' || p_name);
  EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
      DBMS_OUTPUT.PUT_LINE('? Employee ID ' || p_id || ' already exists.');
  END;

  PROCEDURE UpdateDepartment(p_id NUMBER, p_dept VARCHAR2) IS
  BEGIN
    UPDATE Employees SET Department = p_dept WHERE EmployeeID = p_id;
    DBMS_OUTPUT.PUT_LINE('? Updated department of Employee ' || p_id);
  END;

  FUNCTION GetAnnualSalary(p_id NUMBER) RETURN NUMBER IS
    sal NUMBER;
  BEGIN
    SELECT Salary * 12 INTO sal FROM Employees WHERE EmployeeID = p_id;
    RETURN sal;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN 0;
  END;
END;
/

-- ? Test Cases
BEGIN
  EmployeeManagement.HireEmployee(101, 'Ravi', 50000, 'HR');
  EmployeeManagement.HireEmployee(102, 'Sneha', 60000, 'IT');
  EmployeeManagement.HireEmployee(101, 'Duplicate', 45000, 'Finance');

  EmployeeManagement.UpdateDepartment(102, 'Engineering');
  EmployeeManagement.UpdateDepartment(999, 'Ghost');

  DBMS_OUTPUT.PUT_LINE('Annual Salary (101): $' || EmployeeManagement.GetAnnualSalary(101));
  DBMS_OUTPUT.PUT_LINE('Annual Salary (102): $' || EmployeeManagement.GetAnnualSalary(102));
  DBMS_OUTPUT.PUT_LINE('Annual Salary (999): $' || EmployeeManagement.GetAnnualSalary(999));
END;
/

SELECT * FROM Employees;
