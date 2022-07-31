package Model;

import Controller.*;
import View.Event;
import View.GUI;

public class TriviaMazeMain {
	
	public ActionHandler aHandler = new ActionHandler(this);
	public InputHandler iHandler = new InputHandler(this);
	public GUI gui = new GUI(this);
	public Event event = new Event(this);
	public Door door = new Door(0, 0, 0, 0, null);
		
	public static void main(String[] args) {		
		
		new TriviaMazeMain();
		
	}
	
	public TriviaMazeMain() {
		
		
	}

}
