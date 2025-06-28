-- ======= Setup =======
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

INSERT INTO Employees VALUES (1, 'Alice', 50000, 'HR');
INSERT INTO Employees VALUES (2, 'Bob', 60000, 'IT');
INSERT INTO Employees VALUES (3, 'Carol', 55000, 'HR');

-- ======= Before Execution =======

SELECT * FROM Employees;

-- ======= Procedure =======
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
  dept IN VARCHAR2,
  bonus_pct IN NUMBER
) IS
BEGIN
  DBMS_OUTPUT.PUT_LINE('? Applying ' || bonus_pct || '% bonus to ' || dept || ' department...');
  UPDATE Employees
  SET Salary = Salary + (Salary * bonus_pct / 100)
  WHERE Department = dept;

  IF SQL%ROWCOUNT > 0 THEN
    DBMS_OUTPUT.PUT_LINE('? Bonus applied to ' || SQL%ROWCOUNT || ' employee(s)');
  ELSE
    DBMS_OUTPUT.PUT_LINE('?? No employees found in department: ' || dept);
  END IF;
END;
/

-- ======= Execution =======
BEGIN
  UpdateEmployeeBonus('HR', 10);   -- Should apply to 2 employees
  UpdateEmployeeBonus('Sales', 5); -- Should do nothing
END;
/

-- ======= After Execution =======

SELECT * FROM Employees;
