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
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.SqliteDB;
import Model.Door;
import Model.Maze;
import Model.Question;
import Model.Room;
import Model.Room.Direction;

public class GUI implements Serializable {

	private final long mySerialVersionUID = -471174395510626265L;
	private TriviaMazeMain myTmm;
	private JFrame myWindow;
	private JTextArea myMessageText;
	private JPanel myFieldPanel[] = new JPanel[10];
	private JLabel myFieldLabel[] = new JLabel[10];
	private JMenuBar myMenuBar;
	private JMenu myMenu[] = new JMenu[10];
	private JMenuItem myMenuItem;
	private JButton myDoorUp, myDoorDown, myDoorLeft, myDoorRight, myChestClosed, myEnterButton;
	private JLabel myChestOpened, myTextLabel;
	private Container myContainer;
	private JPanel myTextPanel, inputPanel;
	private Font normalFont = new Font("Times New Roman", Font.PLAIN, 26);
	private JTextField myJTF;
	Maze myMazeMap = new Maze(new BorderLayout());
	private int myRoomTokenWidth, myRoomTokenHeight, myPlayerTokenWidth, myDoorDepth;
	Room myGrid[][];
	private int myGridWidth = 5;
	private int myGridHeight = 5;
	Point myGridLocation = new Point(0, 0);
	private boolean myKey = false;

	public JTextField getJTF() {
		return myJTF;
	}

	public JLabel getTextLabel() {
		return myTextLabel;
	}

	public JButton getChestClosed() {
		return myChestClosed;
	}

	public JLabel getChestOpened() {
		return myChestOpened;
	}

	public boolean getKey() {
		return myKey;
	}

	public void setKey(boolean theHasKey) {
		myKey = theHasKey;
	}
	
	public Point getMyGridLocation() {
		return myGridLocation;
	}
	
	public Maze getMyMazeMap() {
		return myMazeMap;
	}
	
	public Room getMyGrid(int theM, int theN) {
		return myGrid[theM][theN];
	}

	public Room getMyCurrentRoom() {
		return myGrid[myGridLocation.x][myGridLocation.y];
	}

	public void setMessageText(String str) {
		myMessageText.setText("");
		myMessageText.append(str);
		myMessageText.paintImmediately(myMessageText.getBounds());
	}

	public GUI(TriviaMazeMain theTmm) {

		this.myTmm = theTmm;

		createFrame();
		createMeunBar();
		creatQuestionBox(myMessageText);
		createMainField();
		creatInoutBox(myEnterButton);
		createChest(true);
		createDoors();
		createMap();

		myWindow.setVisible(true);
	}

	public void createFrame() {
		// the main window.
		myWindow = new JFrame("Trivia Maze");
		myWindow.setSize(1115, 900);
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myWindow.getContentPane().setBackground(Color.black);
		myWindow.setLayout(null);
		myContainer = myWindow.getContentPane();
	}

	public void createMeunBar() {

		myMenuBar = new JMenuBar();
		myMenu[1] = new JMenu("File");
		// menu.setMnemonic(KeyEvent.VK_A);
		// menu.getAccessibleContext().setAccessibleDescription(
		// "The only menu in this program that has menu items");
		myMenuBar.add(myMenu[1]);

		// reset the maze, the rooms, the doors, the questions
		myMenuItem = new JMenuItem("New Game");
		myMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				reset();
			}
		});
		myMenu[1].add(myMenuItem);

		// save the game
		myMenuItem = new JMenuItem("Save Game");
		myMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				try {
					save(null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		myMenu[1].add(myMenuItem);

		// load the save
		myMenuItem = new JMenuItem("Load Game");
		myMenuItem.addActionListener(new ActionListener() {
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
		myMenu[1].add(myMenuItem);

		// close the game
		myMenuItem = new JMenuItem("Exit");
		myMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				myWindow.dispose();
			}
		});
		myMenu[1].add(myMenuItem);

		myMenu[2] = new JMenu("Help");
		myMenuBar.add(myMenu[2]);
		myMenuItem = new JMenuItem("About");
		myMenu[2].add(myMenuItem);
		myMenuItem = new JMenuItem("Game Play Instructions");
		myMenu[2].add(myMenuItem);
		myWindow.setJMenuBar(myMenuBar);
	}

	public void creatQuestionBox(JTextArea theMessageText) {

		myMessageText = new JTextArea("Questions and descriptions");
		myMessageText.setBounds(600, 0, 500, 500);
		myMessageText.setBackground(Color.red);
		myMessageText.setForeground(Color.white);
		myMessageText.setEditable(false);
		myMessageText.setLineWrap(true);
		myMessageText.setWrapStyleWord(true);
		myMessageText.setFont((new Font("Test", Font.BOLD, 30)));
		myWindow.add(myMessageText);

	}

	public void reset() {
		myMessageText.setVisible(false);
		myMessageText = new JTextArea("test");
		myMessageText.setBounds(1200, 0, 400, 1200);
		myMessageText.setBackground(Color.red);
		myMessageText.setForeground(Color.white);
		myMessageText.setEditable(false);
		myMessageText.setLineWrap(true);
		myMessageText.setWrapStyleWord(true);
		myMessageText.setFont((new Font("Test", Font.BOLD, 30)));
		myWindow.add(myMessageText);

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

	public void createMainField() {

		myFieldPanel[1] = new JPanel();
		myFieldPanel[1].setBounds(36, 36, 528, 528);
		myFieldPanel[1].setBackground(Color.yellow);
		myFieldPanel[1].setLayout(null);
		myWindow.add(myFieldPanel[1]);

		myFieldLabel[1] = new JLabel();
		myFieldLabel[1].setBounds(0, 0, 528, 528);

		ImageIcon roomBackgroud = new ImageIcon(getClass().getClassLoader().getResource("room.png"));
		myFieldLabel[1].setIcon(roomBackgroud);

	}

	public void createDoors() {

		myDoorUp = new JButton();
		myDoorUp.setBounds(240, 0, 48, 96);
		myDoorUp.setBackground(null);
		myDoorUp.setContentAreaFilled(false);
		myDoorUp.setFocusPainted(false);
		// doorTop.setBorderPainted(false);
		myDoorUp.addActionListener(myTmm.aHandler);
		myDoorUp.setActionCommand("goForward");

		myDoorDown = new JButton();
		myDoorDown.setBounds(240, 432, 48, 96);
		myDoorDown.setBackground(null);
		myDoorDown.setContentAreaFilled(false);
		myDoorDown.setFocusPainted(false);
		// doorBottom.setBorderPainted(false);
		myDoorDown.addActionListener(myTmm.aHandler);
		myDoorDown.setActionCommand("goBack");

		myDoorLeft = new JButton();
		myDoorLeft.setBounds(0, 216, 48, 96);
		myDoorLeft.setBackground(null);
		myDoorLeft.setContentAreaFilled(false);
		myDoorLeft.setFocusPainted(false);
		// doorLeft.setBorderPainted(false);
		myDoorLeft.addActionListener(myTmm.aHandler);
		myDoorLeft.setActionCommand("goLeft");

		myDoorRight = new JButton();
		myDoorRight.setBounds(480, 216, 48, 96);
		myDoorRight.setBackground(null);
		myDoorRight.setContentAreaFilled(false);
		myDoorRight.setFocusPainted(false);
		// doorRight.setBorderPainted(false);
		myDoorRight.addActionListener(myTmm.aHandler);
		myDoorRight.setActionCommand("goRight");

		ImageIcon door = new ImageIcon(getClass().getClassLoader().getResource("door.png"));
		myDoorUp.setIcon(door);
		myDoorDown.setIcon(door);
		myDoorLeft.setIcon(door);
		myDoorRight.setIcon(door);

		myFieldPanel[1].add(myDoorUp);
		myFieldPanel[1].add(myDoorDown);
		myFieldPanel[1].add(myDoorLeft);
		myFieldPanel[1].add(myDoorRight);

		myFieldPanel[1].add(myFieldLabel[1]);
	}

	public void createChest(boolean ifClosed) {

		myChestClosed = new JButton();
		myChestClosed.setBounds(240, 240, 48, 48);
		myChestClosed.setBackground(null);
		myChestClosed.setContentAreaFilled(false);
		myChestClosed.setFocusPainted(false);
		myChestClosed.setBorderPainted(false);
		myChestClosed.addActionListener(myTmm.aHandler);
		myChestClosed.setActionCommand("openChest");

		myChestOpened = new JLabel();
		myChestOpened.setBounds(240, 240, 48, 48);

		ImageIcon chestClosed = new ImageIcon(getClass().getClassLoader().getResource("chest closed.png"));
		ImageIcon chestopened = new ImageIcon(getClass().getClassLoader().getResource("chest opened.png"));
		myChestClosed.setIcon(chestClosed);
		myChestOpened.setIcon(chestopened);

		myFieldPanel[1].add(myChestClosed);
		myFieldPanel[1].add(myChestOpened);

		myChestClosed.setVisible(true);
	}

	public void creatInoutBox(JButton theEnterB) {

		myTextPanel = new JPanel();
		myTextPanel.setBounds(50, 620, 500, 100);
		myTextPanel.setBackground(Color.black);

		myTextLabel = new JLabel("Enter your answer here");
		myTextLabel.setForeground(Color.white);
		myTextLabel.setFont(normalFont);

		myTextPanel.add(myTextLabel);
		myContainer.add(myTextPanel);

		inputPanel = new JPanel();
		inputPanel.setBounds(50, 720, 500, 50);
		inputPanel.setBackground(Color.black);
		inputPanel.setLayout(new GridLayout(1, 2));

		myJTF = new JTextField();
		inputPanel.add(myJTF);

		myEnterButton = new JButton("ENTER");
		myEnterButton.setForeground(Color.black);
		myEnterButton.addActionListener(myTmm.iHandler);
		myEnterButton.addActionListener(myTmm.aHandler);
		myEnterButton.setActionCommand("enter answer");

		inputPanel.add(myEnterButton);
		myContainer.add(inputPanel);

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
		} while (!linkedList.isEmpty());
		return false;
	}

	public boolean win() {
		return myGridLocation.x == myGrid.length - 1 && myGridLocation.y == myGrid[0].length - 1;
	}

	public void createMap() {

		myMazeMap.setBounds(700, 500, 300, 300);
		myMazeMap.setBackground(Color.white);
		myMazeMap.setLayout(null);
		try {
			generate(myGridWidth, myGridHeight);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myWindow.add(myMazeMap);

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

	// theM = theN = 5
	private void generate(int theM, int theN) throws SQLException {

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

		SqliteDB db = new SqliteDB();
		
		Door tempDoor;
		for (int i = 0; i < theM; i++) {
			for (int j = 0; j < theN; j++) {
				if (i + 1 < theM) {	
					tempDoor = new Door( (myRoomTokenWidth * i) + myRoomTokenWidth - (myDoorDepth / 4), (myRoomTokenHeight * j) + (myRoomTokenHeight / 4),
							myDoorDepth / 2, myRoomTokenHeight / 2, db.getRandomQuestion(myTmm));
					myGrid[i][j].setDoor( Direction.RIGHT, tempDoor);
					myGrid[i+1][j].setDoor( Direction.LEFT, tempDoor);
				}
				if (j + 1 < theN) { 
					tempDoor = new Door( (myRoomTokenWidth * i) + (myRoomTokenWidth / 4), (myRoomTokenHeight * j) + myRoomTokenHeight - (myDoorDepth / 4),
							myRoomTokenWidth / 2, myDoorDepth / 2, db.getRandomQuestion(myTmm));
					myGrid[i][j].setDoor( Direction.DOWN, tempDoor );
					myGrid[i][j+1].setDoor( Direction.UP, tempDoor );
				}
			}
		}
		db.closeConnection();
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
		myGrid[myGridLocation.x][myGridLocation.y].setOpenOrLock(theDirection, myEnterButton, myMessageText, myMazeMap, this);
		draw();
	}
}