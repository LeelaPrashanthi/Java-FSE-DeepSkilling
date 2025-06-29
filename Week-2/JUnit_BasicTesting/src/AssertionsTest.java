import org.junit.Test;
import static org.junit.Assert.*;

public class AssertionsTest {

    @Test
    public void testAssertions() {
        assertEquals(5, 2 + 3);
        assertTrue(10 > 1);
        assertFalse(3 > 6);
        assertNull(null);
        assertNotNull(new Object());
    }
}
