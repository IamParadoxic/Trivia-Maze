package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import org.junit.jupiter.api.Test;

import View.GUI;
import View.TriviaMazeMain;
import Model.Door;
import Model.Door.AccessLevel;
import Model.Maze;
import Model.Question;

class TestDoor {
	
	private final TriviaMazeMain myTmm = new TriviaMazeMain();;
	
	private final int testX = 700;
	private final int testY = 500;
	private final int testWidth = 300;
	private final int testHeight = 300;
	
	private final String testStringQuestion = "";
	private final String[] testStringAnswer = {"", "", "", ""};
	private final int testCorrect = 1;
	private final String testType = "";
	private final Question testQuestion = new Question(testStringQuestion, testStringAnswer, testCorrect, testType, myTmm);
	private final JButton JB = new JButton("");
	private final JTextArea JTA = new JTextArea("");
	private final Maze myMazeMap = new Maze(null);
	private final GUI myGUI = new GUI(myTmm);

	private List<ActionListener> listeners;
	public void addListener(ActionListener listener) {
	  listeners.add(listener);
	}
	public void test() {
	  ActionEvent ae = new ActionEvent(myTmm.aHandler, testCorrect, testStringQuestion);
	  for(ActionListener listener: listeners) {
	    listener.actionPerformed(ae);
	  }
	}
	
	@Test
	void testDoor() {
		constructorTestCorrect(new Door(testX, testY, testWidth, testHeight, testQuestion));
		constructorTestIncorrect(new Door(testX, testY, testWidth, testHeight, testQuestion));
	}

	private void constructorTestCorrect(Door theDoor) {
		assertEquals(theDoor.getAccessLevel(), AccessLevel.CLOSED, "It is not at correct status.");
		theDoor.setAccessLevel(AccessLevel.OPEN);
		assertEquals(theDoor.getAccessLevel(), AccessLevel.OPEN, "It is not at correct status.");
		theDoor.setAccessLevel(AccessLevel.LOCKED);
		assertEquals(theDoor.getAccessLevel(), AccessLevel.LOCKED, "It is not at correct status.");
	}
	
	private void constructorTestIncorrect(Door theDoor) {
		theDoor.setAccessLevel(AccessLevel.OPEN);
		assertNotEquals(theDoor.getAccessLevel(), AccessLevel.CLOSED, "It is not at correct status.");
		assertNotEquals(theDoor.getAccessLevel(), AccessLevel.LOCKED, "It is not at correct status.");
		theDoor.setAccessLevel(AccessLevel.CLOSED);
		assertNotEquals(theDoor.getAccessLevel(), AccessLevel.OPEN, "It is not at correct status.");
		assertNotEquals(theDoor.getAccessLevel(), AccessLevel.LOCKED, "It is not at correct status.");
		theDoor.setAccessLevel(AccessLevel.LOCKED);
		assertNotEquals(theDoor.getAccessLevel(), AccessLevel.OPEN, "It is not at correct status.");
		assertNotEquals(theDoor.getAccessLevel(), AccessLevel.CLOSED, "It is not at correct status.");
	}
	
	@Test
	void testIsOpened() {
		final Door door = new Door(testX, testY, testWidth, testHeight, testQuestion);
		door.setAccessLevel(AccessLevel.OPEN);
		assertEquals(door.getAccessLevel().equals(AccessLevel.OPEN), door.isOpened(), "It is not at correct status.");
		assertFalse(door.isClosed(), "The door is opened.");
		assertFalse(door.isLocked(), "The door is opened.");
	}
	
	@Test
	void testIsClosed() {
		final Door door = new Door(testX, testY, testWidth, testHeight, testQuestion);
		door.setAccessLevel(AccessLevel.CLOSED);
		assertEquals(door.getAccessLevel().equals(AccessLevel.CLOSED), door.isClosed(), "It is not at correct status.");
		assertFalse(door.isOpened(), "The door is opened.");
		assertFalse(door.isLocked(), "The door is opened.");
	}
	
	@Test
	void testIsLocked() {
		final Door door = new Door(testX, testY, testWidth, testHeight, testQuestion);
		door.setAccessLevel(AccessLevel.LOCKED);
		assertEquals(door.getAccessLevel().equals(AccessLevel.LOCKED), door.isLocked(), "It is not at correct status.");
		assertFalse(door.isOpened(), "The door is opened.");
		assertFalse(door.isClosed(), "The door is opened.");
	}
	
	@Test
	void testsetOpenOrLock() {
		final Door door = new Door(testX, testY, testWidth, testHeight, testQuestion);
		door.setAccessLevel(AccessLevel.CLOSED);
		door.setMySetOAD(true);
		door.setOpenOrLock(JB, JTA, myMazeMap, myGUI);
		JB.doClick();
		assertEquals(door.getAccessLevel(), AccessLevel.OPEN, "It is not at correct status.");
		door.setMySetOAD(false);
		door.setOpenOrLock(JB, JTA, myMazeMap, myGUI);
		JB.doClick();
		assertEquals(door.getAccessLevel(), AccessLevel.LOCKED, "It is not at correct status.");
	}
	
}
