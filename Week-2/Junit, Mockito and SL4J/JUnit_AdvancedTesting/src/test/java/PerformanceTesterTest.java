import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class PerformanceTesterTest {

    @Test
    void testTaskCompletesWithinTimeout() {
        PerformanceTester tester = new PerformanceTester();

        assertTimeout(Duration.ofMillis(1000), () -> {
            tester.performTask();
        });
    }

    @Test
    void testFailsIfTaskExceedsTimeout() {
        PerformanceTester tester = new PerformanceTester();

        assertTimeoutPreemptively(Duration.ofMillis(300), () -> {
            tester.performTask();  // This takes 500ms
        });
    }
}
