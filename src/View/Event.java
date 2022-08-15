package View;

import java.util.Scanner;

import javax.swing.JOptionPane;

import Model.Door;
import Model.Door.AccessLevel;
import Model.Room.Direction;

public class Event {

	private TriviaMazeMain myTmm;
	private Scanner myScan;
	private boolean myUseKey = false;
	private boolean mySkipKey = false;
	private Direction myActDirection;

	public Event(TriviaMazeMain theTmm) {
		this.myTmm = theTmm;
	}

	public void openChest() {

		myTmm.gui.getChestClosed().setVisible(false);
		myTmm.gui.setKey(true);
		myTmm.gui.setMessageText("You get a key, now you can open a door without answering question.");
	}

	public void goForward() {

		// to see if player hits the wall
		if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.UP) == null) {
			myTmm.gui.setMessageText("This door is damaged, can't pass.");
		}

		// to see if the door is out of attempt
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.UP).getAccessLevel() == AccessLevel.LOCKED) {
			myTmm.gui.setMessageText("This door is damaged, can't pass.");
		}

		// to see if the door is already been opened
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.UP).getAccessLevel() == AccessLevel.OPEN) {
			myTmm.gui.setMessageText("This door is opened, you can go through.");
			if (myTmm.gui.getMyCurrentRoom().myDoors[0] != null) {
				if (myTmm.gui.getMyCurrentRoom().myDoors[0].isOpened()) {
					myTmm.gui.moveToken(myTmm.gui.getMyGridLocation().x, myTmm.gui.getMyGridLocation().y - 1);
					myTmm.gui.getMyMazeMap().repaint();
				}
				myTmm.gui.getChestClosed().setVisible(false);
				myTmm.gui.getChestOpened().setVisible(false);
			}
		}

		// to see if the door still needs answer
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.UP).getAccessLevel() == AccessLevel.CLOSED) {

			// set direction
			myActDirection = Direction.UP;

			// if player has key
			if (myTmm.gui.getKey() && !mySkipKey) {
				myTmm.gui.setMessageText("Do you want to use key? (Enter K to use the key, enter N not use)");
				myUseKey = true;
			}

			// if player doesn't have key
			else {
				myTmm.gui.setMessageText("Door is closed, answer the question.\n" + myTmm.gui.getMyCurrentRoom().getDoor(myActDirection).getQuestion());
			}
		}
	}

	public void goBack() {

		// to see if player hits the wall
		if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.DOWN) == null) {
			myTmm.gui.setMessageText("This door is damaged, can't pass.");
		}

		// to see if the door is out of attempt
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.DOWN).getAccessLevel() == AccessLevel.LOCKED) {
			myTmm.gui.setMessageText("This door is damaged, can't pass.");

		}

		// to see if the door is already been opened
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.DOWN).getAccessLevel() == AccessLevel.OPEN) {
			myTmm.gui.setMessageText("This door is opened, you can go through.");
			if (myTmm.gui.getMyCurrentRoom().myDoors[2] != null) {
				if (myTmm.gui.getMyCurrentRoom().myDoors[2].isOpened()) {
					myTmm.gui.moveToken(myTmm.gui.getMyGridLocation().x, myTmm.gui.getMyGridLocation().y + 1);
					myTmm.gui.getMyMazeMap().repaint();
				}
				myTmm.gui.getChestClosed().setVisible(false);
				myTmm.gui.getChestOpened().setVisible(false);
			}
		}

		// to see if the door still needs answer
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.DOWN).getAccessLevel() == AccessLevel.CLOSED) {

			// set direction
			myActDirection = Direction.DOWN;

			// if player has key
			if (myTmm.gui.getKey() && !mySkipKey) {
				myTmm.gui.setMessageText("Do you want to use key? (Enter K to use the key, enter N not use)");
				myUseKey = true;
			}

			// if player doesn't have key
			else {
				myTmm.gui.setMessageText("Door is closed, answer the question.\n" + myTmm.gui.getMyCurrentRoom().getDoor(myActDirection).getQuestion());
			}
		}
	}

	public void goLeft() {

		// to see if player hits the wall
		if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.LEFT) == null) {
			myTmm.gui.setMessageText("This door is damaged, can't pass.");
		}

		// to see if the door is out of attempt
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.LEFT).getAccessLevel() == AccessLevel.LOCKED) {
			myTmm.gui.setMessageText("This door is damaged, can't pass.");
		}

		// to see if the door is already been opened
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.LEFT).getAccessLevel() == AccessLevel.OPEN) {
			myTmm.gui.setMessageText("This door is opened, you can go through.");
			if (myTmm.gui.getMyCurrentRoom().myDoors[3] != null) {
				if (myTmm.gui.getMyCurrentRoom().myDoors[3].isOpened()) {
					myTmm.gui.moveToken(myTmm.gui.getMyGridLocation().x - 1, myTmm.gui.getMyGridLocation().y);
					myTmm.gui.getMyMazeMap().repaint();
				}
				myTmm.gui.getChestClosed().setVisible(false);
				myTmm.gui.getChestOpened().setVisible(false);
			}
		}

		// to see if the door still needs answer
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.LEFT).getAccessLevel() == AccessLevel.CLOSED) {

			// set direction
			myActDirection = Direction.LEFT;

			// if player has key
			if (myTmm.gui.getKey() && !mySkipKey) {
				myTmm.gui.setMessageText("Do you want to use key? (Enter K to use the key, enter N not use)");
				myUseKey = true;
			}

			// if player don't have key
			else {
				myTmm.gui.setMessageText("Door is closed, answer the question.\n" + myTmm.gui.getMyCurrentRoom().getDoor(myActDirection).getQuestion());
			}
		}
	}

	public void goRight() {

		// to see if player hits the wall
		if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT) == null) {
			myTmm.gui.setMessageText("This door is damaged, can't pass.");
		}

		// to see if the door is out of attempt
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT).getAccessLevel() == AccessLevel.LOCKED) {
			myTmm.gui.setMessageText("This door is damaged, can't pass.");
		}

		// to see if the door is already been opened
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT).getAccessLevel() == AccessLevel.OPEN) {
			myTmm.gui.setMessageText("This door is opened, you can go through.");
			if (myTmm.gui.getMyCurrentRoom().myDoors[1] != null) {
				if (myTmm.gui.getMyCurrentRoom().myDoors[1].isOpened()) {
					myTmm.gui.moveToken(myTmm.gui.getMyGridLocation().x + 1, myTmm.gui.getMyGridLocation().y);
					myTmm.gui.getMyMazeMap().repaint();
				}
				myTmm.gui.getChestClosed().setVisible(false);
				myTmm.gui.getChestOpened().setVisible(false);
			}
		}

		// to see if the door still needs answer
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT).getAccessLevel() == AccessLevel.CLOSED) {

			// set direction
			myActDirection = Direction.RIGHT;

			// if player has key
			if (myTmm.gui.getKey() && !mySkipKey) {
				myTmm.gui.setMessageText("Do you want to use key? (Enter K to use the key, enter N not use)");
				myUseKey = true;
			}

			// if player doesn't have key
			else {
				myTmm.gui.setMessageText("Door is closed, answer the question.\n" + myTmm.gui.getMyCurrentRoom().getDoor(myActDirection).getQuestion());
			}
		}
	}

	public void submit() {

		myScan = new Scanner (myTmm.gui.getJTF().getText());

		//wait for input
		while (myScan.hasNextLine()) {
			String s = myScan.nextLine();

			// if has key
			if (myUseKey && !mySkipKey) {
				// if use
				if (s.equals("K") || s.equals("k")) {
					// key used
					myUseKey = false;
					myTmm.gui.setKey(false);

					// set open
					myTmm.gui.getMyCurrentRoom().getDoor(myActDirection).setAccessLevel(AccessLevel.OPEN);

				} else {

					// go back to answer question
					// TO-DO
					mySkipKey = true;
					myTmm.gui.getJTF().setText("");					
				}
			} else {

				// answer question
				// TO-DO
				ansQuestion();
			}
		}

		// chose the direction
		switch (myActDirection) {
		case UP: goForward();
		break;
		case DOWN: goBack();
		break;
		case LEFT: goLeft();
		break;
		case RIGHT: goRight();
		break;
		}
		//checks for if you've won, since if you've correctly answered a question, you'd move onto a next room, and thus this is to check if you've won or not yet.
		if (myTmm.gui.win()) {
			JOptionPane.showMessageDialog(null, "YOU'VE WON!");
		}
	}

	public void ansQuestion() {
		//runs the answer method in the door class, which will check if the inputed answer is correct
		myTmm.gui.getMyCurrentRoom().getDoor(myActDirection).answer(myTmm.gui.getJTF().getText(), myTmm.gui.getMyMazeMap(), myTmm.gui);
		//code for clearing whatever was in the user input text field
		myTmm.gui.getJTF().setText("");
		mySkipKey = false;
	}
}
