-- ======= Setup =======
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Accounts CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Accounts (
  AccountID NUMBER PRIMARY KEY,
  CustomerID NUMBER,
  Balance NUMBER
);

INSERT INTO Accounts VALUES (1, 1, 5000);
INSERT INTO Accounts VALUES (2, 1, 3000);

-- ======= Before Execution =======

SELECT * FROM Accounts;

-- ======= Procedure =======
CREATE OR REPLACE PROCEDURE TransferFunds(
  from_acc_id NUMBER,
  to_acc_id NUMBER,
  amount NUMBER
) IS
  from_balance NUMBER;
BEGIN
  DBMS_OUTPUT.PUT_LINE('? Transferring ' || amount || ' from Account ' || from_acc_id || ' to Account ' || to_acc_id);

  SELECT Balance INTO from_balance FROM Accounts WHERE AccountID = from_acc_id;

  IF from_balance < amount THEN
    DBMS_OUTPUT.PUT_LINE('? Error: Insufficient funds in Account ' || from_acc_id);
    RETURN;
  END IF;

  UPDATE Accounts SET Balance = Balance - amount WHERE AccountID = from_acc_id;
  UPDATE Accounts SET Balance = Balance + amount WHERE AccountID = to_acc_id;

  DBMS_OUTPUT.PUT_LINE('? Transfer completed.');
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('? Error: One or both accounts not found.');
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('? Unexpected Error: ' || SQLERRM);
END;
/

-- ======= Execution =======
BEGIN
  TransferFunds(1, 2, 1000);  -- Valid
  TransferFunds(1, 2, 10000); -- Insufficient funds
  TransferFunds(9, 2, 100);   -- Invalid account
END;
/

-- ======= After Execution =======

SELECT * FROM Accounts;
