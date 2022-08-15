package Model;

import java.awt.Point;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JTextArea;

import View.*;

public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3787514693100801453L;

	public Door myDoors[];

	private int myX;

	private int myY;

	private int myWidth;

	private int myHeight;

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