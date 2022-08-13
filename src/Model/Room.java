package Model;

import java.awt.Point;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import View.*;
import Model.Maze;
import Model.Door;
import Controller.*;

public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3787514693100801453L;

//	private static final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;

	public Door myDoors[];

//	private final Room NORTH_ROOM;
//	private final Room WEST_ROOM;
	//private final Room EAST_ROOM;
	//private final Room SOUTH_ROOM;

	private int myX;

	private int myY;

	private int myWidth;

	private int myHeight;

	private GUI gui;
	
	private Point myGridLocation;


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


//	public Room(final int theX, final int theY, final int theWidth, final int theHeight, final Room theNorthRoom, final Room theWestRoom,
//			final Door theSouthDoor, final Door theEastDoor){
//		//sets values based on the parameters given
//		NORTH_ROOM = theNorthRoom;
//		WEST_ROOM = theWestRoom;
//		myX = theX;
//		myY = theY;
//		myWidth = theWidth;
//		myHeight = theHeight;
//		myDoors[SOUTH] = theSouthDoor;
//		myDoors[EAST] = theEastDoor;
//
//		//dynamically sets the north and west door based on if the room to the north/west of it exists, and if it does, and if there is a door in that room that connects to this room, sets the door that would connect back to be the same door object from that room
//		if(NORTH_ROOM != null) {
//			if(NORTH_ROOM.myDoors[SOUTH] != null) {
//				myDoors[NORTH] = NORTH_ROOM.myDoors[SOUTH];
//			} else { myDoors[NORTH] = null;}
//		} else { myDoors[NORTH] = null;}
//
//		if(WEST_ROOM != null) {
//			if(WEST_ROOM.myDoors[EAST] != null) {
//				myDoors[WEST] = WEST_ROOM.myDoors[EAST];
//			} else { myDoors[WEST] = null;}
//		} else { myDoors[WEST] = null;}
//	}
	
	public Room(int theX, int theY, int theWidth, int theHeight, Point theGridLocation) {
		myGridLocation = theGridLocation;
		myX = theX;
		myY = theY;
		myWidth = theWidth;
		myHeight = theHeight;
		myDoors = new Door[4];
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
	
	public boolean isLocked(Direction theDirection) {
		return myDoors[theDirection.getValue()].isLocked();
	}
	
	public void setOpenOrLock(Direction theDirection, JButton theInputPanel, JTextArea theMessageText, Maze theMazeMap, GUI theGUI) {
		myDoors[theDirection.getValue()].setOpenOrLock(theInputPanel, theMessageText, theMazeMap, theGUI);
	}
	
	public void draw(Maze theMazeMap) {
		
		theMazeMap.addRoom(myX + 5, myY + 5, myWidth - 10, myHeight - 10);
		
		for (int i = 0; i < 4; i++) {
			if (myDoors[i] != null){
				myDoors[i].draw(theMazeMap);
			}
		}
	}
	
	
}