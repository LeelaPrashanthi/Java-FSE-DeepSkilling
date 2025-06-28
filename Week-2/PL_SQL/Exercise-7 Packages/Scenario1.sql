-- ============================================
-- ? SCENARIO 1: CustomerManagement (3 Test Cases)
-- ============================================

--  Setup
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Customers CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Customers (
  CustomerID NUMBER PRIMARY KEY,
  Name VARCHAR2(100),
  Balance NUMBER
);

-- Package Specification
CREATE OR REPLACE PACKAGE CustomerManagement IS
  PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_balance NUMBER);
  PROCEDURE UpdateCustomerName(p_id NUMBER, p_newname VARCHAR2);
  FUNCTION GetBalance(p_id NUMBER) RETURN NUMBER;
END;
/

--  Package Body
CREATE OR REPLACE PACKAGE BODY CustomerManagement IS
  PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_balance NUMBER) IS
  BEGIN
    INSERT INTO Customers VALUES (p_id, p_name, p_balance);
    DBMS_OUTPUT.PUT_LINE('? Customer ' || p_name || ' added.');
  EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
      DBMS_OUTPUT.PUT_LINE('? Customer ID ' || p_id || ' already exists.');
  END;

  PROCEDURE UpdateCustomerName(p_id NUMBER, p_newname VARCHAR2) IS
  BEGIN
    UPDATE Customers SET Name = p_newname WHERE CustomerID = p_id;
    DBMS_OUTPUT.PUT_LINE('? Name updated for Customer ID ' || p_id);
  END;

  FUNCTION GetBalance(p_id NUMBER) RETURN NUMBER IS
    v_balance NUMBER;
  BEGIN
    SELECT Balance INTO v_balance FROM Customers WHERE CustomerID = p_id;
    RETURN v_balance;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN NULL;
  END;
END;
/

-- ? Test Cases
BEGIN
  CustomerManagement.AddCustomer(1, 'Alice', 2000);
  CustomerManagement.AddCustomer(2, 'Bob', 4000);
  CustomerManagement.AddCustomer(1, 'DuplicateID', 1000); -- duplicate

  CustomerManagement.UpdateCustomerName(2, 'Bobby');
  CustomerManagement.UpdateCustomerName(3, 'Ghost'); -- non-existent

  DBMS_OUTPUT.PUT_LINE('Balance (1): S' || CustomerManagement.GetBalance(1));
  DBMS_OUTPUT.PUT_LINE('Balance (2): $' || CustomerManagement.GetBalance(2));
  DBMS_OUTPUT.PUT_LINE('Balance (3): $' || NVL(CustomerManagement.GetBalance(3), 0)); -- null
END;
/

SELECT * FROM Customers;
