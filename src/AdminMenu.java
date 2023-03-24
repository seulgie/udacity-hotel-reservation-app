import api.*;
import model.*;
import java.util.*;
import java.text.ParseException;

/**
 * @author seulgie
 */

public class AdminMenu {

    private static AdminResource adminResource = AdminResource.getInstance();


    public static void adminMenu() {

        Scanner scanner = new Scanner(System.in); //Create a Scanner object
        displayAdminMenu();
        Boolean exit = false;

        while(!exit) {
            String line = scanner.nextLine();

            switch (line.charAt(0)) {
                case "1":
                    for (Customer customer : adminResource.getAllCustomers()) {
                        System.out.println(customer);
                    }
                    break;
                case "2":
                    Collection<IRoom> rooms = adminResource.getAllRooms();
                    for (IRoom room : rooms) {
                        System.out.println(room);
                    }
                    break;
                case "3":
                    adminResource.displayAllReservations();
                    break;
                case "4":
                    // To-Do
                    break;
                case "5":
                    MainMenu.mainMenu();
                    break;
                default:
                    System.out.println("Invalid choice\n");
                    break;

            }
        }
    }

    public static void displayAdminMenu() {
        System.out.println("Admin Menu");
        System.out.println("1. See all customers");
        System.out.println("2. See all rooms");
        System.out.println("3. See all reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Bak to Main Menu");
        System.out.println("Please select the number from the menu option: ");
    }

    private void addRoom() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Add a room number: ");
        String roomNumber = scanner.nextLine();

        System.out.println("Add price per night: ");
        // Reference : https://www.javatpoint.com/post/java-scanner-nextdouble-method
        Double roomPrice = scanner.nextDouble();

        System.out.println("Add a room type (1 for single bed, 2 for double bed): ");
        RoomType type = null;
        if (type.equals("1")) {
            type = RoomType.SINGLE;
        } else type = RoomType.DOUBLE;

        Room room = new Room(roomNumber, roomPrice, type);
        adminResource.addRoom((Set<IRoom>) room);

        System.out.println("Rooms successfully added! ");
        displayAdminMenu();
    }

}
