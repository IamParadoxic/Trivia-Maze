package Model;

import java.awt.Point;
import java.io.Serializable;
import javax.swing.*;

import View.GUI;

public class Room implements Serializable {

	private static final long serialVersionUID = 3787514693100801453L;

	private Door myDoors[];
	private int myX;
	private int myY;
	private int myWidth;
	private int myHeight;
	private Point myGridLocation;
	private boolean myHasChest = true;

	public enum Direction {

		UP(0), RIGHT(1), DOWN(2), LEFT(3);

		private final int myValue;

		Direction(int theValue) {
			myValue = theValue;
		};

		public int getValue() {
			return myValue;
		}

		public int getOpposite() {
			return (myValue + 2) % 4;
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

	/**
	 * Get door with corresponding index number.
	 * 
	 * @param theIndex index number
	 * @return corresponding door object
	 */
	public Door getDoor(int theIndex) {
		
		return myDoors[theIndex];
		
	}

	/**
	 * Get door with corresponding direction.
	 * 
	 * @param theDirection door direction
	 * @return corresponding door object
	 */
	public Door getDoor(Direction theDirection) {
		
		return myDoors[theDirection.getValue()];
		
	}

	/**
	 * Set door status.
	 * 
	 * @param theDirection door direction
	 * @param theDoor corresponding door
	 */
	public void setDoor(Direction theDirection, Door theDoor) {
		
		if (myDoors[theDirection.getValue()] == null)
			myDoors[theDirection.getValue()] = theDoor;
		
	}

	/**
	 * Get coordinate x
	 * 
	 * @return coordinate x
	 */
	public int getX() {
		return myGridLocation.x;
	}

	/**
	 * Ge4t coordinate y
	 * 
	 * @return coordinate y
	 */
	public int getY() {
		return myGridLocation.y;
	}

	/**
	 * To see if the door is locked
	 * 
	 * @param theDirection door direction
	 * @return true if locked
	 */
	public boolean isLocked(Direction theDirection) {
		
		return myDoors[theDirection.getValue()].isLocked();
		
	}

	/**
	 * Set open and lock door status.
	 * 
	 * @param theDirection door direction
	 * @param theInputPanel the input box
	 * @param theMessageText the message in box
	 * @param theMazeMap the game map
	 * @param theGUI the game user interface
	 */
	public void setOpenOrLock(Direction theDirection, JButton theInputPanel, JTextArea theMessageText, Maze theMazeMap, GUI theGUI) {
		
		myDoors[theDirection.getValue()].setOpenOrLock(theInputPanel, theMessageText, theMazeMap, theGUI);
		
	}

	/**
	 * Draw the room.
	 * 
	 * @param theMazeMap the game map
	 */
	public void draw(Maze theMazeMap) {

		theMazeMap.addRoom(myX + 5, myY + 5, myWidth - 10, myHeight - 10);

		for (int i = 0; i < 4; i++) {
			if (myDoors[i] != null) {
				myDoors[i].draw(theMazeMap);
			}
		}
	}

	/**
	 * Set if there is unopened chest.
	 */
	public void setHasChest(boolean TF) {
		myHasChest = TF;
	}
	
	/**
	 * Get the chest status.
	 * 
	 * @return if there is unopened chest
	 */
	public boolean getHasChest() {
		return myHasChest;
	}
}