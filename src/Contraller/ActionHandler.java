package Contraller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import maze.TriviaMazeMain;

public class ActionHandler implements ActionListener{
	
	public TriviaMazeMain tmm;
	
	public ActionHandler(TriviaMazeMain tmm) {
		
		this.tmm = tmm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String yourChoice = e.getActionCommand();
		
		switch(yourChoice) {
		
		case "openChest": tmm.event.openChest(); break;
		case "goForward": tmm.event.goForward(); break;
		case "goBack": tmm.event.goBack(); break;
		case "goLeft": tmm.event.goLeft(); break;
		case "goRight": tmm.event.goRight(); break;
		
		}
	}
	
	
}
