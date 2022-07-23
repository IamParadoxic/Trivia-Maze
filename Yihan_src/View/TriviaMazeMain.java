package View;

import Contraller.ActionHandler;
import Contraller.InputHandler;

public class TriviaMazeMain {
	
	public ActionHandler aHandler = new ActionHandler(this);
	public InputHandler iHandler = new InputHandler(this);
	public GUI gui = new GUI(this);
	public Event event = new Event(this);
	
	public static void main(String[] args) {
		
		new TriviaMazeMain();
		
	}
	
	public TriviaMazeMain() {
		
		
	}

}
