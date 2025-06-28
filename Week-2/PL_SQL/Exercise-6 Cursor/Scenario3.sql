-- =========================================================
-- ? SCENARIO 3: Update Loan Interest Rates via Cursor
-- =========================================================

-- ? Setup
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Loans CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Loans (
  LoanID NUMBER PRIMARY KEY,
  CustomerID NUMBER,
  LoanAmount NUMBER,
  InterestRate NUMBER
);

-- ? Test Data
INSERT INTO Loans VALUES (1, 1, 10000, 5);
INSERT INTO Loans VALUES (2, 2, 15000, 6);

-- ? PL/SQL Block
DECLARE
  CURSOR loan_cursor IS
    SELECT LoanID, InterestRate FROM Loans;

  v_increment NUMBER := 0.5;
BEGIN
  FOR loan IN loan_cursor LOOP
    UPDATE Loans
    SET InterestRate = InterestRate + v_increment
    WHERE LoanID = loan.LoanID;

    DBMS_OUTPUT.PUT_LINE('Interest rate for Loan ' || loan.LoanID ||
                         ' updated to ' || (loan.InterestRate + v_increment) || '%');
  END LOOP;
END;
/

-- ? Check updated loan interest rates
SELECT * FROM Loans;
