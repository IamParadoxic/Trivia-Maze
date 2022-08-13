package View;

import Model.Door.AccessLevel;
import Model.Room.Direction;
import View.GUI;

import java.util.Scanner;

import Model.Door;
import Model.Room;
import Model.Maze;

public class Event {

	TriviaMazeMain tmm;

	public Event(TriviaMazeMain tmm) {
		this.tmm = tmm;
	}

	public void openChest() {
		tmm.gui.getChestB().setVisible(false);
		tmm.gui.setKey(true);
	}

	public void goForward() {

		if (tmm.gui.getMyCurrentRoom().getDoor(Direction.UP) == null) {
			tmm.gui.setMessageText("This door is damaged, can't pass.");

		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.UP).getAccessLevel() == AccessLevel.OPEN) {

			tmm.gui.setMessageText("This door is opened, you can go through.");

			if (tmm.gui.myGrid[tmm.gui.myGridLocation.x][tmm.gui.myGridLocation.y].myDoors[0] != null) {
				if (tmm.gui.myGrid[tmm.gui.myGridLocation.x][tmm.gui.myGridLocation.y].myDoors[0].isOpened()) {
					tmm.gui.moveToken(tmm.gui.myGridLocation.x, tmm.gui.myGridLocation.y - 1);
					tmm.gui.myMazeMap.repaint();				
				}

			}

		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.UP).getAccessLevel() == AccessLevel.LOCKED) {

			tmm.gui.setMessageText("This door is damaged, can't pass.");

		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.UP).getAccessLevel() == AccessLevel.CLOSED) {

			if (tmm.gui.getKey()) {

				tmm.gui.setMessageText("Key is used, door is open.");
				tmm.gui.getChestB().setVisible(false);
				tmm.gui.getChestL().setVisible(false);
				tmm.gui.setKey(false);

			} else {

				tmm.gui.setMessageText("Door is closed, answer the question.");

			}
		}
	}

	public void goBack() {

		if (tmm.gui.getMyCurrentRoom().getDoor(Direction.DOWN) == null) {
			tmm.gui.setMessageText("This door is damaged, can't pass.");

		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.DOWN).getAccessLevel() == AccessLevel.OPEN) {

			tmm.gui.setMessageText("This door is opened, you can go through.");

			if (tmm.gui.myGrid[tmm.gui.myGridLocation.x][tmm.gui.myGridLocation.y].myDoors[2] != null) {
				if (tmm.gui.myGrid[tmm.gui.myGridLocation.x][tmm.gui.myGridLocation.y].myDoors[2].isOpened()) {
					tmm.gui.moveToken(tmm.gui.myGridLocation.x, tmm.gui.myGridLocation.y + 1);
					tmm.gui.myMazeMap.repaint();				
				}

			}

		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.DOWN).getAccessLevel() == AccessLevel.LOCKED) {

			tmm.gui.setMessageText("This door is damaged, can't pass.");

		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.DOWN).getAccessLevel() == AccessLevel.CLOSED) {

			if (tmm.gui.getKey()) {

				tmm.gui.setMessageText("Key is used, door is open.");
				tmm.gui.getChestB().setVisible(false);
				tmm.gui.getChestL().setVisible(false);
				tmm.gui.setKey(false);

			} else {

				tmm.gui.setMessageText("Door is closed, answer the question.");

			}
		}
	}

	public void goLeft() {

		if (tmm.gui.getMyCurrentRoom().getDoor(Direction.LEFT) == null) {
			tmm.gui.setMessageText("This door is damaged, can't pass.");

		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.LEFT).getAccessLevel() == AccessLevel.OPEN) {

			tmm.gui.setMessageText("This door is opened, you can go through.");

			if (tmm.gui.myGrid[tmm.gui.myGridLocation.x][tmm.gui.myGridLocation.y].myDoors[3] != null) {
				if (tmm.gui.myGrid[tmm.gui.myGridLocation.x][tmm.gui.myGridLocation.y].myDoors[3].isOpened()) {
					tmm.gui.moveToken(tmm.gui.myGridLocation.x - 1, tmm.gui.myGridLocation.y);
					tmm.gui.myMazeMap.repaint();				
				}

			}

		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.LEFT).getAccessLevel() == AccessLevel.LOCKED) {

			tmm.gui.setMessageText("This door is damaged, can't pass.");

		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.LEFT).getAccessLevel() == AccessLevel.CLOSED) {

			if (tmm.gui.getKey()) {

				tmm.gui.setMessageText("Key is used, door is open.");
				tmm.gui.getChestB().setVisible(false);
				tmm.gui.getChestL().setVisible(false);

			} else {

				tmm.gui.setMessageText("Door is closed, answer the question.");

			}
		}
	}

	//	public void goRight() {
	//
	//		if (tmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT) == null) {
	//			tmm.gui.setMessageText("This door is damaged, can't pass.");
	//
	//		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT).getAccessLevel() == AccessLevel.OPEN) {
	//
	//			tmm.gui.setMessageText("This door is opened, you can go through.");
	//
	//			if (tmm.gui.myGrid[tmm.gui.myGridLocation.x][tmm.gui.myGridLocation.y].myDoors[1] != null) {
	//				if (tmm.gui.myGrid[tmm.gui.myGridLocation.x][tmm.gui.myGridLocation.y].myDoors[1].isOpened()) {
	//					tmm.gui.moveToken(tmm.gui.myGridLocation.x + 1, tmm.gui.myGridLocation.y);
	//					tmm.gui.myMazeMap.repaint();				
	//				}
	//
	//			}
	//
	//		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT).getAccessLevel() == AccessLevel.LOCKED) {
	//
	//			tmm.gui.setMessageText("This door is damaged, can't pass.");
	//
	//		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT).getAccessLevel() == AccessLevel.CLOSED) {
	//
	//			if (tmm.gui.getKey()) {
	//
	//				tmm.gui.setMessageText("Key is used, door is open.");
	//				tmm.gui.getChestB().setVisible(false);
	//				tmm.gui.getChestL().setVisible(false);
	//				tmm.gui.setKey(false);
	//
	//			} else {
	//
	//				tmm.gui.setMessageText("Door is closed, answer the question.");
	//
	//			}
	//		}
	//	}

	public void goRight() {

		if (tmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT) == null) {
			tmm.gui.setMessageText("This door is damaged, can't pass.");

		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT).getAccessLevel() == AccessLevel.OPEN) {

			tmm.gui.setMessageText("This door is opened, you can go through.");

			if (tmm.gui.myGrid[tmm.gui.myGridLocation.x][tmm.gui.myGridLocation.y].myDoors[1] != null) {
				if (tmm.gui.myGrid[tmm.gui.myGridLocation.x][tmm.gui.myGridLocation.y].myDoors[1].isOpened()) {
					tmm.gui.moveToken(tmm.gui.myGridLocation.x + 1, tmm.gui.myGridLocation.y);
					tmm.gui.myMazeMap.repaint();				
				}

			}

		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT).getAccessLevel() == AccessLevel.LOCKED) {

			tmm.gui.setMessageText("This door is damaged, can't pass.");

		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT).getAccessLevel() == AccessLevel.CLOSED) {

			if (tmm.gui.getKey()) {

				tmm.gui.setMessageText("Do you want to use key? (Enter T to use the key, enter N not use)");

				Scanner scanner = new Scanner(GUI.jtf.getText());
				while(scanner.hasNextLine()) {
					String s = scanner.nextLine();

					if (s.equals("T")) {

						tmm.gui.setMessageText("Key is used, door is open.");
						tmm.gui.getChestB().setVisible(false);
						tmm.gui.getChestL().setVisible(false);
						tmm.gui.setKey(false);
					}
				}

			} else {

				tmm.gui.setMessageText("Door is closed, answer the question.");

			}
		}
	}

}
