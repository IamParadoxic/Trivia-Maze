package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

import View.GUI;

public class Door implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2547110088715481646L;

	public enum AccessLevel {OPEN, CLOSED, LOCKED}

	private AccessLevel myAccess;

	private int myX;

	private int myY;

	private int myWidth;

	private int myHeight;

	private Question myQuestion;

	GUI myGrid[][];

	public Door(int theX, int theY, int theWidth, int theHeight) {
		myX = theX;
		myY = theY;
		myWidth = theWidth;
		myHeight = theHeight;
		myAccess = AccessLevel.LOCKED;

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

	public void draw(Maze theMazeMap) {
		theMazeMap.addDoor(myX, myY, myWidth, myHeight);
	}

	public void addOpen(Maze theMazeMap) {
		theMazeMap.addOpen(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
	}

	public void addLock(Maze theMazeMap) {
		theMazeMap.addLock(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
	}

	// if answer is correct, open the door. otherwise the door is locked
	public void setOpenOrLock(JButton theInputPanel, JTextArea theMessageText, Maze theMazeMap, GUI theGUI) {

		if (myAccess.equals(AccessLevel.CLOSED)) {

			theInputPanel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (myQuestion.setOpenOrLock(1)) {
						myAccess = AccessLevel.OPEN;
						theMazeMap.addOpen(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
					} else {
						myAccess = AccessLevel.LOCKED;
						theMazeMap.addLock(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
					}
					if(!theGUI.hasPath()) {
						JOptionPane.showMessageDialog(null, "YOU LOSE!");
					}

				}

			});

		}
	}
}
