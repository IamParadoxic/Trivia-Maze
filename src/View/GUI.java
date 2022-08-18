package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.sql.*;

import Controller.SqliteDB;
import Model.Door;
import Model.Maze;
import Model.Room;
import Model.Room.Direction;

public class GUI implements Serializable {

	private static final long serialVersionUID = -7944338666566093998L;

	private TriviaMazeMain myTmm;
	private JFrame myWindow;
	private JTextArea myMessageText;
	private JPanel myFieldPanel[] = new JPanel[10];
	private JLabel myFieldLabel[] = new JLabel[10];
	private JMenuBar myMenuBar;
	private JMenu myMenu[] = new JMenu[10];
	private JMenuItem myMenuItem;
	private JButton myDoorUp, myDoorDown, myDoorLeft, myDoorRight, myChestClosed, myEnterButton, myStartButton;
	private JLabel myChestOpened, myTextLabel, myStartLabel;
	private Container myContainer;
	private JPanel myTextPanel, inputPanel, myStartPanel;
	private Font NORMAL_FONT = new Font("Times New Roman", Font.PLAIN, 26);
	private Font BIGGER_FONT = new Font("Times New Roman", Font.PLAIN, 50);
	private Font TITLE_FONT = new Font("Times New Roman", Font.PLAIN, 120);
	private JTextField myJTF;
	private Maze myMazeMap = new Maze(new BorderLayout());
	private int myRoomWidth, myRoomHeight, myPlayerWidth, myDoorDepth;
	private Room myGrid[][];
	private int myGridWidth = 5;
	private int myGridHeight = 5;
	private Point myGridLocation = new Point(0, 0);
	private boolean myKey = false;
	private Room myHasChest;

	public GUI(TriviaMazeMain theTmm) {

		this.myTmm = theTmm;

		createFrame();
		createMeunBar();
		createStartPage();
		creatQuestionBox(myMessageText);
		createMainField();
		creatInputBox(myEnterButton);
		createChest(true);
		createDoors();
		createMap();

		// play BGM
		myTmm.sound.play("MUSIC");
		myTmm.sound.loop();

		myWindow.setVisible(true);
	}

	/**
	 * To create the main Frame where everything is.
	 */
	public void createFrame() {

		myWindow = new JFrame("Trivia Maze");
		myWindow.setSize(1120, 900);
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myWindow.getContentPane().setBackground(Color.black);
		myWindow.setLayout(null);
		myContainer = myWindow.getContentPane();
	}

	/**
	 * Create the titel page and start button.
	 */
	public void createStartPage() {

		myStartPanel = new JPanel();
		myStartPanel.setBounds(0, 0, 1120, 900);
		myStartPanel.setBackground(Color.black);

		myStartLabel = new JLabel("TRIVIA  MAZE");
		myStartLabel.setForeground(Color.white);
		myStartLabel.setFont(TITLE_FONT);

		myStartButton = new JButton("START");
		myStartButton.setFont(BIGGER_FONT);
		myStartButton.setForeground(Color.white);
		myStartButton.setBounds(460, 400, 200, 100);
		myStartButton.setBackground(null);
		myStartButton.setContentAreaFilled(false);
		myStartButton.setFocusPainted(false);
		myStartButton.addActionListener(myTmm.aHandler);
		myStartButton.setActionCommand("start");

		myWindow.add(myStartButton);
		myStartPanel.add(myStartLabel);
		myWindow.add(myStartPanel);
	}

	/**
	 * Create menu bar with all choices.
	 */
	public void createMeunBar() {

		myMenuBar = new JMenuBar();
		myMenu[1] = new JMenu("File");
		myMenuBar.add(myMenu[1]);

		// reset the maze, the rooms, the doors, the questions
		myMenuItem = new JMenuItem("New Game");
		myMenuItem.addActionListener((ActionListener) new ActionListener() {
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
					save("save.ser");
					JOptionPane.showMessageDialog(null, "Game saved!");
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
					load("save.ser");
					JOptionPane.showMessageDialog(null, "Game loaded!");
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
		myMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				JOptionPane.showMessageDialog(null, "Version 1.0", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		myMenu[2].add(myMenuItem);

		myMenuItem = new JMenuItem("Game Play Instructions");
		myMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				JOptionPane.showMessageDialog(null,
						"Use keyboard and mouse to play." + "\nAnswer the quetions to open the doors."
								+ "\nGo from left-top conner to right-bottom conner to win!",
								"Game Play Instructions", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		myMenu[2].add(myMenuItem);
		myMenu[2].add(myMenuItem);

		myWindow.setJMenuBar(myMenuBar);
	}

	/**
	 * To create the question box.
	 * 
	 * @param theMessageText the message printed in the box
	 */
	public void creatQuestionBox(JTextArea theMessageText) {

		myMessageText = new JTextArea("Questions and descriptions");
		myMessageText.setBounds(600, 0, 500, 500);
		myMessageText.setBackground(Color.black);
		myMessageText.setForeground(Color.white);
		myMessageText.setEditable(false);
		myMessageText.setLineWrap(true);
		myMessageText.setWrapStyleWord(true);
		myMessageText.setFont((new Font("Test", Font.BOLD, 30)));
		myWindow.add(myMessageText);
	}

	/**
	 * Close the game and reopen it.
	 */
	public void reset() {

		myTmm.sound.stop();
		myWindow.dispose();
		new TriviaMazeMain();
	}

	/**
	 * Save all data using serializtion in a .ser file.
	 * 
	 * @param theSave .ser file
	 * @throws IOException null
	 */
	public void save(String theSave) throws IOException {

		try {

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(theSave));
			out.writeObject(myGrid);
			out.writeObject(myMazeMap);
			out.writeObject(myGridWidth);
			out.writeObject(myGridHeight);
			out.writeObject(myGridLocation);
			out.writeObject(myHasChest);
			out.flush();
			out.close();

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Load all data from .ser file useing serializtion.
	 * 
	 * @param theSave .ser file
	 * @throws IOException null
	 * @throws ClassNotFoundException if there is no .ser file
	 */
	public void load(String theSave) throws IOException, ClassNotFoundException {

		try {

			ObjectInputStream in = new ObjectInputStream(new FileInputStream(theSave));
			myGrid = (Room[][]) in.readObject();
			in.readObject();
			myGridWidth = (int) in.readObject();
			myGridHeight = (int) in.readObject();
			myGridLocation = (Point) in.readObject();
			myHasChest = (Room) in.readObject();
			movePlayer((int) myGridLocation.getX(), (int) myGridLocation.getY());
			in.close();

			Set<Door> doors = new HashSet<Door>();
			for (int i = 0; i < myGridWidth; i++) {
				for (int j = 0; j < myGridHeight; j++) {
					if (myGrid[i][j].getDoor(Direction.UP) != null) {
						doors.add(myGrid[i][j].getDoor(Direction.UP));
					}
					if (myGrid[i][j].getDoor(Direction.DOWN) != null) {
						doors.add(myGrid[i][j].getDoor(Direction.DOWN));
					}
					if (myGrid[i][j].getDoor(Direction.LEFT) != null) {
						doors.add(myGrid[i][j].getDoor(Direction.LEFT));
					}
					if (myGrid[i][j].getDoor(Direction.RIGHT) != null) {
						doors.add(myGrid[i][j].getDoor(Direction.RIGHT));
					}
				}
			}

			Iterator<Door> iterator = doors.iterator();
			Door temp = null;

			while (iterator.hasNext()) {
				temp = iterator.next();
				if (temp.getAccessLevel() == Model.Door.AccessLevel.LOCKED) {
					temp.addLock(myMazeMap);
				} else if (temp.getAccessLevel() == Model.Door.AccessLevel.OPEN) {
					temp.addOpen(myMazeMap);
				}
			}

			if (getMyCurrentRoom().getHasChest() == true) {
				myTmm.gui.getChestClosed().setVisible(true);
			} else if (getMyCurrentRoom().getHasChest() == false) {
				myTmm.gui.getChestClosed().setVisible(false);
			}

			draw();
			myMazeMap.repaint();

		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	/**
	 * To create the main room picture.
	 */
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

	/**
	 * To create doors in the room, and link it with action handler
	 */
	public void createDoors() {

		myDoorUp = new JButton();
		myDoorUp.setBounds(240, 0, 48, 96);
		myDoorUp.setBackground(null);
		myDoorUp.setContentAreaFilled(false);
		myDoorUp.setFocusPainted(false);
		myDoorUp.addActionListener(myTmm.aHandler);
		myDoorUp.setActionCommand("goUp");

		myDoorDown = new JButton();
		myDoorDown.setBounds(240, 432, 48, 96);
		myDoorDown.setBackground(null);
		myDoorDown.setContentAreaFilled(false);
		myDoorDown.setFocusPainted(false);
		myDoorDown.addActionListener(myTmm.aHandler);
		myDoorDown.setActionCommand("goDown");

		myDoorLeft = new JButton();
		myDoorLeft.setBounds(0, 216, 48, 96);
		myDoorLeft.setBackground(null);
		myDoorLeft.setContentAreaFilled(false);
		myDoorLeft.setFocusPainted(false);
		myDoorLeft.addActionListener(myTmm.aHandler);
		myDoorLeft.setActionCommand("goLeft");

		myDoorRight = new JButton();
		myDoorRight.setBounds(480, 216, 48, 96);
		myDoorRight.setBackground(null);
		myDoorRight.setContentAreaFilled(false);
		myDoorRight.setFocusPainted(false);
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

		myDoorUp.setVisible(false);
		myDoorDown.setVisible(false);
		myDoorLeft.setVisible(false);
		myDoorRight.setVisible(false);
	}

	/**
	 * To create cheat chest and link it with action handler.
	 * 
	 * @param ifClosed if the chest is closed
	 */
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

		myChestClosed.setVisible(false);
	}

	/**
	 * To create the input box below.
	 * 
	 * @param theEnterB the enter button
	 */
	public void creatInputBox(JButton theEnterB) {

		myTextPanel = new JPanel();
		myTextPanel.setBounds(50, 620, 500, 100);
		myTextPanel.setBackground(Color.black);

		myTextLabel = new JLabel("Enter your answer here");
		myTextLabel.setForeground(Color.white);
		myTextLabel.setFont(NORMAL_FONT);

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

	/**
	 * To check if there is still path to the winning room.
	 * 
	 * @return false means lost
	 */
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

	/**
	 * To check if the player reaches the final room.
	 * 
	 * @return true means won
	 */
	public boolean win() {

		return myGridLocation.x == myGrid.length - 1 && myGridLocation.y == myGrid[0].length - 1;
	}

	/**
	 * Create a mini map on the left down corner.
	 */
	public void createMap() {

		myMazeMap.setBounds(700, 500, 300, 300);
		myMazeMap.setBackground(Color.white);
		myMazeMap.setLayout(null);

		try {
			generate(myGridWidth, myGridHeight);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		myWindow.add(myMazeMap);
	}

	/**
	 * Draw the player token showed in the min map.
	 */
	public void draw() {

		if (!myMazeMap.hasPlayer()) {
			myMazeMap.addPlayer(25, 25, 10, 10);
		}

		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[i].length; j++) {
				myGrid[i][j].draw(myMazeMap);
			}
		}

		myMazeMap.repaint();
	}

	/**
	 * Generate the entire maze.
	 * 
	 * @param theM Width X = 5
	 * @param theN Height Y = 5
	 * @throws SQLException
	 */
	private void generate(int theM, int theN) throws SQLException {

		myRoomWidth = myMazeMap.getWidth() / 5;
		myRoomHeight = myMazeMap.getWidth() / 5;
		myPlayerWidth = myRoomWidth / 5;
		myDoorDepth = (myRoomWidth + myRoomHeight) / 8;

		myGrid = new Room[theM][];
		for (int i = 0; i < theM; i++) {
			myGrid[i] = new Room[theN];
			for (int j = 0; j < theN; j++) {
				myGrid[i][j] = new Room(myRoomWidth * i, myRoomHeight * j, myRoomWidth,
						myRoomHeight, new Point(i, j));
			}
		}

		myGridLocation.setLocation(0, 0);

		SqliteDB db = new SqliteDB();

		Door door;
		for (int i = 0; i < theM; i++) {
			for (int j = 0; j < theN; j++) {
				if (i + 1 < theM) {
					door = new Door((myRoomWidth * i) + myRoomWidth - (myDoorDepth / 4),
							(myRoomHeight * j) + (myRoomHeight / 4), myDoorDepth / 2, myRoomHeight / 2,
							db.getRandomQuestion(myTmm));
					myGrid[i][j].setDoor(Direction.RIGHT, door);
					myGrid[i + 1][j].setDoor(Direction.LEFT, door);
				}
				if (j + 1 < theN) {
					door = new Door((myRoomWidth * i) + (myRoomWidth / 4),
							(myRoomHeight * j) + myRoomHeight - (myDoorDepth / 4), myRoomWidth / 2,
							myDoorDepth / 2, db.getRandomQuestion(myTmm));
					myGrid[i][j].setDoor(Direction.DOWN, door);
					myGrid[i][j + 1].setDoor(Direction.UP, door);
				}
			}
		}
		db.closeConnection();
		draw();
	}

	/**
	 * Move player token and track room/door status.
	 * 
	 * @param theM Coordinate x
	 * @param theN Coordinate y
	 */
	public void movePlayer(int theM, int theN) {

		if (theM <= 4 || theN <= 4) {
			myGridLocation.setLocation(theM, theN);
			int x = (myGridLocation.x * myRoomWidth) + (myRoomWidth / 2) - (myPlayerWidth / 2);
			int y = (myGridLocation.y * myRoomHeight) + (myRoomHeight / 2) - (myPlayerWidth / 2);
			myMazeMap.movePlayer(x,y);
			myMazeMap.repaint();
		}
	}

	/**
	 * To set door status.
	 * 
	 * @param theDirection witch door in the room
	 */
	public void setOpenOrLock(Direction theDirection) {

		myGrid[myGridLocation.x][myGridLocation.y].setOpenOrLock(theDirection, myEnterButton, myMessageText, myMazeMap, this);
		draw();
	}

	/**
	 * Print out the message in question box.
	 * 
	 * @param theString Things developer want to put
	 */
	public void setMessageText(String theString) {

		myMessageText.setText("");
		myMessageText.append(theString);
		myMessageText.paintImmediately(myMessageText.getBounds());
	}

	/**
	 * To play the lose sound effect
	 */
	public void playLoseSound() {

		myTmm.sound.stop();
		myTmm.sound.play("LOSE");
	}

	/**
	 * Helper method that set start page invisiable.
	 */
	public void startGame() {

		if(getMyCurrentRoom().getHasChest() == true) {
			myChestClosed.setVisible(true);
		}

		myStartPanel.setVisible(false);
		myStartButton.setVisible(false);
	}

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

	public JButton getDoorUp() {
		return myDoorUp;
	}

	public JButton getDoorDown() {
		return myDoorDown;
	}

	public JButton getDoorLeft() {
		return myDoorLeft;
	}

	public JButton getDoorRight() {
		return myDoorRight;
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
}
