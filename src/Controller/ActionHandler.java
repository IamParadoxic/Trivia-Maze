package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.TriviaMazeMain;

public class ActionHandler implements ActionListener {

	private TriviaMazeMain MyTmm;

	public ActionHandler(TriviaMazeMain theTmm) {
		
		this.MyTmm = theTmm;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String yourChoice = e.getActionCommand();

		switch (yourChoice) {

		case "openChest": MyTmm.event.openChest();
			break;
			
		case "goForward": MyTmm.event.goForward();
			break;
			
		case "goBack": MyTmm.event.goBack();
			break;
			
		case "goLeft": MyTmm.event.goLeft();
			break;
			
		case "goRight": MyTmm.event.goRight();
			break;
			
		case "enter answer": MyTmm.event.submit();
			break;

		}
	}

}
