package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import View.TriviaMazeMain;

public class InputHandler implements ActionListener, Serializable{
	
	private static final long serialVersionUID = 7697858437217405903L;
	
	private TriviaMazeMain myTmm;
	
	public InputHandler(TriviaMazeMain tmm) {
		
		this.myTmm = tmm;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		myTmm.gui.getJTF().getText();
		
		// reset input box
		myTmm.gui.getJTF().setText("");
		
	}
}
