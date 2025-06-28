-- ? SCENARIO 1: CALCULATE AGE FUNCTION
-- =========================
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Customers CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Customers (
  CustomerID NUMBER PRIMARY KEY,
  Name VARCHAR2(100),
  DOB DATE
);

-- Insert Test Data
INSERT INTO Customers VALUES (1, 'John Doe', TO_DATE('1980-06-01', 'YYYY-MM-DD'));
INSERT INTO Customers VALUES (2, 'Jane Smith', TO_DATE('2000-01-01', 'YYYY-MM-DD'));
INSERT INTO Customers VALUES (3, 'David Clark', TO_DATE('1965-12-15', 'YYYY-MM-DD'));

-- Create Function
CREATE OR REPLACE FUNCTION CalculateAge(dob DATE) RETURN NUMBER IS
  age NUMBER;
BEGIN
  age := FLOOR(MONTHS_BETWEEN(SYSDATE, dob) / 12);
  RETURN age;
END;
/

-- Test Calls
DECLARE
  age1 NUMBER;
  age2 NUMBER;
  age3 NUMBER;
BEGIN
  SELECT CalculateAge(DOB) INTO age1 FROM Customers WHERE CustomerID = 1;
  SELECT CalculateAge(DOB) INTO age2 FROM Customers WHERE CustomerID = 2;
  SELECT CalculateAge(DOB) INTO age3 FROM Customers WHERE CustomerID = 3;

  DBMS_OUTPUT.PUT_LINE('Age of Customer 1: ' || age1);
  DBMS_OUTPUT.PUT_LINE('Age of Customer 2: ' || age2);
  DBMS_OUTPUT.PUT_LINE('Age of Customer 3: ' || age3);
END;
/
