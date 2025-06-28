-- ========== Setup ==========
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Customers CASCADE CONSTRAINTS';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Customers (
  CustomerID NUMBER PRIMARY KEY,
  Name VARCHAR2(100)
);

INSERT INTO Customers VALUES (1, 'John Doe');

-- ========== Procedure ==========
CREATE OR REPLACE PROCEDURE AddNewCustomer(
  cid NUMBER,
  cname VARCHAR2
) IS
BEGIN
  DBMS_OUTPUT.PUT_LINE('--------------------------------------------------');
  DBMS_OUTPUT.PUT_LINE('Adding new customer: ' || cname || ' (ID: ' || cid || ')');
  DBMS_OUTPUT.PUT_LINE('--------------------------------------------------');

  INSERT INTO Customers VALUES (cid, cname);
  DBMS_OUTPUT.PUT_LINE('? Customer added successfully.');
EXCEPTION
  WHEN DUP_VAL_ON_INDEX THEN
    DBMS_OUTPUT.PUT_LINE('? Error: Customer with ID ' || cid || ' already exists.');
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('? Unexpected Error: ' || SQLERRM);
END;
/

-- ========== Test Cases ==========
BEGIN
  AddNewCustomer(1, 'John Doe');    -- Duplicate
  AddNewCustomer(2, 'Jane Smith');  -- Success
END;
/

-- ========== Output ==========
SELECT * FROM Customers;
