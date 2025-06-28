-- Setup required table
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Customers CASCADE CONSTRAINTS';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Balance NUMBER,
    IsVIP VARCHAR2(5)
);

INSERT INTO Customers VALUES (1, 'John Doe', 1000, NULL);
INSERT INTO Customers VALUES (2, 'Jane Smith', 15000, NULL);

-- PL/SQL block
BEGIN
  FOR cust IN (SELECT CustomerID, Balance FROM Customers) LOOP
    IF cust.Balance > 10000 THEN
      UPDATE Customers
      SET IsVIP = 'TRUE'
      WHERE CustomerID = cust.CustomerID;

      DBMS_OUTPUT.PUT_LINE('Customer promoted to VIP: ' || cust.CustomerID);
    END IF;
  END LOOP;
END;
/

-- Output Schema Verification
SELECT * FROM Customers;

