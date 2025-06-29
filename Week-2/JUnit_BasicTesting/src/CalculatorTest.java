import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        // Runs before each test
        calculator = new Calculator();
    }

    @After
    public void tearDown() {
        // Runs after each test
        calculator = null;
    }

    @Test
    public void testAdd() {
        // Arrange is done in setUp
        // Act
        int result = calculator.add(2, 3);
        // Assert
        assertEquals(5, result);
    }

    @Test
    public void testDivideByZero() {
        try {
            calculator.divide(10, 0);
            fail("Expected exception");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot divide by zero", e.getMessage());
        }
    }
}
