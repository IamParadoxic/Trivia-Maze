package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.TriviaMazeMain;
import View.GUI;

public class InputHandler implements ActionListener{
	
	private TriviaMazeMain MyTmm;
	private String myText;
	
	public InputHandler(TriviaMazeMain tmm) {
		
		this.MyTmm = tmm;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		myText = MyTmm.gui.getJTF().getText();
		
	}
	
	public void setAnsMessage(String theString) {
		MyTmm.gui.getTextLabel().setText(theString);
	}
	
	public void setKeyMessage(String theString) {
		MyTmm.gui.getTextLabel().setText(theString);
	}
}
