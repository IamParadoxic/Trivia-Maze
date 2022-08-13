package Model;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

import View.GUI;
import Model.Question;
import Model.Maze;
import Model.Room;

public class Door implements Serializable{

	public enum AccessLevel {OPEN, CLOSED, LOCKED}

	private AccessLevel myAccess;

	private int myX;

	private int myY;

	private int myWidth;

	private int myHeight;

	private Question myQuestion;

	private GUI gui;

	private GUI messageText;

	GUI myGrid[][];

	private Point myGridLocation;

	public Door(int theX, int theY, int theWidth, int theHeight) {
		myX = theX;
		myY = theY;
		myWidth = theWidth;
		myHeight = theHeight;
		myAccess = AccessLevel.CLOSED;

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
	
	public void addOpen(Maze theMapPanel) {
		theMapPanel.addOpen(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
	}
	
	public void addLock(Maze theMapPanel) {
		theMapPanel.addLock(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
	}

	// if answer is correct, open the door. otherwise the door is locked
	public void setOpenOrLock(JButton theInputPanel, JTextArea theMessageText, Maze theMazeMap, GUI theGUI) {

		if (myAccess.equals(AccessLevel.CLOSED)) {


			theInputPanel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(myQuestion.attempt(1)) {
						myAccess = AccessLevel.OPEN;
					} else {
						myAccess = AccessLevel.LOCKED;
						if (!theGUI.hasPath()) {
							JOptionPane.showMessageDialog(null, "YOU LOSE!");
						}

					}
					theMazeMap.repaint();
				}
			});

			theInputPanel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(myQuestion.attempt(2)) {
						myAccess = AccessLevel.OPEN;
					} else {
						myAccess = AccessLevel.LOCKED;
						if (!theGUI.hasPath()) {
							JOptionPane.showMessageDialog(null, "YOU LOSE!");
						}

					}
					theMazeMap.repaint();
				}
			});

			theInputPanel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(myQuestion.attempt(3)) {
						myAccess = AccessLevel.OPEN;
					} else {
						myAccess = AccessLevel.LOCKED;
						if (!theGUI.hasPath()) {
							JOptionPane.showMessageDialog(null, "YOU LOSE!");
						}

					}
					theMazeMap.repaint();
				}
			});

			theInputPanel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(myQuestion.attempt(4)) {
						myAccess = AccessLevel.OPEN;
					} else {
						myAccess = AccessLevel.LOCKED;
						if (!theGUI.hasPath()) {
							JOptionPane.showMessageDialog(null, "YOU LOSE!");
						}

					}
					theMazeMap.repaint();
				}
			});
		}
	}
}
