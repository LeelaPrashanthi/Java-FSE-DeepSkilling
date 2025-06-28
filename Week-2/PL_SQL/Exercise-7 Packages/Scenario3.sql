-- ============================================
-- ? SCENARIO 3: AccountOperations (3 Test Cases)
-- ============================================

--  Setup
BEGIN
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

-- ? Insert 2 Customers
INSERT INTO Customers VALUES (1, 'Tarun');
INSERT INTO Customers VALUES (2, 'Leela');

-- ? Package Specification
CREATE OR REPLACE PACKAGE AccountOperations IS
  PROCEDURE OpenAccount(p_accid NUMBER, p_custid NUMBER, p_bal NUMBER);
  PROCEDURE CloseAccount(p_accid NUMBER);
  FUNCTION GetTotalBalance(p_custid NUMBER) RETURN NUMBER;
END;
/

-- ? Package Body
CREATE OR REPLACE PACKAGE BODY AccountOperations IS
  PROCEDURE OpenAccount(p_accid NUMBER, p_custid NUMBER, p_bal NUMBER) IS
  BEGIN
    INSERT INTO Accounts VALUES (p_accid, p_custid, p_bal);
    DBMS_OUTPUT.PUT_LINE('? Account ' || p_accid || ' opened for Customer ' || p_custid);
  EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
      DBMS_OUTPUT.PUT_LINE('? Account already exists.');
  END;

  PROCEDURE CloseAccount(p_accid NUMBER) IS
  BEGIN
    DELETE FROM Accounts WHERE AccountID = p_accid;
    DBMS_OUTPUT.PUT_LINE('? Account ' || p_accid || ' closed.');
  END;

  FUNCTION GetTotalBalance(p_custid NUMBER) RETURN NUMBER IS
    total NUMBER;
  BEGIN
    SELECT NVL(SUM(Balance), 0) INTO total FROM Accounts WHERE CustomerID = p_custid;
    RETURN total;
  END;
END;
/

-- ? Test Calls
BEGIN
  AccountOperations.OpenAccount(201, 1, 2000);
  AccountOperations.OpenAccount(202, 1, 3000);
  AccountOperations.OpenAccount(201, 1, 999);  -- duplicate

  DBMS_OUTPUT.PUT_LINE('Total Balance (Customer 1): ?' || AccountOperations.GetTotalBalance(1));
  AccountOperations.CloseAccount(202);
  DBMS_OUTPUT.PUT_LINE('Total Balance After Closing: ?' || AccountOperations.GetTotalBalance(1));

  DBMS_OUTPUT.PUT_LINE('Total Balance (Customer 2): ?' || AccountOperations.GetTotalBalance(2)); -- no account
END;
/

SELECT * FROM Accounts;
