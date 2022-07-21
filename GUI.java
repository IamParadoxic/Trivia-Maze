package Maze;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.TriviaMazeMain;


public class GUI implements Serializable {

	/**
	 *  Generated serial ID randomly.
	 */
	private static final long serialVersionUID = -471174395510626265L;

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

		// reset the maze, the rooms, the doors, the questions
		menuItem = new JMenuItem("New Game");
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				reset(); 
			}
		});
		menu[1].add(menuItem);

		// save the game
		menuItem = new JMenuItem("Save Game");
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				try {
					save(null);
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		});
		menu[1].add(menuItem);

		// load the save
		menuItem = new JMenuItem("Load Game");
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				try {
					load(null);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} // need to add the function
			}
		});
		menu[1].add(menuItem);

		// close the game
		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				window.dispose();
			}
		});
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

	public void reset() {
		messageText1.setVisible(false);
		messageText1 = new JTextArea("test");
		messageText1.setBounds(1200,0,400,1200);
		messageText1.setBackground(Color.red);
		messageText1.setForeground(Color.white);
		messageText1.setEditable(false);
		messageText1.setLineWrap(true);
		messageText1.setWrapStyleWord(true);
		messageText1.setFont((new Font("Test", Font.BOLD, 30)));
		window.add(messageText1);

	}

	public void save(String theSave) throws IOException {
		
		FileOutputStream fileOut = new FileOutputStream("Saved.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(theSave); 
		out.flush();
		out.close();
		fileOut.close();
		
		System.out.println("Game is saved!");
	
	}

	public void load(String theSave) throws IOException, ClassNotFoundException {
		
		FileInputStream fileIn = new FileInputStream("Saved.ser");
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(theSave));
		in.readObject();
		in.close();
		fileIn.close();


	}

}
