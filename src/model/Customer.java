package model;
import java.util.*;

/**
 * A customer who search for an available room to book.
 * Basic information about that customer.
 * @author seulgie
 */
public class Customer {

    private String firstName;
    private String lastName;
    private String email;

    public class Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isValid(email);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }


    // Reference: https://rollbar.com/blog/how-to-throw-illegalargumentexception-in-java/
    public void isValid(final String email) {
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("This email format is invalid");
        }
    }

    @Override
    public String toString() {
        return "First Name: " + firstName
                + ", Last Name: " + lastName
                + ", Email: " + email;
    }

    // Override hashCode and Equals
    // Reference: https://www.baeldung.com/java-equals-hashcode-contracts
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Customer))
            return false;
        Customer that = (Customer) o;
        return Objects.equals(this.email, that.email);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + email.hashCode();
        return result;
    }
}
