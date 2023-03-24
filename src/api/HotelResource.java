package api;

import model.*;
import service.*;

import java.util.*;

/**
 * @author : seulgie
 */

public class HotelResource {

    // Reference: https://www.baeldung.com/java-singleton
    private static HotelResource INSTANCE;

    private static final CustomerService customerService = CustomerService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();

    private HotelResource() {}

    public static HotelResource getInstance() {
        if(INSTANCE == null) INSTANCE = new HotelResource();
        return INSTANCE;
    }

    /**
     * Service to receive a customer with the given email.
     *
     * @param email of the customer
     * @return customer info of that email
     */
    public static Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    /**
     * Put the new customer data
     *
     * @param email : customer email
     * @param firstName : customer first name
     * @param lastName : customer last name
     */
    public static void createCustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);

    }

    /**
     * Get a room info which corresponds to the given room number
     *
     * @param roomNumber : the room number
     * @return iRoom of that room number
     */
    public static IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    /**
     * Book a room for the registered customer with the given email
     *
     * @param customerEmail : email of the customer who booked that room
     * @param room : room to book
     * @param checkInDate : check-in date
     * @param checkOutDate : check-out date
     * @return : booked reservation info
     */
    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        return reservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    /**
     * Receive all the reservation history with the given email account
     *
     * @param customerEmail : customer email who booked before
     * @return collections of all the recorded reservations of that customer
     */
    public static Collection<Reservation> getCustomerReservations(String customerEmail) {
        Customer customer = getCustomer(customerEmail);
        return reservationService.getCustomersReservation(customer);
    }

    /**
     * Check all available rooms for the given period
     *
     * @param checkIn : check-in date
     * @param checkOut : check-out date
     * @return : available rooms from the given period
     */
    public static Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }
}
