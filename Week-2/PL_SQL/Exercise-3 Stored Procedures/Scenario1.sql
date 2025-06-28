-- ======= Setup =======
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Accounts CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Accounts (
  AccountID NUMBER PRIMARY KEY,
  CustomerID NUMBER,
  AccountType VARCHAR2(20),
  Balance NUMBER
);

INSERT INTO Accounts VALUES (1, 1, 'Savings', 1000);
INSERT INTO Accounts VALUES (2, 2, 'Checking', 1500);
INSERT INTO Accounts VALUES (3, 3, 'Savings', 2000);

-- ======= Before Execution =======

SELECT * FROM Accounts;

-- ======= Procedure =======
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
  DBMS_OUTPUT.PUT_LINE('? Applying 1% interest to Savings accounts...');
  FOR acc IN (
    SELECT AccountID, Balance FROM Accounts WHERE AccountType = 'Savings'
  ) LOOP
    UPDATE Accounts
    SET Balance = Balance + (acc.Balance * 0.01)
    WHERE AccountID = acc.AccountID;

    DBMS_OUTPUT.PUT_LINE('? Interest added to Account ID: ' || acc.AccountID);
  END LOOP;
END;
/

-- ======= Execution =======
BEGIN
  ProcessMonthlyInterest;
END;
/

-- ======= After Execution =======

SELECT * FROM Accounts;
