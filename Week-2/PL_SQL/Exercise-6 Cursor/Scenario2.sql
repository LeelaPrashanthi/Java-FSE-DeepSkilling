-- =====================================================
--  SCENARIO 2: Deduct Annual Fee from All Accounts
-- =====================================================

--  PL/SQL Block
DECLARE
  CURSOR fee_cursor IS
    SELECT AccountID, Balance FROM Accounts;

  v_fee NUMBER := 100;
BEGIN
  FOR acc IN fee_cursor LOOP
    IF acc.Balance >= v_fee THEN
      UPDATE Accounts
      SET Balance = Balance - v_fee
      WHERE AccountID = acc.AccountID;

      DBMS_OUTPUT.PUT_LINE('Fee of $' || v_fee || 
                           ' applied to Account ' || acc.AccountID);
    ELSE
      DBMS_OUTPUT.PUT_LINE('? Insufficient balance in Account ' || acc.AccountID);
    END IF;
  END LOOP;
END;
/

-- ? Check updated account balances
SELECT * FROM Accounts;
