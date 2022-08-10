package Model;

import java.awt.Point;
import java.io.*;

import javax.swing.*;

import View.GUI;
import Model.Question;

public class Door implements Serializable{

	public enum AccessLevel {OPEN, CLOSED, LOCKED}

	private AccessLevel myAccess;

	private String myQuestion;

	private int myX;

	private int myY;

	private int myWidth;

	private int myHeight;

	private Question question;

	private GUI gui;

	private GUI messageText;

	GUI myGrid[][];

	private Point myGridLocation;

	public Door(int theX, int theY, int theWidth, int theHeight, String theQuestion, GUI rightText) {
		myX = theX;
		myY = theY;
		myWidth = theWidth;
		myHeight = theHeight;
		myAccess = AccessLevel.CLOSED;
		myQuestion = theQuestion;	
		messageText = rightText;
		
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

	// if answer is correct, open the door. otherwise the door is locked
	public void setOpenOrLock(Question theInput, GUI rightText, ImageIcon theDoor) {

		if(theInput.attempt(1)) {
			myAccess = AccessLevel.OPEN;
			theDoor.setImage(null);
			ImageIcon door = new ImageIcon(getClass().getClassLoader().getResource(""));
		} else {
			myAccess = AccessLevel.LOCKED;
			theDoor.setImage(null);
			if (!myGrid[myGridLocation.x][myGridLocation.y].hasPath()) {
				JOptionPane.showMessageDialog(null, "YOU LOSE!");
			}
			rightText.messageText.getAccessibleContext().setAccessibleDescription("The door is locked forever!");;
		}

		if(theInput.attempt(2)) {
			myAccess = AccessLevel.OPEN;
			theDoor.setImage(null);
		} else {
			myAccess = AccessLevel.LOCKED;
			theDoor.setImage(null);
			if (!myGrid[myGridLocation.x][myGridLocation.y].hasPath()) {
				JOptionPane.showMessageDialog(null, "YOU LOSE!");
			}
			rightText.messageText.getAccessibleContext().setAccessibleDescription("The door is locked forever!");;
		}

		if(theInput.attempt(3)) {
			myAccess = AccessLevel.OPEN;
			theDoor.setImage(null);
		} else {
			myAccess = AccessLevel.LOCKED;
			theDoor.setImage(null);
			if (!myGrid[myGridLocation.x][myGridLocation.y].hasPath()) {
				JOptionPane.showMessageDialog(null, "YOU LOSE!");
			}
			rightText.messageText.getAccessibleContext().setAccessibleDescription("The door is locked forever!");;
		}

		if(theInput.attempt(4)) {
			myAccess = AccessLevel.OPEN;
			theDoor.setImage(null);
		} else {
			myAccess = AccessLevel.LOCKED;
			theDoor.setImage(null);
			if (!myGrid[myGridLocation.x][myGridLocation.y].hasPath()) {
				JOptionPane.showMessageDialog(null, "YOU LOSE!");
			}
			rightText.messageText.getAccessibleContext().setAccessibleDescription("The door is locked forever!");;
		}
	}

}
