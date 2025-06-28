-- ========================================================
-- ? SCENARIO 1: Generate Monthly Statements using Cursor
-- ========================================================

-- ? Schema Setup
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Transactions CASCADE CONSTRAINTS';
  EXECUTE IMMEDIATE 'DROP TABLE Accounts CASCADE CONSTRAINTS';
  EXECUTE IMMEDIATE 'DROP TABLE Customers CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Customers (
  CustomerID NUMBER PRIMARY KEY,
  Name VARCHAR2(100)
);

CREATE TABLE Accounts (
  AccountID NUMBER PRIMARY KEY,
  CustomerID NUMBER,
  Balance NUMBER,
  FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Transactions (
  TransactionID NUMBER PRIMARY KEY,
  AccountID NUMBER,
  TransactionDate DATE,
  Amount NUMBER,
  TransactionType VARCHAR2(10),
  FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

-- ? Test Data
INSERT INTO Customers VALUES (1, 'Alice');
INSERT INTO Customers VALUES (2, 'Bob');

INSERT INTO Accounts VALUES (101, 1, 5000);
INSERT INTO Accounts VALUES (102, 2, 3000);

INSERT INTO Transactions VALUES (1, 101, SYSDATE - 2, 200, 'Deposit');
INSERT INTO Transactions VALUES (2, 101, SYSDATE - 1, 100, 'Withdrawal');
INSERT INTO Transactions VALUES (3, 102, SYSDATE - 5, 400, 'Deposit');

-- ? PL/SQL Block
BEGIN
  DBMS_OUTPUT.PUT_LINE('--- Monthly Statement ---');
  FOR rec IN (
    SELECT c.Name, t.AccountID, t.Amount, t.TransactionType, t.TransactionDate
    FROM Customers c
    JOIN Accounts a ON c.CustomerID = a.CustomerID
    JOIN Transactions t ON a.AccountID = t.AccountID
    WHERE t.TransactionDate >= TRUNC(SYSDATE, 'MM')
  ) LOOP
    DBMS_OUTPUT.PUT_LINE('Customer: ' || rec.Name || 
                         ' | Account: ' || rec.AccountID || 
                         ' | ' || rec.TransactionType || 
                         ' $' || rec.Amount || 
                         ' on ' || TO_CHAR(rec.TransactionDate, 'DD-MON'));
  END LOOP;
END;
/
