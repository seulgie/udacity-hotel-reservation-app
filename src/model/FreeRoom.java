package model;
import model.RoomType;

/**
 * @author seulgie
 * Create the FreeRoom lass
 */

public class FreeRoom extends Room {

    // Constructor
    public FreeRoom(String roomNumber, RoomType enumeration) {
        super(roomNumber, 0.0, enumeration);
    }
    
    @Override
    public String toString() {
        return "Free room: " + super.toString();
    }
}
