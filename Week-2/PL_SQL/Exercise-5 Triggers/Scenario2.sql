-- ? SCHEMA SETUP
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE AuditLog CASCADE CONSTRAINTS';
  EXECUTE IMMEDIATE 'DROP TABLE Transactions CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Transactions (
  TransactionID NUMBER PRIMARY KEY,
  AccountID NUMBER,
  TransactionDate DATE,
  Amount NUMBER,
  TransactionType VARCHAR2(10)
);

CREATE TABLE AuditLog (
  LogID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  TransactionID NUMBER,
  Message VARCHAR2(200),
  LoggedAt DATE
);

-- ? TRIGGER: Log transaction inserts
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (TransactionID, Message, LoggedAt)
  VALUES (:NEW.TransactionID,
          'Transaction ' || :NEW.TransactionType || ' of ?' || :NEW.Amount,
          SYSDATE);
END;
/

-- ? TEST: Insert transactions
INSERT INTO Transactions VALUES (1, 101, SYSDATE, 500, 'Deposit');
INSERT INTO Transactions VALUES (2, 101, SYSDATE, 200, 'Withdrawal');

-- ? OUTPUT CHECK
SELECT * FROM Transactions;
SELECT * FROM AuditLog;
