-- ========== Setup ==========
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Accounts CASCADE CONSTRAINTS';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    Balance NUMBER
);

INSERT INTO Accounts VALUES (1, 500);
INSERT INTO Accounts VALUES (2, 100);

-- ========== Procedure ==========
CREATE OR REPLACE PROCEDURE SafeTransferFunds(
  from_id NUMBER,
  to_id NUMBER,
  amount NUMBER
) IS
  source_balance NUMBER;
BEGIN
  DBMS_OUTPUT.PUT_LINE('--------------------------------------------------');
  DBMS_OUTPUT.PUT_LINE('Initiating transfer of ' || amount || ' from Account ' || from_id || ' to Account ' || to_id);
  DBMS_OUTPUT.PUT_LINE('--------------------------------------------------');

  SELECT Balance INTO source_balance FROM Accounts WHERE AccountID = from_id;
  DBMS_OUTPUT.PUT_LINE('? Source Account Balance: ' || source_balance);

  IF source_balance < amount THEN
    DBMS_OUTPUT.PUT_LINE('? Error: Insufficient funds in Account ' || from_id);
    RETURN;
  END IF;

  UPDATE Accounts SET Balance = Balance - amount WHERE AccountID = from_id;
  DBMS_OUTPUT.PUT_LINE('? Deducted ' || amount || ' from Account ' || from_id);

  UPDATE Accounts SET Balance = Balance + amount WHERE AccountID = to_id;
  DBMS_OUTPUT.PUT_LINE('? Added ' || amount || ' to Account ' || to_id);

  COMMIT;
  DBMS_OUTPUT.PUT_LINE('? Transfer successful.');
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('? Error: Source account not found.');
  WHEN OTHERS THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('? Unexpected Error: ' || SQLERRM);
END;
/

-- ========== Test Cases ==========
BEGIN
  SafeTransferFunds(1, 2, 100);  -- Success
  SafeTransferFunds(1, 2, 1000); -- Fail: insufficient funds
  SafeTransferFunds(9, 2, 50);   -- Fail: no source account
END;
/

-- ========== Output ==========
SELECT * FROM Accounts;
