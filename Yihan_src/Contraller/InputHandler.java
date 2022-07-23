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
		
		String text = GUI.jtf.getText();
		
		GUI.textLabel.setText(text);
		
		if (text.equals("A") || text.equals("a")) {
			GUI.textLabel.setText("Yes, you got it right!");
		} else {
			GUI.textLabel.setText("Sorry, it is wrong.");
		}
		
	}

}
