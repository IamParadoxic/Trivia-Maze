package Model;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

public class Maze extends JPanel implements Serializable{
	
	private static final long serialVersionUID = -471174395510626265L;

	private Timer timer;
	
    private final List<Rectangle> myRooms = new LinkedList<Rectangle>();
    private final List<Rectangle> myDoors = new LinkedList<Rectangle>();
    private final List<Rectangle> myOpenSymbols = new LinkedList<Rectangle>();
    private final List<Rectangle> myLockedSymbols = new LinkedList<Rectangle>();
    private Rectangle myPlayerToken;
	
	public Maze(BorderLayout theBorderLayout) {
		
        super(theBorderLayout);
        myPlayerToken = new Rectangle(0,0,2,2);
	}
	
    public void addRoom(int theX, int theY, int theWidth, int theHeight) {
    	myRooms.add(new Rectangle(theX,theY,theWidth,theHeight));
    }
    
    public void addDoor(int theX, int theY, int theWidth, int theHeight) {
    	myDoors.add(new Rectangle(theX,theY,theWidth,theHeight));
    }
    
    public void addOpen(int theX, int theY, int theWidth, int theHeight) {
    	myOpenSymbols.add(new Rectangle(theX,theY,theWidth,theHeight));
    }
    
    public void addLock(int theX, int theY, int theWidth, int theHeight) {
    	myLockedSymbols.add(new Rectangle(theX,theY,theWidth,theHeight));
    }
    
    public void addPlayerToken(int theX, int theY, int theWidth, int theHeight) {
    	myPlayerToken = new Rectangle(theX, theY, theWidth, theHeight);
    }
    
    public void movePlayer(int theX, int theY) {
    	myPlayerToken.setLocation(theX, theY);
    }
    
    public boolean hasPlayerToken() {
    	boolean flag = true;
    	if (myPlayerToken.contains(1, 1)) {
    		flag = false;
    	}
    	return flag;
    }
    
    public void clear() {
    	myRooms.clear();
    	myDoors.clear();
    	myOpenSymbols.clear();
    	myLockedSymbols.clear();
    	myPlayerToken = new Rectangle(0,0,2,2);	
    	repaint();
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        Graphics2D tempGraphics = (Graphics2D) theGraphics;
        
        for (int i = 0; i < myRooms.size(); i++) {
        	tempGraphics.setPaint(Color.GRAY);
        	tempGraphics.fill(myRooms.get(i));
        }
        
        for (int i = 0; i < myDoors.size(); i++) {
        	tempGraphics.setPaint(Color.LIGHT_GRAY);
        	tempGraphics.fill(myDoors.get(i));
        }
        
        for (int i = 0; i < myOpenSymbols.size(); i++) {
        	tempGraphics.setPaint(Color.WHITE);
        	tempGraphics.fill(myOpenSymbols.get(i));
        }
        
        for (int i = 0; i < myLockedSymbols.size(); i++) {
        	tempGraphics.setPaint(Color.RED);
        	tempGraphics.fill(myLockedSymbols.get(i));
        }

    	tempGraphics.setPaint(Color.BLUE);
    	if (myPlayerToken != null) {
    		tempGraphics.fill(myPlayerToken);
    	}
    }

}
