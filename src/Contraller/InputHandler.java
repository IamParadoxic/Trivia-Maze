package Contraller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.TriviaMazeMain;
import View.GUI;

public class InputHandler implements ActionListener{
	
	public TriviaMazeMain tmm;
	
	public InputHandler(TriviaMazeMain tmm) {
		
		this.tmm = tmm;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String text = tmm.gui.getJtf().getText();
		
		tmm.gui.getTextLabel().setText(text);
		
		
	}

}
