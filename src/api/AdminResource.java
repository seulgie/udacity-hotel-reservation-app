package api;

import model.*;
import service.*;

import java.util.*;

/**
 * @author : seulgie
 */

public class AdminResource {

    // Reference: https://www.baeldung.com/java-singleton
    private static AdminResource INSTANCE;

    private final CustomerService customerService = CustomerService.getInstance();
    private final ReservationService reservationService = ReservationService.getInstance();

    private AdminResource() {}

    public static AdminResource getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new AdminResource();
        }
        return INSTANCE;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom(Set<IRoom> rooms) {
        // Reference: https://www.programiz.com/java-programming/examples/iterate-over-set
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.printAllReservation();
    }
}
