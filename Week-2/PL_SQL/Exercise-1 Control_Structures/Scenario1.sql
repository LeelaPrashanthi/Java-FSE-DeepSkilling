BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Loans CASCADE CONSTRAINTS';
  EXECUTE IMMEDIATE 'DROP TABLE Customers CASCADE CONSTRAINTS';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE
);

CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    LoanAmount NUMBER,
    InterestRate NUMBER,
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

INSERT INTO Customers VALUES (1, 'John Doe', TO_DATE('1950-05-15', 'YYYY-MM-DD'));
INSERT INTO Customers VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'));

INSERT INTO Loans VALUES (1, 1, 5000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 25));
INSERT INTO Loans VALUES (2, 2, 10000, 7, SYSDATE, SYSDATE + 20);

-- PL/SQL block
BEGIN
  FOR cust IN (
    SELECT c.CustomerID, l.LoanID, c.DOB
    FROM Customers c
    JOIN Loans l ON c.CustomerID = l.CustomerID
  ) LOOP
    IF MONTHS_BETWEEN(SYSDATE, cust.DOB) / 12 > 60 THEN
      UPDATE Loans
      SET InterestRate = InterestRate - 1
      WHERE LoanID = cust.LoanID;

      DBMS_OUTPUT.PUT_LINE('Discount applied to LoanID: ' || cust.LoanID);
    END IF;
  END LOOP;
END;
/

-- Output Schema Verification
SELECT * FROM Loans;