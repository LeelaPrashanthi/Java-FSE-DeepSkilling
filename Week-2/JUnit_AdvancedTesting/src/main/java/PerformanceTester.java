public class PerformanceTester {

    public void performTask() {
        try {
            // Simulate some time-consuming task
            Thread.sleep(500);  // 500 milliseconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
