package Model;

import java.awt.Point;
import java.io.Serializable;

import View.*;
import Model.*;
import Controller.*;

public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3787514693100801453L;

	public Door myDoors[];

	private final Room NORTH_ROOM;
	private final Room WEST_ROOM;
	
	private int myX;

	private int myY;

	private int myWidth;

	private int myHeight;

	private GUI gui;
	
	private Point myGridLocation;

	/// up is the same as North, right same as east, down same as south, west same as left
	public enum Direction{UP(0), RIGHT(1), DOWN(2), LEFT(3);
		private final int myValue;
		Direction(int theValue){ 
			myValue = theValue; 
		};
		public int getValue() { 
			return myValue; 
		}
		public int getOpposite() { 
			return (myValue+2)%4; 
		}
	}


	public Room(final int theX, final int theY, final int theWidth, final int theHeight, final Room theNorthRoom, final Room theWestRoom,
			final Door theSouthDoor, final Door theEastDoor){
		//sets values based on the parameters given
		NORTH_ROOM = theNorthRoom;
		WEST_ROOM = theWestRoom;
		myX = theX;
		myY = theY;
		myWidth = theWidth;
		myHeight = theHeight;
		myDoors[Direction.DOWN.getValue()] = theSouthDoor;
		myDoors[Direction.RIGHT.getValue()] = theEastDoor;

		//dynamically sets the north and west door based on if the room to the north/west of it exists
		//and if it does, and if there is a door in that room that connects to this room, sets the door that would connect back to be the same door object from that room
		if(NORTH_ROOM != null) {
			if(NORTH_ROOM.myDoors[Direction.DOWN.getValue()] != null) {
				myDoors[Direction.UP.getValue()] = NORTH_ROOM.myDoors[Direction.DOWN.getValue()];
			} else { myDoors[Direction.UP.getValue()] = null;}
		} else { myDoors[Direction.UP.getValue()] = null;}

		if(WEST_ROOM != null) {
			if(WEST_ROOM.myDoors[Direction.RIGHT.getValue()] != null) {
				myDoors[Direction.RIGHT.getValue()] = WEST_ROOM.myDoors[Direction.RIGHT.getValue()];
			} else { myDoors[Direction.RIGHT.getValue()] = null;}
		} else { myDoors[Direction.RIGHT.getValue()] = null;}
	}

	public Door getDoor(Direction theDirection) {
		return myDoors[theDirection.getValue()];
	}
	
	public void setDoor(Direction theDirection, Door theDoor) {
		if (myDoors[theDirection.getValue()] == null) {
			myDoors[theDirection.getValue()] = theDoor;
		}
	}
	
	public int getX() {
		return myGridLocation.x;
	}
	
	public int getY() {
		return myGridLocation.y;
	}
	
	
	
}