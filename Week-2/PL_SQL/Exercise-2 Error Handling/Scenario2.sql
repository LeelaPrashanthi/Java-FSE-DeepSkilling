-- ========== Setup ==========
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Employees CASCADE CONSTRAINTS';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Salary NUMBER
);

INSERT INTO Employees VALUES (1, 'Alice', 50000);

-- ========== Procedure ==========
CREATE OR REPLACE PROCEDURE UpdateSalary(
  emp_id NUMBER,
  percent NUMBER
) IS
BEGIN
  DBMS_OUTPUT.PUT_LINE('--------------------------------------------------');
  DBMS_OUTPUT.PUT_LINE('Updating salary for Employee ID: ' || emp_id);
  DBMS_OUTPUT.PUT_LINE('--------------------------------------------------');

  UPDATE Employees
  SET Salary = Salary + (Salary * percent / 100)
  WHERE EmployeeID = emp_id;

  IF SQL%ROWCOUNT = 0 THEN
    DBMS_OUTPUT.PUT_LINE('? Error: Employee ID ' || emp_id || ' not found.');
  ELSE
    DBMS_OUTPUT.PUT_LINE('? Salary updated by ' || percent || '%.');
  END IF;
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('? Unexpected Error: ' || SQLERRM);
END;
/

-- ========== Test Cases ==========
BEGIN
  UpdateSalary(1, 10);     -- Success
  UpdateSalary(999, 15);   -- Fail: not found
END;
/

-- ========== Output ==========
SELECT * FROM Employees;
