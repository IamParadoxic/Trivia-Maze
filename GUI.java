import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GUI {

	 TriviaMazeMain tmm;
	 
	 JFrame window;
	 public JTextArea messageText1;
	 public JTextArea messageText2;
	 public JPanel fieldPanel[] = new JPanel[10];
	 public JLabel fieldLabel[] = new JLabel[10];
	 public JMenuBar menuBar;
	 public JMenu menu[] = new JMenu[10];
	 public JMenuItem menuItem;
	 
	 public GUI(TriviaMazeMain tmm) {
	 
	 	this.tmm = tmm;
	 	
	 	createFrame();
	 	createMainField();
	 	window.setVisible(true);
	 	
	 }
	 
	 public void createFrame() {
		 
		 // the main window.
		 window = new JFrame("Trivia Maze");
		 window.setSize(1600, 1200);
		 window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 window.getContentPane().setBackground(Color.black);
		 window.setLayout(null);
		 
		 // Create the MeunBar
		 menuBar = new JMenuBar();
		 menu[1] = new JMenu("File");
//		 menu.setMnemonic(KeyEvent.VK_A);
//		 menu.getAccessibleContext().setAccessibleDescription(
//			        "The only menu in this program that has menu items");
		 menuBar.add(menu[1]);
		 menuItem = new JMenuItem("New Game");
		 menu[1].add(menuItem);
		 menuItem = new JMenuItem("Save Game");
		 menu[1].add(menuItem);
		 menuItem = new JMenuItem("Load Game");
		 menu[1].add(menuItem);
		 menuItem = new JMenuItem("Exit");
		 menu[1].add(menuItem);
		 menu[2] = new JMenu("Help");
		 menuBar.add(menu[2]);
		 menuItem = new JMenuItem("About");
		 menu[2].add(menuItem);
		 menuItem = new JMenuItem("Game Play Instructions");
		 menu[2].add(menuItem);
		 window.setJMenuBar(menuBar);
		 
		 // MessageBox to the right side.
		 messageText1 = new JTextArea("red background for test");
		 messageText1.setBounds(1200,0,400,1200);
		 messageText1.setBackground(Color.red);
		 messageText1.setForeground(Color.white);
		 messageText1.setEditable(false);
		 messageText1.setLineWrap(true);
		 messageText1.setWrapStyleWord(true);
		 messageText1.setFont((new Font("Test", Font.BOLD, 30)));
		 window.add(messageText1);
		 
		// MessageBox to the bottom.
		 messageText2 = new JTextArea("red background for test");
		 messageText2.setBounds(0,900,1200,400);
		 messageText2.setBackground(Color.red);
		 messageText2.setForeground(Color.white);
		 messageText2.setEditable(false);
		 messageText2.setLineWrap(true);
		 messageText2.setWrapStyleWord(true);
		 messageText2.setFont((new Font("Test", Font.BOLD, 30)));
		 window.add(messageText2);
		 
		 
	 }
	 
	 
	 public void createMainField() {
		 
		 fieldPanel[1] = new JPanel();
		 fieldPanel[1].setBounds(50, 50, 1100, 800);
		 fieldPanel[1].setBackground(Color.yellow);
		 fieldPanel[1].setLayout(null);
		 window.add(fieldPanel[1]);
		 
		 fieldLabel[1] = new JLabel();
		 fieldLabel[1].setBounds(0, 0, 1100, 800);
		 
		 ImageIcon fieldImage = new ImageIcon(getClass().getClassLoader().getResource("")); // add picture here
		 fieldLabel[1].setIcon(fieldImage);
		 
		 fieldPanel[1].add(fieldLabel[1]);
	 }
	 
	 public void createObjects(int fNumber, int objx, int objy, int objWidth, int objHeight, String objFileNAme) {
		 
		 
		 
	 }

}
