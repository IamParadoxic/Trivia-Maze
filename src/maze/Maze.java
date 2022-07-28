package maze;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.*;

public class Maze extends JPanel implements ActionListener{

	private Timer timer;
	
	public Maze() {
		
		timer = new Timer(25, this);
		timer.start();
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.red);
		g.fillRect(100,100,100,100);
	}
}
