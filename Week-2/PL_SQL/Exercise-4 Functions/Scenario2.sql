--  SCENARIO 2: CALCULATE MONTHLY INSTALLMENT
-- =========================
-- Create Function
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
  principal NUMBER,
  annual_rate NUMBER,
  years NUMBER
) RETURN NUMBER IS
  monthly_rate NUMBER := annual_rate / (12 * 100);
  months NUMBER := years * 12;
  emi NUMBER;
BEGIN
  IF monthly_rate = 0 THEN
    emi := principal / months;
  ELSE
    emi := (principal * monthly_rate) / (1 - POWER(1 + monthly_rate, -months));
  END IF;
  RETURN ROUND(emi, 2);
END;
/

-- Test Cases
DECLARE
  emi1 NUMBER;
  emi2 NUMBER;
  emi3 NUMBER;
BEGIN
  emi1 := CalculateMonthlyInstallment(100000, 12, 5);  -- Normal Case
  emi2 := CalculateMonthlyInstallment(50000, 0, 2);    -- Zero Interest
  emi3 := CalculateMonthlyInstallment(200000, 10, 10); -- Long Duration

  DBMS_OUTPUT.PUT_LINE('EMI for ?100K @12% for 5yrs: ?' || emi1);
  DBMS_OUTPUT.PUT_LINE('EMI for ?50K @0% for 2yrs: ?' || emi2);
  DBMS_OUTPUT.PUT_LINE('EMI for ?200K @10% for 10yrs: ?' || emi3);
END;
/
