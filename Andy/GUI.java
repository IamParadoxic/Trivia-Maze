package View;

import java.awt.BorderLayout;
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
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

import Model.*;
import Model.Room.Direction;
import Model.Room;
import Model.Maze;
import Model.Door.AccessLevel;
import Model.Door;
import Model.Question;

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
	private JButton doorTop;
	private JButton doorBottom;
	private JButton doorLeft;
	private JButton doorRight;
	private JButton chestB;
	private JButton enterB;
	public JLabel chestL;
	public static JLabel textLabel;
	public Container con;
	public JPanel textPanel, inputPanel;
	public Font normalFont = new Font("Times New Roman", Font.PLAIN, 26);
	public static JTextField jtf;
	private Question myQuestion;
	public Maze myMazeMap = new Maze(new BorderLayout());
	private int myRoomTokenWidth;
	private int myRoomTokenHeight;
	private int myPlayerTokenWidth;
	private int myDoorDepth;
	public Room myGrid[][];
	private int myGridWidth = 5;
	private int myGridHeight = 5;
	public Point myGridLocation = new Point(0,0);
	private boolean myKey = false;

	public JTextField getJtf() {
		return jtf;
	}

	public JLabel getTextLabel() {
		return textLabel;
	}

	public JButton getChestB() {
		return chestB;
	}

	public JLabel getChestL() {
		return chestL;
	}

	public JButton getDoorTop() {
		return doorTop;
	}

	public JButton getDoorBottom() {
		return doorBottom;
	}

	public JButton getDoorLeft() {
		return doorLeft;
	}

	public JButton getDoorRight() {
		return doorRight;
	}

	public JTextArea getMessageText() {
		return messageText;
	}

	public Room getGridRoom(int m, int n) {
		return myGrid[m][n];
	}

	public Point getGridLocation() {
		return myGridLocation;
	}

	public Question getQuestion() {
		return myQuestion;
	}

	public boolean getKey() {
		return myKey;
	}

	public void setKey(boolean have) {
		myKey = have;
	}

	public Room getMyCurrentRoom() {
		return myGrid[myGridLocation.x][myGridLocation.y];
	}


	public void setMessageText(String str) {
		messageText.setText("");
		messageText.append(str);
		messageText.paintImmediately(messageText.getBounds());  
	}

	public GUI(TriviaMazeMain tmm) {

		this.tmm = tmm;

		createFrame();
		createMeunBar();
		creatQuestionBox(messageText);
		createMainField();
		creatInoutBox(enterB);
		createChest(true);
		createDoors();
		createMap();

		window.setVisible(true);

	}

	public void createFrame() {
		// the main window.
		window = new JFrame("Trivia Maze");
		window.setSize(1100, 900);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		con = window.getContentPane();
		generate(myGridWidth, myGridHeight);
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

	public void creatQuestionBox(JTextArea theMessageText) {

		messageText = new JTextArea("Questions and descriptions");
		messageText.setBounds(600, 0, 500, 500);
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

	public void createMap() {

		myMazeMap.setBounds(700, 500, 300, 300);
		myMazeMap.setBackground(Color.white);
		myMazeMap.setLayout(null);
		generate(myGridWidth, myGridHeight);
		window.add(myMazeMap);

	}

	public void draw() {

		if (!myMazeMap.hasPlayerToken()){	
			myMazeMap.addPlayerToken(25, 25, 10, 10);
		}

		final GUI thisMap = this;

		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[i].length; j++) {
				myGrid[i][j].draw(myMazeMap);
			}
		}

		myMazeMap.repaint();
	}

	private void generate(int theM, int theN) {

		myRoomTokenWidth = myMazeMap.getWidth() / 5;
		myRoomTokenHeight = myMazeMap.getWidth() / 5;
		myPlayerTokenWidth = myRoomTokenWidth / 5;
		myDoorDepth = (myRoomTokenWidth + myRoomTokenHeight) / 8;

		myGrid = new Room[theM][];
		for (int i = 0; i < theM; i++) {
			myGrid[i] = new Room[theN];
			for (int j = 0; j < theN; j++) {
				myGrid[i][j] = new Room(myRoomTokenWidth*i,myRoomTokenHeight*j, 
						myRoomTokenWidth, myRoomTokenHeight, new Point(i,j));	
			}
		}

		myGridLocation.setLocation(0, 0);

		Door tempDoor;
		for (int i = 0; i < theM; i++) {
			for (int j = 0; j < theN; j++) {
				if (i + 1 < theM) {	
					tempDoor = new Door( (myRoomTokenWidth * i) + myRoomTokenWidth - (myDoorDepth / 4), (myRoomTokenHeight * j) + (myRoomTokenHeight / 4),
							myDoorDepth / 2, myRoomTokenHeight / 2);
					myGrid[i][j].setDoor( Direction.RIGHT, tempDoor);
					myGrid[i+1][j].setDoor( Direction.LEFT, tempDoor);
				}
				if (j + 1 < theN) { 
					tempDoor = new Door( (myRoomTokenWidth * i) + (myRoomTokenWidth / 4), (myRoomTokenHeight * j) + myRoomTokenHeight - (myDoorDepth / 4),
							myRoomTokenWidth / 2, myDoorDepth / 2);
					myGrid[i][j].setDoor( Direction.DOWN, tempDoor );
					myGrid[i][j+1].setDoor( Direction.UP, tempDoor );
				}
			}
		}
		draw();
	}

	public void moveToken(int theM, int theN) {
		if(theM <= 4 || theN <= 4) {
		myGridLocation.setLocation(theM, theN);
		myMazeMap.movePlayer((myGridLocation.x * myRoomTokenWidth) + (myRoomTokenWidth / 2) - (myPlayerTokenWidth / 2),
				(myGridLocation.y * myRoomTokenHeight) + (myRoomTokenHeight / 2) - (myPlayerTokenWidth / 2));
		myMazeMap.repaint();
		}
	}
	
	public void setOpenOrLock(Direction theDirection) {
		myGrid[myGridLocation.x][myGridLocation.y].setOpenOrLock(theDirection, enterB, messageText, myMazeMap, this);
		draw();
	}

	// int fNumber, int objx, int objy, int objWidth, int objHeight, String objFileNAme
	public void createDoors() {

		doorTop = new JButton();
		doorTop.setBounds(240, 0, 48, 96);
		doorTop.setBackground(null);
		doorTop.setContentAreaFilled(false);
		doorTop.setFocusPainted(false);
		//		doorTop.setBorderPainted(false);
		doorTop.addActionListener(tmm.aHandler);
		doorTop.setActionCommand("goForward");

		doorBottom = new JButton();
		doorBottom.setBounds(240, 432, 48, 96);
		doorBottom.setBackground(null);
		doorBottom.setContentAreaFilled(false);
		doorBottom.setFocusPainted(false);
		//		doorBottom.setBorderPainted(false);
		doorBottom.addActionListener(tmm.aHandler);
		doorBottom.setActionCommand("goBack");

		doorLeft = new JButton();
		doorLeft.setBounds(0, 216, 48, 96);
		doorLeft.setBackground(null);
		doorLeft.setContentAreaFilled(false);
		doorLeft.setFocusPainted(false);
		//		doorLeft.setBorderPainted(false);
		doorLeft.addActionListener(tmm.aHandler);
		doorLeft.setActionCommand("goLeft");

		doorRight = new JButton();
		doorRight.setBounds(480, 216, 48, 96);
		doorRight.setBackground(null);
		doorRight.setContentAreaFilled(false);
		doorRight.setFocusPainted(false);
		//		doorRight.setBorderPainted(false);
		doorRight.addActionListener(tmm.aHandler);
		doorRight.setActionCommand("goRight");

		ImageIcon door = new ImageIcon(getClass().getClassLoader().getResource("door.png"));
		doorTop.setIcon(door);
		doorBottom.setIcon(door);
		doorLeft.setIcon(door);
		doorRight.setIcon(door);

		fieldPanel[1].add(doorTop);
		fieldPanel[1].add(doorBottom);
		fieldPanel[1].add(doorLeft);
		fieldPanel[1].add(doorRight);

		fieldPanel[1].add(fieldLabel[1]);
	}

	public void createChest(boolean ifClosed) {

		chestB = new JButton();
		chestB.setBounds(240, 240, 48, 48);
		chestB.setBackground(null);
		chestB.setContentAreaFilled(false);
		chestB.setFocusPainted(false);
		chestB.setBorderPainted(false);
		chestB.addActionListener(tmm.aHandler);
		chestB.setActionCommand("openChest");

		chestL = new JLabel();
		chestL.setBounds(240, 240, 48, 48);

		ImageIcon chestClosed = new ImageIcon(getClass().getClassLoader().getResource("chest closed.png"));
		ImageIcon chestopened = new ImageIcon(getClass().getClassLoader().getResource("chest opened.png"));
		chestB.setIcon(chestClosed);
		chestL.setIcon(chestopened);

		fieldPanel[1].add(chestB);
		fieldPanel[1].add(chestL);

		chestB.setVisible(true);
	}

	public void creatInoutBox(JButton theEnterB) {

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

		enterB = new JButton("ENTER");
		enterB.setForeground(Color.black);
		enterB.addActionListener(tmm.iHandler);
		enterB.setActionCommand("enter answer");

		inputPanel.add(enterB);
		con.add(inputPanel);

	}

	public boolean hasPath() {
		LinkedList<Room> linkedList = new LinkedList<Room>();
		HashSet<Room> hashSet = new HashSet<Room>();
		linkedList.push(myGrid[myGridLocation.x][myGridLocation.y]);
		Room current = null;
		int x, y;
		do {
			current = linkedList.pop();
			hashSet.add(current);
			if (hashSet.contains(myGrid[myGrid.length - 1][myGrid[0].length - 1])) {
				return true;
			}
			x = current.getX();
			y = current.getY();
			if (x + 1 < myGrid.length) {
				if (!myGrid[x][y].getDoor(Direction.RIGHT).isLocked() && !hashSet.contains(myGrid[x + 1][y])) {
					linkedList.push(myGrid[x + 1][y]);
					hashSet.add(myGrid[x + 1][y]);
				}
			}
			if (x - 1 >= 0) {
				if (!myGrid[x][y].getDoor(Direction.LEFT).isLocked() && !hashSet.contains(myGrid[x - 1][y])) {
					linkedList.push(myGrid[x - 1][y]);
					hashSet.add(myGrid[x - 1][y]);
				}
			}
			if (y + 1 < myGrid[0].length) {
				if (!myGrid[x][y].getDoor(Direction.DOWN).isLocked() && !hashSet.contains(myGrid[x][y + 1])) {
					linkedList.push(myGrid[x][y + 1]);
					hashSet.add(myGrid[x][y + 1]);
				}
			}
			if (y - 1 >= 0) {
				if (!myGrid[x][y].getDoor(Direction.UP).isLocked() && !hashSet.contains(myGrid[x][y - 1])) {
					linkedList.push(myGrid[x][y - 1]);
					hashSet.add(myGrid[x][y - 1]);
				}
			}
		} while(!linkedList.isEmpty());
		return false;
	}

	public boolean win() {
		return myGridLocation.x == myGrid.length - 1 && myGridLocation.y == myGrid[0].length - 1;
	}

}