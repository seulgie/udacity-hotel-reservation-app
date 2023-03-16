package model;

import model.RoomType;

/**
 * @author seulgie
 * Create the IRoom Interface
 */

public interface IRoom {
    public String getRoomNumber();
    public Double getRoomPrice();
    public RoomType getRoomType();
    public boolean isFree();
}
