package View;

import java.io.*;

import Controller.ActionHandler;
import Controller.InputHandler;

public class TriviaMazeMain implements Serializable{
	
	private static final long serialVersionUID = -6543460195244884436L;
	
	public Sound sound = new Sound();
	public ActionHandler aHandler = new ActionHandler(this);
	public InputHandler iHandler = new InputHandler(this);
	public Event event = new Event(this);
	public GUI gui = new GUI(this);
	
	public static void main(String[] args) {

		new TriviaMazeMain();
		
	}
	
	public TriviaMazeMain() {
		
	}
}
