package View;

import java.io.Serializable;
import java.util.Scanner;
import javax.swing.JOptionPane;

import Model.Door.AccessLevel;
import Model.Room.Direction;

public class Event implements Serializable {

	private static final long serialVersionUID = 2559151663429771478L;
	
	private TriviaMazeMain myTmm;
	private transient Scanner myScan;
	private boolean myUseKey = false;
	private boolean mySkipKey = false;
	private Direction myActDirection;

	public Event(TriviaMazeMain theTmm) {
		this.myTmm = theTmm;
	}
	
	/**
	 * Start the game;
	 */
	public void start() {
		myTmm.gui.startGame();
	}

	/**
	 * Open chest action.
	 */
	public void openChest() {
		
		chestSound();
		myTmm.gui.getChestClosed().setVisible(false);
		myTmm.gui.setKey(true);
		myTmm.gui.getMyCurrentRoom().setHasChest(false);
		myTmm.gui.setMessageText("You got a key, now you can open a door without answering question.");

	}

	/**
	 * Go up action.
	 */
	public void goUp() {

		// to see if player hits the wall
		if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.UP) == null) {
			myTmm.sound.play("LOCK");
			myTmm.gui.setMessageText("This door is damaged, can't pass.");
		}

		// to see if the door is out of attempt
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.UP).getAccessLevel() == AccessLevel.LOCKED) {
			myTmm.sound.play("LOCK");
			myTmm.gui.setMessageText("Wrong answer, door permanently locked.");
		}

		// to see if the door is already been opened
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.UP).getAccessLevel() == AccessLevel.OPEN) {
			
			myTmm.gui.setMessageText("This door is opened.");
			
			if (myTmm.gui.getMyCurrentRoom().getDoor(0) != null) {
				if (myTmm.gui.getMyCurrentRoom().getDoor(0).isOpened()) {
					doorSound();
					myTmm.gui.moveToken(myTmm.gui.getMyGridLocation().x, myTmm.gui.getMyGridLocation().y - 1);
					myTmm.gui.getMyMazeMap().repaint();
					System.out.println("test");
				}
				
				if (myTmm.gui.getMyCurrentRoom().getHasChest()) {
					myTmm.gui.getChestClosed().setVisible(true);
				} else {
					myTmm.gui.getChestClosed().setVisible(false);
				}
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
				myTmm.gui.setMessageText("Door is closed, answer the question.\n" + "\n"
						+ myTmm.gui.getMyCurrentRoom().getDoor(myActDirection).getQuestion());
			}
		}
	}

	/**
	 * Go down action
	 */
	public void goDown() {

		// to see if player hits the wall
		if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.DOWN) == null) {
			myTmm.sound.play("LOCK");
			myTmm.gui.setMessageText("This door is damaged, can't pass.");
		}

		// to see if the door is out of attempt
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.DOWN).getAccessLevel() == AccessLevel.LOCKED) {
			myTmm.sound.play("LOCK");
			myTmm.gui.setMessageText("Wrong answer, door permanently locked.");

		}

		// to see if the door is already been opened
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.DOWN).getAccessLevel() == AccessLevel.OPEN) {
			
			myTmm.gui.setMessageText("This door is opened.");
			
			if (myTmm.gui.getMyCurrentRoom().getDoor(2) != null) {
				if (myTmm.gui.getMyCurrentRoom().getDoor(2).isOpened()) {
					doorSound();
					myTmm.gui.moveToken(myTmm.gui.getMyGridLocation().x, myTmm.gui.getMyGridLocation().y + 1);
					myTmm.gui.getMyMazeMap().repaint();
				}
				
				if (myTmm.gui.getMyCurrentRoom().getHasChest()) {
					myTmm.gui.getChestClosed().setVisible(true);
				} else {
					myTmm.gui.getChestClosed().setVisible(false);
				}
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
				myTmm.gui.setMessageText("Door is closed, answer the question.\n" + "\n"
						+ myTmm.gui.getMyCurrentRoom().getDoor(myActDirection).getQuestion());
			}
		}
	}

	/**
	 * Go left action.
	 */
	public void goLeft() {

		// to see if player hits the wall
		if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.LEFT) == null) {
			myTmm.sound.play("LOCK");
			myTmm.gui.setMessageText("This door is damaged, can't pass.");
		}

		// to see if the door is out of attempt
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.LEFT).getAccessLevel() == AccessLevel.LOCKED) {
			myTmm.sound.play("LOCK");
			myTmm.gui.setMessageText("Wrong answer, door permanently locked.");
		}

		// to see if the door is already been opened
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.LEFT).getAccessLevel() == AccessLevel.OPEN) {
			
			myTmm.gui.setMessageText("This door is opened.");
			
			if (myTmm.gui.getMyCurrentRoom().getDoor(3) != null) {
				if (myTmm.gui.getMyCurrentRoom().getDoor(3).isOpened()) {
					doorSound();
					myTmm.gui.moveToken(myTmm.gui.getMyGridLocation().x - 1, myTmm.gui.getMyGridLocation().y);
					myTmm.gui.getMyMazeMap().repaint();
				}
				
				if (myTmm.gui.getMyCurrentRoom().getHasChest()) {
					myTmm.gui.getChestClosed().setVisible(true);
				} else {
					myTmm.gui.getChestClosed().setVisible(false);
				}
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
				myTmm.gui.setMessageText("Door is closed, answer the question.\n" + "\n"
						+ myTmm.gui.getMyCurrentRoom().getDoor(myActDirection).getQuestion());
			}
		}
	}

	/**
	 * Go right action.
	 */
	public void goRight() {

		// to see if player hits the wall
		if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT) == null) {
			myTmm.sound.play("LOCK");
			myTmm.gui.setMessageText("This door is damaged, can't pass.");
		}

		// to see if the door is out of attempt
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT).getAccessLevel() == AccessLevel.LOCKED) {
			myTmm.sound.play("LOCK");
			myTmm.gui.setMessageText("Wrong answer, door permanently locked.");
		}

		// to see if the door is already been opened
		else if (myTmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT).getAccessLevel() == AccessLevel.OPEN) {
			myTmm.gui.setMessageText("This door is opened.");
			if (myTmm.gui.getMyCurrentRoom().getDoor(1) != null) {
				if (myTmm.gui.getMyCurrentRoom().getDoor(1).isOpened()) {
					doorSound();
					myTmm.gui.moveToken(myTmm.gui.getMyGridLocation().x + 1, myTmm.gui.getMyGridLocation().y);
					myTmm.gui.getMyMazeMap().repaint();
				}
				
				if (myTmm.gui.getMyCurrentRoom().getHasChest()) {
					myTmm.gui.getChestClosed().setVisible(true);
				} else {
					myTmm.gui.getChestClosed().setVisible(false);
				}
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
				myTmm.gui.setMessageText("Door is closed, answer the question.\n" + "\n"
						+ myTmm.gui.getMyCurrentRoom().getDoor(myActDirection).getQuestion());
			}
		}
	}

	/**
	 * Enter button action.
	 */
	public void submit() {

		myScan = new Scanner(myTmm.gui.getJTF().getText());

		// wait for input
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
					myTmm.gui.getMyCurrentRoom().getDoor(myActDirection).addOpen(myTmm.gui.getMyMazeMap());

				} else {
					// go back to answer question
					mySkipKey = true;
				}
			} else {
				// answer question
				ansQuestion();
			}
		}
		
		// chose the direction
		switch (myActDirection) {

		case UP: goUp();
			break;
			
		case DOWN: goDown();
			break;
			
		case LEFT: goLeft();
			break;
			
		case RIGHT: goRight();
			break;
		}

		// checks for if you've won, since if you've correctly answered a question,
		// you'd move onto a next room, and thus this is to check if you've won or not
		// yet.
		if (myTmm.gui.win()) {
			myTmm.sound.stop();
			myTmm.sound.play("WIN");
			JOptionPane.showMessageDialog(null, "YOU'VE WON!");
			myTmm.gui.reset();
		}
	}

	/**
	 * Helper class for check if the answer is correct
	 */
	private void ansQuestion() {

		// runs the answer method in the door class, which will check if the inputed answer is correct
		myTmm.gui.getMyCurrentRoom().getDoor(myActDirection).answer(myTmm.gui.getJTF().getText(), myTmm.gui.getMyMazeMap(), myTmm.gui);
		mySkipKey = false;
		
	}
	
	/**
	 * Play door sound effect.
	 */
	private void doorSound() {
		myTmm.sound.play("DOOR");
	}
	
	/**
	 * Play chest sound effect.
	 */
	private void chestSound() {
		myTmm.sound.play("CHEST");
	}
}
