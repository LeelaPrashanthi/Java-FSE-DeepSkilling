-- ? SCHEMA SETUP
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Customers CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Customers (
  CustomerID NUMBER PRIMARY KEY,
  Name VARCHAR2(100),
  Balance NUMBER,
  LastModified DATE
);

-- Insert Test Data
INSERT INTO Customers VALUES (1, 'John Doe', 5000, SYSDATE);
INSERT INTO Customers VALUES (2, 'Jane Smith', 8000, SYSDATE);

-- ? TRIGGER: Updates LastModified on UPDATE
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
  :NEW.LastModified := SYSDATE;
END;
/

-- ? TEST: Update name of customer
UPDATE Customers SET Name = 'John Updated' WHERE CustomerID = 1;

-- ? OUTPUT CHECK
SELECT * FROM Customers;
