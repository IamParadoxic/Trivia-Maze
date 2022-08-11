
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
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
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
	public JButton doorTop, doorBottom, doorLeft, doorRight, chestB, enterB;
	public JLabel chestL;
	public static JLabel textLabel;
	public Container con;
	public JPanel textPanel, inputPanel;
	public Font normalFont = new Font("Times New Roman", Font.PLAIN, 26);
	public static JTextField jtf;
	private Room myGrid[][];
	private Point myGridLocation = new Point(0,0);
	private Question myQuestion;
	private Maze myMazeMap = new Maze(new BorderLayout());
	private int myRoomTokenWidth;
	private int myRoomTokenHeight;
	private int myPlayerTokenWidth;
	private int myDoorDepth;

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
		myMazeMap.setBackground(Color.yellow);
		myMazeMap.setLayout(null);
		window.add(myMazeMap);

	}

	public void draw() {
		if (!myMazeMap.hasPlayerToken()){	
			myMazeMap.addPlayerToken((myGridLocation.x * myRoomTokenWidth) + (myRoomTokenWidth / 2) - (myPlayerTokenWidth / 2),
					(myGridLocation.y * myRoomTokenHeight) + (myRoomTokenHeight / 2) - (myPlayerTokenWidth / 2),
					myPlayerTokenWidth, myPlayerTokenWidth);
		}

		final GUI thisMap = this;

		doorTop.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				if (myGrid[myGridLocation.x][myGridLocation.y].myDoors[0] != null) {
					if (myGrid[myGridLocation.x][myGridLocation.y].myDoors[0].isOpened()) {
						moveToken(myGridLocation.x, myGridLocation.y - 1);
						myMazeMap.repaint();
						if (win()) {
							System.out.println("YOU WIN!");
							JOptionPane.showMessageDialog(null, "YOU WIN!");
							reset();
						}

					} else {
						myGrid[myGridLocation.x][myGridLocation.y].setOpenOrLock(Direction.UP, enterB, messageText, myMazeMap, thisMap);
						System.out.println(Direction.UP.toString() + " is " + myGrid[myGridLocation.x][myGridLocation.y].isLocked((Direction.UP)));
					}
				} else {
					System.out.println(Direction.UP.toString() + " is DEAD END");
				}
			}
		});

		doorRight.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				if (myGrid[myGridLocation.x][myGridLocation.y].myDoors[1] != null) {
					if (myGrid[myGridLocation.x][myGridLocation.y].myDoors[1].isOpened()) {
						moveToken(myGridLocation.x + 1, myGridLocation.y);
						myMazeMap.repaint();
						if (win()) {
							System.out.println("YOU WIN!");
							JOptionPane.showMessageDialog(null, "YOU WIN!");
							reset();
						}
					} else {
						myGrid[myGridLocation.x][myGridLocation.y].setOpenOrLock(Direction.RIGHT, enterB, messageText, myMazeMap, thisMap);
						System.out.println(Direction.RIGHT.toString() + " is " + myGrid[myGridLocation.x][myGridLocation.y].isLocked(Direction.RIGHT));
					}
				} else {
					System.out.println(Direction.RIGHT.toString() + " is DEAD END");
				}
			}
		});

		doorBottom.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				if (myGrid[myGridLocation.x][myGridLocation.y].myDoors[2] != null) {
					if (myGrid[myGridLocation.x][myGridLocation.y].myDoors[2].isOpened()) {
						moveToken(myGridLocation.x, myGridLocation.y + 1);
						myMazeMap.repaint();
						if (win()) {
							System.out.println("YOU WIN!");
							JOptionPane.showMessageDialog(null, "YOU WIN!");
							reset();
						}
					} else {
						myGrid[myGridLocation.x][myGridLocation.y].setOpenOrLock(Direction.DOWN, enterB, messageText, myMazeMap, thisMap);
						System.out.println(Direction.DOWN.toString() + " is " + myGrid[myGridLocation.x][myGridLocation.y].isLocked(Direction.DOWN));
					}
				} else {
					System.out.println(Direction.DOWN.toString() + " is DEAD END");
				}
			}
		});

		doorLeft.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				if (myGrid[myGridLocation.x][myGridLocation.y].myDoors[3] != null) {
					if (myGrid[myGridLocation.x][myGridLocation.y].myDoors[3].isOpened()) {
						moveToken(myGridLocation.x - 1, myGridLocation.y);
						myMazeMap.repaint();
						if (win()) {
							System.out.println("YOU WIN!");
							JOptionPane.showMessageDialog(null, "YOU WIN!");
							reset();
						}
					} else {
						myGrid[myGridLocation.x][myGridLocation.y].setOpenOrLock(Direction.LEFT, enterB, messageText, myMazeMap, thisMap);
						System.out.println(Direction.LEFT.toString() + " is " + myGrid[myGridLocation.x][myGridLocation.y].isLocked(Direction.LEFT));
					}
				} else {
					System.out.println(Direction.LEFT.toString() + " is DEAD END");
				}
			}
		});

		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[i].length; j++) {
				myGrid[i][j].draw(myMazeMap, inputPanel);
			}
		}

		// Draws updated map to the GUI.
		window.repaint();
	}

	public void moveToken(int theX, int theY) {
		myGridLocation.setLocation(theX, theY);
		myMazeMap.movePlayer((myGridLocation.x * myRoomTokenWidth) + (myRoomTokenWidth / 2) - (myPlayerTokenWidth / 2),
				(myGridLocation.y * myRoomTokenHeight) + (myRoomTokenHeight / 2) - (myPlayerTokenWidth / 2));
		myMazeMap.repaint();
	}



	// int fNumber, int objx, int objy, int objWidth, int objHeight, String objFileNAme
	public void createDoors() {

		doorTop = new JButton();
		doorTop.setBounds(240, 0, 48, 96);
		doorTop.setBackground(null);
		doorTop.setContentAreaFilled(false);
		doorTop.setFocusPainted(false);
		doorTop.setBorderPainted(false);
		doorTop.addActionListener(tmm.aHandler);
		doorTop.setActionCommand("goForward");
		doorTop.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		doorBottom = new JButton();
		doorBottom.setBounds(240, 432, 48, 96);
		doorBottom.setBackground(null);
		doorBottom.setContentAreaFilled(false);
		doorBottom.setFocusPainted(false);
		doorBottom.setBorderPainted(false);
		doorBottom.addActionListener(tmm.aHandler);
		doorBottom.setActionCommand("goBack");
		doorBottom.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		doorLeft = new JButton();
		doorLeft.setBounds(0, 216, 48, 96);
		doorLeft.setBackground(null);
		doorLeft.setContentAreaFilled(false);
		doorLeft.setFocusPainted(false);
		doorLeft.setBorderPainted(false);
		doorLeft.addActionListener(tmm.aHandler);
		doorLeft.setActionCommand("goLeft");
		doorLeft.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});


		doorRight = new JButton();
		doorRight.setBounds(480, 216, 48, 96);
		doorRight.setBackground(null);
		doorRight.setContentAreaFilled(false);
		doorRight.setFocusPainted(false);
		doorRight.setBorderPainted(false);
		doorRight.addActionListener(tmm.aHandler);
		doorRight.setActionCommand("goBack");
		doorRight.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

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
