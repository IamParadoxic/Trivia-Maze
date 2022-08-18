package Model;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class Maze extends JPanel implements Serializable{

	private static final long serialVersionUID = 153585957946307993L;

	private final List<Rectangle> myRooms = new LinkedList<Rectangle>();
	private final List<Rectangle> myDoors = new LinkedList<Rectangle>();
	private final List<Rectangle> myOpenSymbols = new LinkedList<Rectangle>();
	private final List<Rectangle> myLockedSymbols = new LinkedList<Rectangle>();
	private Rectangle myPlayerToken;

	public Maze(BorderLayout theBorderLayout) {
		super(theBorderLayout);
		myPlayerToken = new Rectangle(0,0,2,2);
	}

	/**
	 * Add room into mini map
	 * 
	 * @param theX coordinate x 
	 * @param theY coordinate y
	 * @param theWidth item width
	 * @param theHeight item height
	 */
	public void addRoom(int theX, int theY, int theWidth, int theHeight) {
		myRooms.add(new Rectangle(theX,theY,theWidth,theHeight));
	}

	/**
	 * Add closed door into mini map
	 * 
	 * @param theX coordinate x 
	 * @param theY coordinate y
	 * @param theWidth item width
	 * @param theHeight item height
	 */
	public void addDoor(int theX, int theY, int theWidth, int theHeight) {
		myDoors.add(new Rectangle(theX,theY,theWidth,theHeight));
	}

	/**
	 * Add opened door into mini map
	 * 
	 * @param theX coordinate x 
	 * @param theY coordinate y
	 * @param theWidth item width
	 * @param theHeight item height
	 */
	public void addOpen(int theX, int theY, int theWidth, int theHeight) {
		myOpenSymbols.add(new Rectangle(theX,theY,theWidth,theHeight));
	}

	/**
	 * Add locked door into mini map
	 * 
	 * @param theX coordinate x 
	 * @param theY coordinate y
	 * @param theWidth item width
	 * @param theHeight item height
	 */
	public void addLock(int theX, int theY, int theWidth, int theHeight) {
		myLockedSymbols.add(new Rectangle(theX,theY,theWidth,theHeight));
	}

	/**
	 * Add player token into mini map
	 * 
	 * @param theX coordinate x 
	 * @param theY coordinate y
	 * @param theWidth item width
	 * @param theHeight item height
	 */
	public void addPlayer(int theX, int theY, int theWidth, int theHeight) {
		myPlayerToken = new Rectangle(theX, theY, theWidth, theHeight);
	}

	/**
	 * Move player to a different position.
	 * 
	 * @param theX coordinate x 
	 * @param theY coordinate y
	 */
	public void movePlayer(int theX, int theY) {
		myPlayerToken.setLocation(theX, theY);
	}

	/**
	 * To see if there is player token.
	 * 
	 * @return true if there is one
	 */
	public boolean hasPlayer() {
		boolean flag = true;

		if (myPlayerToken.contains(1, 1)) {
			flag = false;
		}
		return flag;
	}

	/**
	 * Draw things in the mini map.
	 */
	@Override
	public void paintComponent(final Graphics theGraphics) {
		super.paintComponent(theGraphics);
		Graphics2D graphics = (Graphics2D) theGraphics;

		for (int i = 0; i < myRooms.size(); i++) {
			graphics.setPaint(Color.GRAY);
			graphics.fill(myRooms.get(i));
		}

		for (int i = 0; i < myDoors.size(); i++) {
			graphics.setPaint(Color.LIGHT_GRAY);
			graphics.fill(myDoors.get(i));
		}

		for (int i = 0; i < myOpenSymbols.size(); i++) {
			graphics.setPaint(Color.green);
			graphics.fill(myOpenSymbols.get(i));
		}

		for (int i = 0; i < myLockedSymbols.size(); i++) {
			graphics.setPaint(Color.RED);
			graphics.fill(myLockedSymbols.get(i));
		}

		graphics.setPaint(Color.BLUE);

		if (myPlayerToken != null) {
			graphics.fill(myPlayerToken);
		}
	}
}
