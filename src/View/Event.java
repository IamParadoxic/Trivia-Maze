package View;

import Model.Door.AccessLevel;
import Model.Room.Direction;

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
		
		if (tmm.gui.getMyCurrentRoom().getDoor(Direction.UP).getAccessLevel() == AccessLevel.OPEN) {
			
			tmm.gui.setMessageText("This door is opened, you can go through.");
			tmm.gui.setMyCurrentRoom(1, 0);
			
		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.UP).getAccessLevel() == AccessLevel.LOCKED) {
			
			tmm.gui.setMessageText("This door is damaged, can't pass.");
			
		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.UP).getAccessLevel() == AccessLevel.CLOSED) {
			
			if (tmm.gui.getKey()) {
				
				tmm.gui.setMessageText("Key is used, door is open.");
				tmm.gui.getChestB().setVisible(false);
				tmm.gui.getChestL().setVisible(false);
				tmm.gui.setMyCurrentRoom(0, -1);
				tmm.gui.setKey(false);
				
			} else {

				tmm.gui.setMessageText("Door is closed, answer the question.");
				
			}
		}
	}
	
	public void goBack() {
		
		if (tmm.gui.getMyCurrentRoom().getDoor(Direction.DOWN).getAccessLevel() == AccessLevel.OPEN) {
			
			tmm.gui.setMessageText("This door is opened, you can go through.");
			tmm.gui.setMyCurrentRoom(0, 1);
			
		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.DOWN).getAccessLevel() == AccessLevel.LOCKED) {
			
			tmm.gui.setMessageText("This door is damaged, can't pass.");
			
		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.DOWN).getAccessLevel() == AccessLevel.CLOSED) {
			
			if (tmm.gui.getKey()) {
				
				tmm.gui.setMessageText("Key is used, door is open.");
				tmm.gui.getChestB().setVisible(false);
				tmm.gui.getChestL().setVisible(false);
				tmm.gui.setMyCurrentRoom(0, 1);
				tmm.gui.setKey(false);
				
			} else {

				tmm.gui.setMessageText("Door is closed, answer the question.");
				
			}
		}
	}
	
	public void goLeft() {
		
		if (tmm.gui.getMyCurrentRoom().getDoor(Direction.LEFT).getAccessLevel() == AccessLevel.OPEN) {
			
			tmm.gui.setMessageText("This door is opened, you can go through.");
			tmm.gui.setMyCurrentRoom(-1, 0);
			
		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.LEFT).getAccessLevel() == AccessLevel.LOCKED) {
			
			tmm.gui.setMessageText("This door is damaged, can't pass.");
			
		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.LEFT).getAccessLevel() == AccessLevel.CLOSED) {
			
			if (tmm.gui.getKey()) {
				
				tmm.gui.setMessageText("Key is used, door is open.");
				tmm.gui.getChestB().setVisible(false);
				tmm.gui.getChestL().setVisible(false);
				tmm.gui.setMyCurrentRoom(-1, 0);
				
			} else {

				tmm.gui.setMessageText("Door is closed, answer the question.");
				
			}
		}
	}
	
	public void goRight() {
		
		if (tmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT).getAccessLevel() == AccessLevel.OPEN) {
			
			tmm.gui.setMessageText("This door is opened, you can go through.");
			tmm.gui.setMyCurrentRoom(1, 0);
			
		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT).getAccessLevel() == AccessLevel.LOCKED) {
			
			tmm.gui.setMessageText("This door is damaged, can't pass.");
			
		} else if (tmm.gui.getMyCurrentRoom().getDoor(Direction.RIGHT).getAccessLevel() == AccessLevel.CLOSED) {
			
			if (tmm.gui.getKey()) {
				
				tmm.gui.setMessageText("Key is used, door is open.");
				tmm.gui.getChestB().setVisible(false);
				tmm.gui.getChestL().setVisible(false);
				tmm.gui.setMyCurrentRoom(1, 0);
				tmm.gui.setKey(false);
				
			} else {

				tmm.gui.setMessageText("Door is closed, answer the question.");
				
			}
		}
	}
}
