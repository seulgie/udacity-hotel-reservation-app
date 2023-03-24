package service;
import java.util.*;
import model.*;

/**
 * Service to manage all the
 * @author seulgie
 */

public class ReservationService {

    // Reference: https://www.baeldung.com/java-singleton
    private static ReservationService INSTANCE;
    private final Map<String, IRoom> rooms = new HashMap<>();
    private final Map<String, Collection<Reservation>> reservations = new HashMap<>(); // key: customer E-mail

    private ReservationService() {
    }

    // Returns the singleton service instance.
    public static ReservationService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReservationService();
        }
        return INSTANCE;
    }


    /**
     * Put the room info with the given room number
     *
     * @param room : a room object to record new
     */
    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    /**
     * Get a room with the given room ID
     *
     * @param roomId : room ID
     * @return : iRoom with the given room ID
     */
    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    /**
     * Record a new reservation with the given info
     *
     * @param customer     : customer who tries to book a room
     * @param room         : iRoom to book
     * @param checkInDate  : check-in date
     * @param checkOutDate : check-out date
     * @return : newly created reservation transaction
     */

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {

        Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);

        // Create Collections to store and retrieve a Reservation of that customer
        Collection<Reservation> thatCustomerReservations = getCustomersReservation(customer);
        thatCustomerReservations.add(newReservation);

        // put this new reservation to the global reservations using customer e-mail as a key
        reservations.put(customer.getEmail(), thatCustomerReservations);
        return newReservation;
    }

    /**
     * Get all reservations done by the given customer
     *
     * @param customer : that customer
     * @return : collection of all reservations for that customer
     */
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        // Reference: https://www.geeksforgeeks.org/hashmap-get-method-in-java/
        return reservations.get(customer.getEmail());
    }

    /**
     *
     */
    public void printAllReservation() {
        // Reference: https://www.techiedelight.com/print-all-keys-and-values-map-java/
        reservations.keySet().iterator().forEachRemaining(System.out::println);
    }

    /**
     * Get all the available rooms for a given dates
     *
     * @param checkInDate  : check-in date
     * @param checkOutDate : check-out date
     * @return : collection of all rooms available on the given period
     */
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        // Copy all rooms
        Map<String, IRoom> availableRooms = new HashMap<>(rooms);

        ArrayList<Reservation> allReservations = new ArrayList<>();
        // Put all the reservations from the Hashmap 'reservations'
        for (Collection<Reservation> reservations : reservations.values()) {
            allReservations.add((Reservation) reservations);
        }

        // Compare given dates with reservations
        // Reference : https://www.geeksforgeeks.org/calendar-before-method-in-java/?ref=rp

        // Delete an unavailable room from all available rooms
        for (Reservation reservation: allReservations) {
            if (reservation.getCheckInDate().before(checkInDate) && reservation.getCheckOutDate().after(checkOutDate)) {
                Object roomNumber = reservation.getRoom().getRoomNumber();
                availableRooms.remove(roomNumber);
            }
        }
        return availableRooms.values();
    }
}
