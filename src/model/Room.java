package model;

import model.RoomType;
import java.util.*;

public class Room implements IRoom {

    private final String roomNumber;
    private final Double price;
    private final RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber(){
        return roomNumber;
    }

    @Override
    public Double getRoomPrice(){
        return price;
    }

    @Override
    public RoomType getRoomType(){
        return enumeration;
    }

    @Override
    public String toString() {
        return "Room number: " + roomNumber
                + ", Price: " + price
                + ", Type: " + enumeration;
    }

  
    // Overrides both the hashcode and equals methods to utilize Collections functions
    // Reference: https://mkyong.com/java/java-how-to-overrides-equals-and-hashcode/

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Room)) {
            return false;
        }
        final Room that = (Room) o;
        return Objects.equals(roomNumber, that.getRoomNumber()) ||
                (roomNumber == null && that.getRoomNumber() == null);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 + result + roomNumber.hashCode();
        return result;
    }
}
