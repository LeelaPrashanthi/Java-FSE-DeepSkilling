import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionThrowerTest {

    @Test
    void testThrowsExceptionForNegative() {
        ExceptionThrower thrower = new ExceptionThrower();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            thrower.throwIfNegative(-1);
        });

        assertEquals("Negative number not allowed", exception.getMessage());
    }

    @Test
    void testDoesNotThrowForPositive() {
        ExceptionThrower thrower = new ExceptionThrower();

        // Should not throw anything
        assertDoesNotThrow(() -> thrower.throwIfNegative(10));
    }
}
