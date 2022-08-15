package View;

import Controller.ActionHandler;
import Controller.InputHandler;
import Model.Door;
import Model.Question;
import Model.Room;

public class TriviaMazeMain {
	
	public ActionHandler aHandler = new ActionHandler(this);
	public InputHandler iHandler = new InputHandler(this);
	public GUI gui = new GUI(this);
	public Event event = new Event(this);
	//public Door door = new Door(0, 0, 0, 0);
	//public Room room = new Room(0, 0, 0, 0, null);
	//public Question question = new Question(null, null, 0, null, this);
	
	public static void main(String[] args) {
		
		new TriviaMazeMain();
		
	}
	
	public TriviaMazeMain() {
		
	}
	
}
