package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import View.TriviaMazeMain;

public class ActionHandler implements ActionListener , Serializable{

	private static final long serialVersionUID = -5918381906718494150L;
	
	private TriviaMazeMain myTmm;

	public ActionHandler(TriviaMazeMain theTmm) {
		
		this.myTmm = theTmm;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String yourChoice = e.getActionCommand();

		switch (yourChoice) {

		case "openChest": myTmm.event.openChest();
			break;
			
		case "goUp": myTmm.event.goUp();
			break;
			
		case "goDown": myTmm.event.goDown();
			break;
			
		case "goLeft": myTmm.event.goLeft();
			break;
			
		case "goRight": myTmm.event.goRight();
			break;
			
		case "enter answer": myTmm.event.submit();
			break;

		case "start": myTmm.event.start();
			break;
		}
	}

}
