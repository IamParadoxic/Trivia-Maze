package Model;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

import View.GUI;
import Model.Question;
import Model.Question.QuestionType;
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

	public Door(int theX, int theY, int theWidth, int theHeight, Question theQuestion) {
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

	
	
	
	//quick lil getting method, for learning what the actual question for the question object of this door is, since it's inaccessible from outside classes.
	public String getQuestion() {
		return myQuestion.getQuestion();
	}


	//general method that is called for answering the question for this door, that than calls one of three specialized methods based on the question's type
	public void answer(String theAnswer, Maze theMazeMap, GUI theGUI) {
		switch(myQuestion.getType()) {
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
	//method that is called when the question type is short answer
	private void SAAnswer(String theAnswer, Maze theMazeMap, GUI theGUI) {
		if(theAnswer.equalsIgnoreCase(myQuestion.getAnswer(0))) {
			myAccess = AccessLevel.OPEN;
		} else {
			myAccess = AccessLevel.LOCKED;
			if (!theGUI.hasPath()) {
				JOptionPane.showMessageDialog(null, "YOU LOSE!");
			}
			
		}
		theMazeMap.repaint();
	}
	//method that is called when the question type of the question in this door is Multiple choice
	private void MCAnswer(String theAnswer, Maze theMazeMap, GUI theGUI) {
		answerHelper(Integer.parseInt(theAnswer) - 1,theMazeMap, theGUI);
	}
	//method this is called when the question type is True/False, setting an in to either 0, 1 or 3, 
	private void TFAnswer(String theAnswer, Maze theMazeMap, GUI theGUI) {
		int temp;
		switch(theAnswer) {
		case "True":
			temp = 0;
			break;
		case "False":
			temp = 1;
			break;
		default:
			temp = 3;
		}
		answerHelper(temp,theMazeMap, theGUI);
	}
	//helper method for checking if the user inputed the right answer for MC or TF questions, since they both simply check via the attempt method of the question class
	private void answerHelper(int theAnswer, Maze theMazeMap, GUI theGUI) {
		if(myQuestion.attempt(theAnswer)) {
			myAccess = AccessLevel.OPEN;
		} else {
			myAccess = AccessLevel.LOCKED;
			if (!theGUI.hasPath()) {
				JOptionPane.showMessageDialog(null, "YOU LOSE!");
			}

		}
		theMazeMap.repaint();
	}

}
