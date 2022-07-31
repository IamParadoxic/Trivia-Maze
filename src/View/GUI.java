package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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

import Model.TriviaMazeMain;

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

	public GUI(TriviaMazeMain tmm) {

		this.tmm = tmm;

		createFrame();
		createMeunBar();
		creatQuestionBox();
		createMainField();
		creatInoutBox();
		createChest(true);
		createDoors();

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
		// menu.setMnemonic(KeyEvent.VK_A);
		// menu.getAccessibleContext().setAccessibleDescription(
		// "The only menu in this program that has menu items");
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
		menu[2].add(menuItem);
		menuItem = new JMenuItem("Game Play Instructions");
		menu[2].add(menuItem);
		window.setJMenuBar(menuBar);
	}

	public void creatQuestionBox() {
		
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
		messageText.setVisible(false);
		messageText = new JTextArea("test");
		messageText.setBounds(1200, 0, 400, 1200);
		messageText.setBackground(Color.red);
		messageText.setForeground(Color.white);
		messageText.setEditable(false);
		messageText.setLineWrap(true);
		messageText.setWrapStyleWord(true);
		messageText.setFont((new Font("Test", Font.BOLD, 30)));
		window.add(messageText);

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

		doorTop = new JButton();
		doorTop.setBounds(240, 0, 48, 96);
		doorTop.setBackground(null);
		doorTop.setContentAreaFilled(false);
		doorTop.setFocusPainted(false);
		doorTop.setBorderPainted(false);
		doorTop.addActionListener(tmm.aHandler);
		doorTop.setActionCommand("goForward");

		doorBottom = new JButton();
		doorBottom.setBounds(240, 432, 48, 96);
		doorBottom.setBackground(null);
		doorBottom.setContentAreaFilled(false);
		doorBottom.setFocusPainted(false);
		doorBottom.setBorderPainted(false);
		doorBottom.addActionListener(tmm.aHandler);
		doorBottom.setActionCommand("goBack");

		doorLeft = new JButton();
		doorLeft.setBounds(0, 216, 48, 96);
		doorLeft.setBackground(null);
		doorLeft.setContentAreaFilled(false);
		doorLeft.setFocusPainted(false);
		doorLeft.setBorderPainted(false);
		doorLeft.addActionListener(tmm.aHandler);
		doorLeft.setActionCommand("goLeft");

		doorRight = new JButton();
		doorRight.setBounds(480, 216, 48, 96);
		doorRight.setBackground(null);
		doorRight.setContentAreaFilled(false);
		doorRight.setFocusPainted(false);
		doorRight.setBorderPainted(false);
		doorRight.addActionListener(tmm.aHandler);
		doorRight.setActionCommand("goBack");

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
		
		enterB = new JButton("ENTER");
		enterB.setForeground(Color.black);
		enterB.addActionListener(tmm.iHandler);
		enterB.setActionCommand("enter answer");
		
		inputPanel.add(enterB);
		con.add(inputPanel);
		
	}
	
	
}