import java.util.HashMap;
import java.util.Map;

public class CustomerRepositoryImpl implements CustomerRepository {
    private final Map<String, Customer> customerDatabase = new HashMap<>();

    public CustomerRepositoryImpl() {
        // Pre-populate with sample data
        customerDatabase.put("C001", new Customer("C001", "Alice"));
        customerDatabase.put("C002", new Customer("C002", "Bob"));
    }

    @Override
    public Customer findCustomerById(String id) {
        return customerDatabase.get(id);
    }
}
