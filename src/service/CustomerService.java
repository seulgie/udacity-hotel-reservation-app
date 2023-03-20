package service;
import model.Customer;
import java.util.*;

/**
 * @author: seulgie
 */

public class CustomerService {

    // Reference: https://www.baeldung.com/java-singleton
    private static CustomerService INSTANCE;

    private final Map<String, Customer> customers = new HashMap<>();

    private CustomerService() {}

    // Returns the singleton service instance.
    public static CustomerService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CustomerService();
        }
        return INSTANCE;
    }


    public void addCustomer(String email, String firstName, String lastName) {
        customers.put(email, new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String customerEmail){
        return customers.get(customerEmail);
    }

    // Process data for an application in a Collection
    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }
}
