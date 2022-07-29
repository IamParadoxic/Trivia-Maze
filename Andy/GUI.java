package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.Room;

public class GUI implements Serializable{

	private static final long serialVersionUID = -471174395510626265L;

	TriviaMazeMain tmm;

	JFrame window;
	public JTextArea messageText;
	public JPanel fieldPanel[] = new JPanel[10];
	public JLabel fieldLabel[] = new JLabel[10];
	public JMenuBar menuBar;
	public JMenu menu[] = new JMenu[10];
	public JMenuItem menuItem;
	public JButton doorTop[] = new JButton[10];
	public JButton doorBottom[] = new JButton[10];
	public JButton doorLeft[] = new JButton[10];
	public JButton doorRight[] = new JButton[10];
	public JButton chestB[] = new JButton[10];
	public JButton enterB[] = new JButton[10];
	public JLabel chestL;
	public static JLabel textLabel;
	public Container con;
	public JPanel textPanel, inputPanel;
	public Font normalFont = new Font("Times New Roman", Font.PLAIN, 26);
	public static JTextField jtf;
	private Room myGrid[][] = new Room[100][100];
	private int myGridWidth;
	private int myGridHeight;

	public GUI(TriviaMazeMain tmm) {

		this.tmm = tmm;

		createFrame();
		createMeunBar();
		creatQuestionBox(null);
		createMainField();
		creatInoutBox();
		createChest(true);
		createDoors();

		window.setVisible(true);

	}
	
	public void GameMap(int theM, int theN){
		myGridWidth = theM;
		myGridHeight = theN;
	}

	public void createFrame() {
		// the main window.
		window = new JFrame("Trivia Maze");
		window.setSize(1100, 900);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		con = window.getContentPane();
	}

	public void createMeunBar() {

		menuBar = new JMenuBar();
		menu[1] = new JMenu("File");
		menuBar.add(menu[1]);

		// reset the maze, the rooms, the doors, the questions
		menuItem = new JMenuItem("New Game");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				reset();
			}
		});
		menu[1].add(menuItem);

		// save the game
		menuItem = new JMenuItem("Save Game");
		menuItem.addActionListener(new ActionListener() {
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
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				try {
					load(null);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		menu[1].add(menuItem);

		// close the game
		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				window.dispose();
			}
		});
		menu[1].add(menuItem);

		menu[2] = new JMenu("Help");
		menuBar.add(menu[2]);

		menuItem = new JMenuItem("About");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				JOptionPane.showMessageDialog(null, "Version 1.0", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menu[2].add(menuItem);

		menuItem = new JMenuItem("Game Play Instructions");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				JOptionPane.showMessageDialog(null, "Use keyboard and mouse to play." 
						+ "\nAnswer the quetions to open the doors."
						+ "\nGo from left-top conner to right-bottom conner to win!" 
						, "Game Play Instructions", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menu[2].add(menuItem);

		window.setJMenuBar(menuBar);

	}

	public void creatQuestionBox(String rightText) {

		messageText = new JTextArea("Questions and descriptions");
		messageText.setBounds(600, 0, 500, 1100);
		messageText.setBackground(Color.red);
		messageText.setForeground(Color.white);
		messageText.setEditable(false);
		messageText.setLineWrap(true);
		messageText.setWrapStyleWord(true);
		messageText.setFont((new Font("Test", Font.BOLD, 30)));
		window.add(messageText);

	}

	public void reset() {
		
		window.dispose();
		new TriviaMazeMain();
	}

	public void save(String theSave) throws IOException {

		FileOutputStream fileOut = new FileOutputStream("Saved.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(theSave);
		out.flush();
		out.close();
		fileOut.close();

		JOptionPane.showMessageDialog(null, "Saved!");

	}

	public void load(String theSave) throws IOException, ClassNotFoundException {

		FileInputStream fileIn = new FileInputStream("Saved.ser");
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(theSave));
		in.readObject();
		in.close();
		fileIn.close();

	}

	public void createMainField() {

		fieldPanel[1] = new JPanel();
		fieldPanel[1].setBounds(36, 36, 528, 528);
		fieldPanel[1].setBackground(Color.yellow);
		fieldPanel[1].setLayout(null);
		window.add(fieldPanel[1]);

		fieldLabel[1] = new JLabel();
		fieldLabel[1].setBounds(0, 0, 528, 528);

		ImageIcon roomBackgroud = new ImageIcon(getClass().getClassLoader().getResource("room.png"));
		fieldLabel[1].setIcon(roomBackgroud);

	}

	// int fNumber, int objx, int objy, int objWidth, int objHeight, String objFileNAme
	public void createDoors() {

		doorTop[1] = new JButton();
		doorTop[1].setBounds(240, 0, 48, 96);
		doorTop[1].setBackground(null);
		doorTop[1].setContentAreaFilled(false);
		doorTop[1].setFocusPainted(false);
		doorTop[1].setBorderPainted(false);
		doorTop[1].addActionListener(tmm.aHandler);
		doorTop[1].setActionCommand("goForward");

		doorBottom[1] = new JButton();
		doorBottom[1].setBounds(240, 432, 48, 96);
		doorBottom[1].setBackground(null);
		doorBottom[1].setContentAreaFilled(false);
		doorBottom[1].setFocusPainted(false);
		doorBottom[1].setBorderPainted(false);
		doorBottom[1].addActionListener(tmm.aHandler);
		doorBottom[1].setActionCommand("goBack");

		doorLeft[1] = new JButton();
		doorLeft[1].setBounds(0, 216, 48, 96);
		doorLeft[1].setBackground(null);
		doorLeft[1].setContentAreaFilled(false);
		doorLeft[1].setFocusPainted(false);
		doorLeft[1].setBorderPainted(false);
		doorLeft[1].addActionListener(tmm.aHandler);
		doorLeft[1].setActionCommand("goLeft");

		doorRight[1] = new JButton();
		doorRight[1].setBounds(480, 216, 48, 96);
		doorRight[1].setBackground(null);
		doorRight[1].setContentAreaFilled(false);
		doorRight[1].setFocusPainted(false);
		doorRight[1].setBorderPainted(false);
		doorRight[1].addActionListener(tmm.aHandler);
		doorRight[1].setActionCommand("goBack");

		ImageIcon door = new ImageIcon(getClass().getClassLoader().getResource("door.png"));
		doorTop[1].setIcon(door);
		doorBottom[1].setIcon(door);
		doorLeft[1].setIcon(door);
		doorRight[1].setIcon(door);

		fieldPanel[1].add(doorTop[1]);
		fieldPanel[1].add(doorBottom[1]);
		fieldPanel[1].add(doorLeft[1]);
		fieldPanel[1].add(doorRight[1]);

		fieldPanel[1].add(fieldLabel[1]);
	}

	public void createChest(boolean ifClosed) {

		chestB[1] = new JButton();
		chestB[1].setBounds(240, 240, 48, 48);
		chestB[1].setBackground(null);
		chestB[1].setContentAreaFilled(false);
		chestB[1].setFocusPainted(false);
		chestB[1].setBorderPainted(false);
		chestB[1].addActionListener(tmm.aHandler);
		chestB[1].setActionCommand("openChest");

		chestL = new JLabel();
		chestL.setBounds(240, 240, 48, 48);

		ImageIcon chestClosed = new ImageIcon(getClass().getClassLoader().getResource("chest closed.png"));
		ImageIcon chestopened = new ImageIcon(getClass().getClassLoader().getResource("chest opened.png"));
		chestB[1].setIcon(chestClosed);
		chestL.setIcon(chestopened);

		fieldPanel[1].add(chestB[1]);
		fieldPanel[1].add(chestL);

		chestB[1].setVisible(true);
	}
	
	public void creatInoutBox() {
		
		textPanel = new JPanel();
		textPanel.setBounds(50, 620, 500, 100);
		textPanel.setBackground(Color.black);
		
		textLabel = new JLabel("Enter your answer here");
		textLabel.setForeground(Color.white);
		textLabel.setFont(normalFont);
		
		textPanel.add(textLabel);
		con.add(textPanel);
		
		inputPanel = new JPanel();
		inputPanel.setBounds(50, 720, 500, 50);
		inputPanel.setBackground(Color.black);
		inputPanel.setLayout(new GridLayout(1,2));
		
		jtf = new JTextField();
		inputPanel.add(jtf);
		
		enterB[1] = new JButton("ENTER");
		enterB[1].setForeground(Color.black);
		enterB[1].addActionListener(tmm.iHandler);
		enterB[1].setActionCommand("enter answer");
		
		inputPanel.add(enterB[1]);
		con.add(inputPanel);
		
	}
	
	public void createRooms(int theM, int theN){
		
		
		myGrid = new Room[theM][];
		for (int i = 0; i < theM; i++) {
			myGrid[i] = new Room[theN];
			for (int j = 0; j < theN; j++) {
				myGrid[i][j] = new Room();	
			}
		}
		
	}
	
}
