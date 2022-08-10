package Model;

import Controller.*;
import View.*;
import Model.*;

public class TriviaMazeMain {
	
	public ActionHandler aHandler = new ActionHandler(this);
	public InputHandler iHandler = new InputHandler(this);
	public GUI gui = new GUI(this);
	public Event event = new Event(this);
	public Door door = new Door(0, 0, 0, 0, null, gui);
	public Question question = new Question(null, null, 0, null, this);
	
	public static void main(String[] args) {
		
		new TriviaMazeMain();
		
	}
	
	public TriviaMazeMain() {
		
		
	}

}
