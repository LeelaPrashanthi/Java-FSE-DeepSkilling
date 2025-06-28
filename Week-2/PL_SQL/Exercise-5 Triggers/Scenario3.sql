-- =============================================
-- ? SCENARIO 3: ENFORCE BUSINESS RULES ON TRANSACTIONS
-- =============================================

-- Drop existing tables if present
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Transactions CASCADE CONSTRAINTS';
  EXECUTE IMMEDIATE 'DROP TABLE Accounts CASCADE CONSTRAINTS';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

-- ? Create Accounts table
CREATE TABLE Accounts (
  AccountID NUMBER PRIMARY KEY,
  Balance NUMBER
);

--  Create Transactions table
CREATE TABLE Transactions (
  TransactionID NUMBER PRIMARY KEY,
  AccountID NUMBER,
  TransactionDate DATE,
  Amount NUMBER,
  TransactionType VARCHAR2(10)
);

-- ? Insert test accounts
INSERT INTO Accounts VALUES (1, 1000);  -- Sufficient balance
INSERT INTO Accounts VALUES (2, 100);   -- Low balance
INSERT INTO Accounts VALUES (3, 0);     -- Zero balance

-- ?? Create trigger to enforce transaction rules
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
  acc_balance NUMBER;
BEGIN
  -- Get current balance
  SELECT Balance INTO acc_balance FROM Accounts WHERE AccountID = :NEW.AccountID;

  -- Validate deposit
  IF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN
    RAISE_APPLICATION_ERROR(-20002, '? Deposit amount must be positive.');
  END IF;

  -- Validate withdrawal
  IF :NEW.TransactionType = 'Withdrawal' AND :NEW.Amount > acc_balance THEN
    RAISE_APPLICATION_ERROR(-20001, '? Withdrawal exceeds available balance.');
  END IF;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RAISE_APPLICATION_ERROR(-20003, '? Account not found.');
END;
/

-- =============================================
-- ? TEST CASES
-- =============================================

-- ? Valid deposit: ?500 to Account 1
BEGIN
  INSERT INTO Transactions VALUES (1, 1, SYSDATE, 500, 'Deposit');
  DBMS_OUTPUT.PUT_LINE('? Transaction 1 (Deposit) inserted successfully.');
EXCEPTION
  WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE(SQLERRM);
END;
/

-- ? Invalid withdrawal: ?200 from Account 2 (only ?100 available)
BEGIN
  INSERT INTO Transactions VALUES (2, 2, SYSDATE, 200, 'Withdrawal');
  DBMS_OUTPUT.PUT_LINE('? Transaction 2 inserted.');
EXCEPTION
  WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE(SQLERRM);
END;
/

-- ? Invalid deposit: ?0 to Account 1
BEGIN
  INSERT INTO Transactions VALUES (3, 1, SYSDATE, 0, 'Deposit');
  DBMS_OUTPUT.PUT_LINE('? Transaction 3 inserted.');
EXCEPTION
  WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE(SQLERRM);
END;
/

-- ? Invalid account ID
BEGIN
  INSERT INTO Transactions VALUES (4, 999, SYSDATE, 100, 'Deposit');
  DBMS_OUTPUT.PUT_LINE('? Transaction 4 inserted.');
EXCEPTION
  WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE(SQLERRM);
END;
/

-- =============================================
--  FINAL OUTPUT: Transactions Table
-- =============================================
SELECT * FROM Transactions;
