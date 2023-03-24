import api.*;
import model.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author seulgie
 */
public class MainMenu {

    private HotelResource hotelResource = HotelResource.getInstance();


    public static void mainMenu() {

        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        displayMainMenu();
        Boolean exit = false;

        while(!exit) {
            String line = scanner.nextLine();

            switch (line.charAt(0)) {
                case "1":
                    findAndReserveRoom();
                    break;
                case "2":
                    seeMyReservation();
                    break;
                case "3:
                    createAccount();
                    break;
                case "4":
                    AdminMenu.adminMenu();
                    break;
                case "5":
                    System.out.println("Exit");
                    exit= true;
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid choice\n");
                    break;
            }
        }
    }

    public static void displayMainMenu() {
        System.out.println("Welcome to the Hotel Reservation Application");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an Account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("Please select a number from the menu : ");
    }

    // #1 (getInputDate, printRooms, reserveRoom)
    private static void findAndReserveRoom() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your check-in date in the format of dd/mm/yyyy: ");
        Date checkInDate = getDate(scanner);
        System.out.println("Enter your check-out date in the format of dd/mm/yyyy: ");
        Date checkOutDate = getDate(scanner);

        if(checkInDate.before(checkOutDate)) {

            System.out.println("Available rooms for the given period: ");
            Collection<IRoom> availableRooms = HotelResource.findARoom(checkInDate, checkOutDate);

            if(availableRooms.isEmpty()) {
                System.out.println("Sorry, no rooms available for the given period! Instead, we can suggest alternatives 1 week later");
                // Reference : https://mkyong.com/java/java-how-to-add-days-to-current-date/
                Calendar c = Calendar.getInstance();
                c.setTime(checkInDate);
                c.add(Calendar.DATE, 7);
                checkInDate = c.getTime();

                c.setTime(checkOutDate);
                c.add(Calendar.DATE, 7);
                checkOutDate = c.getTime();

                System.out.println("Period between " + checkInDate + "and " + "checkOutDate");
                Collection<IRoom> alternativeRooms = HotelResource.findARoom(checkInDate, checkOutDate);

                if (!alternativeRooms.isEmpty()){
                    alternativeRooms.forEach(System.out::println);
                    reserveRoom(checkInDate, checkOutDate, alternativeRooms);
                } else {
                    System.out.println("Sorry, no available rooms for 1 week later!");
                    displayMainMenu();
                }
            }
            availableRooms.forEach(System.out::println);
            reserveRoom(checkInDate, checkOutDate, availableRooms);
        }
    }

    // sub 1-1
    private static Date getDate(Scanner scanner) {
        // Reference : https://stackoverflow.com/questions/27580655/how-to-set-a-date-as-input-in-java
        try {
            return new SimpleDateFormat("dd/mm/yyyy").parse(scanner.nextLine());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // sub 1-2
    private static void reserveRoom(Date checkInDate, Date checkOutDate, Collection<IRoom> rooms) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to book this room? (y/n)");
        String answerRoom = scanner.nextLine();

        if (answerRoom.equals("y")){
            System.out.println("Do you have an account? (y/n)");
            String answerAccount = scanner.nextLine();

            if (answerAccount.equals("y")) {
                System.out.println("Please enter your email: id@domain.com");
                String customerEmail = scanner.nextLine();

                System.out.println("Please enter a room number you would like to book.");
                String roomNumber = scanner.nextLine();

                IRoom room = HotelResource.getRoom(roomNumber);
                Reservation reservation = HotelResource.bookARoom(customerEmail, room, checkInDate, checkOutDate);
                System.out.println("Reservation successfully done!: " + reservation);

                // do reservation
            } else {
                createAccount();
                mainMenu();
            }
        } else { mainMenu(); }

    }

    // #2
    private static void seeMyReservation() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your email address :");
        String customerEmail = scanner.nextLine();

        Collection<Reservation> reservations = HotelResource.getCustomerReservations(customerEmail);
        if (reservations.isEmpty()) {
            System.out.println("No reservations yet!");
        } else {
            reservations.forEach(reservation -> System.out.println(reservation));
        }
    }

    // # 3
    private static void createAccount() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your email address: ");
        String email = scanner.nextLine();

        System.out.println("First name: ");
        String firstName = scanner.nextLine();

        System.out.println("Last name: ");
        String lastName = scanner.nextLine();

        HotelResource.createCustomer(email, firstName, lastName);
        System.out.println("Account successfully created!");
    }

}
