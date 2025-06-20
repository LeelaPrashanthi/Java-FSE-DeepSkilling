public class CustomerService {
    private final CustomerRepository customerRepository;

    // Constructor Injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void displayCustomer(String customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer != null) {
            System.out.println("Customer ID   : " + customer.getId());
            System.out.println("Customer Name : " + customer.getName());
        } else {
            System.out.println("Customer not found with ID: " + customerId);
        }
    }
}

