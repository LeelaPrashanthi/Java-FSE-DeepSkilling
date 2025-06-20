public class DependencyInjectionTest {
    public static void main(String[] args) {
        // Create repository
        CustomerRepository repository = new CustomerRepositoryImpl();

        // Inject repository into service
        CustomerService service = new CustomerService(repository);

        // Use the service
        service.displayCustomer("C001");
        System.out.println("---");
        service.displayCustomer("C003"); // Non-existent ID
    }
}
