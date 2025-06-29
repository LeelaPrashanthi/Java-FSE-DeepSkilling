import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTest {

    @Test
    @Order(1)
    void testFirst() {
        System.out.println("Running test #1");
        assertTrue(10 > 5);
    }

    @Test
    @Order(2)
    void testSecond() {
        System.out.println("Running test #2");
        assertEquals(15, 10 + 5);
    }

    @Test
    @Order(3)
    void testThird() {
        System.out.println("Running test #3");
        assertNotNull("JUnit");
    }
}
