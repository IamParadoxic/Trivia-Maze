package GUI;

import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;

import GUI.Question;
import GUI.GUI;

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

	// if answer is correct, open the door. otherwise the door is locked
	public void setOpenOrLock(Question theInput, GUI rightText, ImageIcon theDoor) {
		
		if(theInput.attempt(1)) {
			myAccess = AccessLevel.OPEN;
			theDoor.setImage(null);
//			ImageIcon door = new ImageIcon(getClass().getClassLoader().getResource(""));
		} else {
			myAccess = AccessLevel.LOCKED;
//			rightText.messageText1.getAccessibleContext().setAccessibleDescription("The door is locked forever!");;
		}
		
		if(theInput.attempt(2)) {
			myAccess = AccessLevel.OPEN;
		} else {
			myAccess = AccessLevel.LOCKED;
//			rightText.messageText1.getAccessibleContext().setAccessibleDescription("The door is locked forever!");;
		}
		
		if(theInput.attempt(3)) {
			myAccess = AccessLevel.OPEN;
		} else {
			myAccess = AccessLevel.LOCKED;
//			rightText.messageText1.getAccessibleContext().setAccessibleDescription("The door is locked forever!");;
		}
		
		if(theInput.attempt(4)) {
			myAccess = AccessLevel.OPEN;
		} else {
			myAccess = AccessLevel.LOCKED;
//			rightText.messageText1.getAccessibleContext().setAccessibleDescription("The door is locked forever!");;
		}


	}

}
