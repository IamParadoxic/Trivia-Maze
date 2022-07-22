package Maze;

import java.io.*;

public class Door implements Serializable{

	public enum AccessLevel {OPEN, CLOSED, LOCKED}

	private AccessLevel myAccess;
	
	private String myQuestion;

	private int myX;
	
	private int myY;
	
	private int myWidth;
	
	private int myHeight;
	
	public Door(int theX, int theY, int theWidth, int theHeight, String theQuestion) {
		myX = theX;
		myY = theY;
		myWidth = theWidth;
		myHeight = theHeight;
		myAccess = AccessLevel.CLOSED;
		myQuestion = theQuestion;	
	}
	
	public boolean isOpened() {
		boolean doorOpened = false;
		if (myAccess.equals(AccessLevel.OPEN)) {
			doorOpened = true;
		}
		return doorOpened;
	}
	
	public boolean isClosed() {
		boolean doorClosed = false;
		if (myAccess.equals(AccessLevel.CLOSED)) {
			doorClosed = true;
		}
		return doorClosed;
	}
	
	public boolean isLocked() {
		boolean doorLocked = false;
		if (myAccess.equals(AccessLevel.LOCKED)) {
			doorLocked = true;
		}
		return doorLocked;
	}
	
	public AccessLevel getAccessLevel() {
		return myAccess;
	}
	
	public void setAccessLevel(AccessLevel theAccessLevel) {
		myAccess = theAccessLevel;
	}
	
	// if answer is correct, open the door
	public boolean setOpen() {
		return false; // add function
		
	}
	
	// if answer is not correct, lock the door
	public boolean setLock() {
		return false; // add function
		
	}

}
