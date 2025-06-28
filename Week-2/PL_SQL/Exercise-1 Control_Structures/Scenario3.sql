-- Setup required table
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Loans CASCADE CONSTRAINTS';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    EndDate DATE
);

INSERT INTO Loans VALUES (1, 1, SYSDATE + 10);
INSERT INTO Loans VALUES (2, 2, SYSDATE + 40);

-- PL/SQL block
BEGIN
  FOR loan IN (
    SELECT LoanID, CustomerID, EndDate
    FROM Loans
    WHERE EndDate BETWEEN SYSDATE AND SYSDATE + 30
  ) LOOP
    DBMS_OUTPUT.PUT_LINE('Reminder: LoanID ' || loan.LoanID ||
                         ' for CustomerID ' || loan.CustomerID ||
                         ' is due on ' || TO_CHAR(loan.EndDate, 'DD-MON-YYYY'));
  END LOOP;
END;
/

-- Output Schema Verification
SELECT * FROM Loans;
