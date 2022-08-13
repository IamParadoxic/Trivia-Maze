package View;

import Contraller.ActionHandler;
import Contraller.InputHandler;
import Model.Door;
import Model.Question;
import Model.Room;

public class TriviaMazeMain {
	
	public ActionHandler aHandler = new ActionHandler(this);
	public InputHandler iHandler = new InputHandler(this);
	public GUI gui = new GUI(this);
	public Event event = new Event(this);
	public Door door = new Door(0, 0, 0, 0);
	public Question question = new Question(null, null, 0, this);
	public Room room = new Room(0, 0, 0, 0, null);
	
	public static void main(String[] args) {
		
		new TriviaMazeMain();
		
	}
	
	public TriviaMazeMain() {
		
	}
	
}
