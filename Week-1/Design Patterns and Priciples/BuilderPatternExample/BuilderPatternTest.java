public class BuilderPatternTest {
    public static void main(String[] args) {
        // Basic configuration
        Computer basicComputer = new Computer.Builder("Intel i5", "16GB")
                .build();

        System.out.println("=== Basic Computer ===");
        basicComputer.printConfiguration();

        System.out.println();

        // High-end configuration
        Computer gamingComputer = new Computer.Builder("12th Gen IntelCore i5", "16GB")
                .setStorage("1TB SSD")
                .setGraphicsCard("Intel(R) UHD Graphics")
                .setOperatingSystem("Windows 11")
                .build();

        System.out.println("=== Light Weight Laptop ===");
        gamingComputer.printConfiguration();
    }
}
