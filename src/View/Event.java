package View;

import java.util.Scanner;

import Model.Door.AccessLevel;
import Model.Room.Direction;

public class Event {

	private TriviaMazeMain myTmm;
	private Scanner myScan;
	private boolean myUseKey = false;
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
			if (myTmm.gui.getKey()) {
				myTmm.gui.setMessageText("Do you want to use key? (Enter K to use the key, enter N not use)");
				myUseKey = true;
			}
			
			// if player doesn't have key
			else {
				myTmm.gui.setMessageText("Door is closed, answer the question.");
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
			if (myTmm.gui.getMyCurrentRoom().myDoors[0] != null) {
				if (myTmm.gui.getMyCurrentRoom().myDoors[0].isOpened()) {
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
			if (myTmm.gui.getKey()) {
				myTmm.gui.setMessageText("Do you want to use key? (Enter K to use the key, enter N not use)");
				myUseKey = true;
			}
			
			// if player doesn't have key
			else {
				myTmm.gui.setMessageText("Door is closed, answer the question.");
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
			if (myTmm.gui.getMyCurrentRoom().myDoors[0] != null) {
				if (myTmm.gui.getMyCurrentRoom().myDoors[0].isOpened()) {
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
			if (myTmm.gui.getKey()) {
				myTmm.gui.setMessageText("Do you want to use key? (Enter K to use the key, enter N not use)");
				myUseKey = true;
			}
			
			// if player don't have key
			else {
				myTmm.gui.setMessageText("Door is closed, answer the question.");
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
			if (myTmm.gui.getMyCurrentRoom().myDoors[0] != null) {
				if (myTmm.gui.getMyCurrentRoom().myDoors[0].isOpened()) {
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
			if (myTmm.gui.getKey()) {
				myTmm.gui.setMessageText("Do you want to use key? (Enter K to use the key, enter N not use)");
				myUseKey = true;
			}
			
			// if player doesn't have key
			else {
				myTmm.gui.setMessageText("Door is closed, answer the question.");
			}
		}
	}

	public void submit() {
		
		myScan = new Scanner (myTmm.gui.getJTF().getText());
		
		//wait for input
		while (myScan.hasNextLine()) {
			String s = myScan.nextLine();
			
			// if has key
			if (myUseKey) {
				
				// if use
				if (s.equals("K") || s.equals("k")) {
					// key used
					myUseKey = false;
					myTmm.gui.setKey(false);

					// set open
					// TO-DO

				} else {
					
					// go back to answer question
					// TO-DO
				}
			} else {
				
				// answer question
				// TO-DO
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
	}
	
	public void ansQuestion() {
		
	}
}
