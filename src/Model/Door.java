package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.*;

import View.GUI;

public class Door implements Serializable{

	private static final long serialVersionUID = 5763380582064286175L;

	public enum AccessLevel {
		OPEN, CLOSED, LOCKED
	}

	private AccessLevel myAccess;
	private int myX;
	private int myY;
	private int myWidth;
	private int myHeight;
	private Question myQuestion;
	private boolean mySetOAD = true;

	public Door(int theX, int theY, int theWidth, int theHeight, Question theQuestion) {

		myX = theX;
		myY = theY;
		myWidth = theWidth;
		myHeight = theHeight;
		myAccess = AccessLevel.CLOSED;
		myQuestion = theQuestion;

	}

	/**
	 * To see if the door status is opened.
	 * 
	 * @return true if open
	 */
	public boolean isOpened() {
		boolean doorOpened = false;

		if (myAccess.equals(AccessLevel.OPEN)) {
			doorOpened = true;
		}
		return doorOpened;
	}

	/**
	 * To see if the door status is closed.
	 * 
	 * @return true if closed
	 */
	public boolean isClosed() {
		boolean doorClosed = false;

		if (myAccess.equals(AccessLevel.CLOSED)) {
			doorClosed = true;
		}
		return doorClosed;
	}

	/**
	 * To see if the door status is locked.
	 * 
	 * @return true if locked
	 */
	public boolean isLocked() {
		boolean doorLocked = false;

		if (myAccess.equals(AccessLevel.LOCKED)) {
			doorLocked = true;
		}
		return doorLocked;
	}

	/**
	 * Get the access level status.
	 * 
	 * @return the door access level
	 */
	public AccessLevel getAccessLevel() {
		return myAccess;
	}

	/**
	 * Set the access level status
	 * 
	 * @param theAccessLevel the level you want to set
	 */
	public void setAccessLevel(AccessLevel theAccessLevel) {
		myAccess = theAccessLevel;
	}

	/**
	 * Draw doors.
	 * 
	 * @param theMazeMap the game map
	 */
	public void draw(Maze theMazeMap) {
		theMazeMap.addDoor(myX, myY, myWidth, myHeight);
	}

	/**
	 * Add opened door effect.
	 * 
	 * @param theMazeMap the game map
	 */
	public void addOpen(Maze theMazeMap) {
		theMazeMap.addOpen(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
	}

	/**
	 * Add locked door effect.
	 * 
	 * @param theMazeMap the game map
	 */
	public void addLock(Maze theMazeMap) {
		theMazeMap.addLock(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
	}

	/**
	 * if answer is correct, open the door. otherwise the door is locked.
	 * 
	 * @param theInputPanel  the input box
	 * @param theMessageText change message text
	 * @param theMazeMap     the game map
	 * @param theGUI         the game user interface
	 */
	public void setOpenOrLock(JButton theInputPanel, JTextArea theMessageText, Maze theMazeMap, GUI theGUI) {
		if (myAccess.equals(AccessLevel.CLOSED)) {

			theInputPanel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (mySetOAD) {
						myAccess = AccessLevel.OPEN;
						theMazeMap.addOpen(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
					} else {
						myAccess = AccessLevel.LOCKED;
						theMazeMap.addLock(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
					}
					if (!theGUI.hasPath()) {
						JOptionPane.showMessageDialog(null, "YOU LOSE!");
					}
				}
			});
		}
	}

	/**
	 * quick lil getting method, for learning what the actual question for the
	 * question object of this door is, since it's inaccessible from outside
	 * classes.
	 * 
	 * @return the question
	 */
	public String getQuestion() {
		return myQuestion.getQuestion();
	}

	/**
	 * general method that is called for answering the question for this door, that
	 * than calls one of three specialized methods based on the question's type.
	 * 
	 * @param theAnswer  player input
	 * @param theMazeMap the game map
	 * @param theGUI     the game user interface
	 */
	public void answer(String theAnswer, Maze theMazeMap, GUI theGUI) {

		switch (myQuestion.getType()) {

		case MC:
			MCAnswer(theAnswer, theMazeMap, theGUI);
			break;

		case SA:
			SAAnswer(theAnswer, theMazeMap, theGUI);
			break;

		case TF:
			TFAnswer(theAnswer, theMazeMap, theGUI);
			break;
		}
	}

	/**
	 * method that is called when the question type is short answer.
	 * 
	 * @param theAnswer  player input
	 * @param theMazeMap the game map
	 * @param theGUI     the game user interface
	 */
	private void SAAnswer(String theAnswer, Maze theMazeMap, GUI theGUI) {

		if (theAnswer.equalsIgnoreCase(myQuestion.getAnswer(0))) {
			mySetOAD = true;
			myAccess = AccessLevel.OPEN;
			theMazeMap.addOpen(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
		} else {
			mySetOAD = false;
			myAccess = AccessLevel.LOCKED;
			theMazeMap.addLock(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
		}

		theMazeMap.repaint();
	}

	/**
	 * method that is called when the question type of the question in this door is
	 * Multiple choice.
	 * 
	 * @param theAnswer  player input
	 * @param theMazeMap the game map
	 * @param theGUI     the game user interface
	 */
	private void MCAnswer(String theAnswer, Maze theMazeMap, GUI theGUI) {
		answerHelper(Integer.parseInt(theAnswer) - 1, theMazeMap, theGUI);
	}

	/**
	 * method this is called when the question type is True/False, setting an in to
	 * either 0, 1 or 3.
	 * 
	 * @param theAnswer  player input
	 * @param theMazeMap the game map
	 * @param theGUI     the game user interface
	 */
	private void TFAnswer(String theAnswer, Maze theMazeMap, GUI theGUI) {

		int temp;

		switch (theAnswer) {

		case "True":
			temp = 0;
			break;

		case "False":
			temp = 1;
			break;

		default:
			temp = 3;
		}

		answerHelper(temp, theMazeMap, theGUI);
	}

	/**
	 * helper method for checking if the user inputed the right answer for MC or TF
	 * questions, since they both simply check via the attempt method of the
	 * question class.
	 * 
	 * @param theAnswer  player input
	 * @param theMazeMap the game map
	 * @param theGUI     the game user interface
	 */
	private void answerHelper(int theAnswer, Maze theMazeMap, GUI theGUI) {

		if (myQuestion.correctAnswer(theAnswer)) {
			mySetOAD = true;
			myAccess = AccessLevel.OPEN;
			theMazeMap.addOpen(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
		} else {
			mySetOAD = false;
			myAccess = AccessLevel.LOCKED;
			theMazeMap.addLock(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
		}

		theMazeMap.repaint();
	}
}
