-- ? SCENARIO 3: CHECK IF SUFFICIENT BALANCE EXISTS
-- =========================

BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Accounts CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Accounts (
  AccountID NUMBER PRIMARY KEY,
  Balance NUMBER
);

-- Insert Test Data
INSERT INTO Accounts VALUES (1, 8000);
INSERT INTO Accounts VALUES (2, 400);
INSERT INTO Accounts VALUES (3, 0);

-- Create Function
CREATE OR REPLACE FUNCTION HasSufficientBalance(
  acc_id NUMBER,
  amount NUMBER
) RETURN BOOLEAN IS
  acc_balance NUMBER;
BEGIN
  SELECT Balance INTO acc_balance FROM Accounts WHERE AccountID = acc_id;
  RETURN acc_balance >= amount;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN FALSE;
END;
/

-- Test Calls
DECLARE
  res1 BOOLEAN;
  res2 BOOLEAN;
  res3 BOOLEAN;
BEGIN
  res1 := HasSufficientBalance(1, 5000);
  res2 := HasSufficientBalance(2, 500);
  res3 := HasSufficientBalance(3, 1);

  IF res1 THEN
    DBMS_OUTPUT.PUT_LINE('? Account 1 has sufficient balance.');
  ELSE
    DBMS_OUTPUT.PUT_LINE('? Account 1 has insufficient balance.');
  END IF;

  IF res2 THEN
    DBMS_OUTPUT.PUT_LINE('? Account 2 has sufficient balance.');
  ELSE
    DBMS_OUTPUT.PUT_LINE('? Account 2 has insufficient balance.');
  END IF;

  IF res3 THEN
    DBMS_OUTPUT.PUT_LINE('? Account 3 has sufficient balance.');
  ELSE
    DBMS_OUTPUT.PUT_LINE('? Account 3 has insufficient balance.');
  END IF;
END;
/
