package Controller;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Question;
import View.TriviaMazeMain;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Question;
import View.TriviaMazeMain;

public class SqliteDB {

	private Connection c = null;
	private Statement stmt = null;
	private ArrayList<Integer> track = new ArrayList<>();

	/**
	 * constructor that sets up the connect to the SQLite database, allowing for interacting with it from within the code
	 */
	public SqliteDB() {
		// try to connect to DB
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:trivia.sqlite");
			System.out.println("connected to db");
		} catch (Exception e) {
			System.out.println("Expection: " + e.getMessage());
		}
	}

	/**
	 * quick lil method that the user just calls to generate a random question from
	 * the database, using the method from which you can generate an question object
	 * from with the id of it makes sure that the question returned isn't one that
	 * has been generated before, using an arraylist that keeps track of what ids
	 * have been used to create a question object
	 * 
	 * @param tmm
	 * @return
	 * @throws SQLException
	 */
	public Question getRandomQuestion(TriviaMazeMain tmm) throws SQLException {
		this.stmt = c.createStatement();
		ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) AS total FROM TriviaQuestions");
		int total = rs2.getInt("total");
		Random rand = new Random();
		int ran = rand.nextInt(total) + 1;
		while (track.contains(ran)) {
			ran = rand.nextInt(total) + 1;
		}
		return getFromID(ran, tmm);
	}

	// method that creates a question object based on a certain id that is passed
	// through from the user
	public Question getFromID(final int theId, TriviaMazeMain tmm) {
		try {
			this.stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM TriviaQuestions WHERE id = " + theId); // gets all the
																									// information from
																									// the row with this
																									// id
			if (rs.next()) {
				String q = rs.getString("Question");
				String type = rs.getString("Type");
				String correct = rs.getString("Answer");
				String[] answers = new String[4];
				Random rand = new Random();
				int correctIndex = 0;
				// if statement that will randomize where in the array of answers the correct
				// one is if it's multiple choice, and will then fill the array with the
				// preprovided wrong answers
				if (type.equals("MC")) {
					correctIndex = rand.nextInt(4);
					answers[correctIndex] = correct;
					for (int i = 0; i < 3; i++) {
						if (correctIndex == i) {
							answers[3] = rs.getString("Wrong" + (i + 1));
						} else {
							answers[i] = rs.getString("Wrong" + (i + 1));
						}
					}
				}
				// check if the question is a true false one, and depending on the answer, sets
				// the correct index to 0 or 1, true or false
				else if (type.equals("TF")) {
					if (correct.equals("True")) {
						correctIndex = 0;
					} else {
						correctIndex = 1;
					}
					answers[0] = "True";
					answers[1] = "False";
				}
				// now if it's not a mc or tf, then it has to be short answer, which will store
				// the correct answer in the 0 index
				else {
					answers[0] = correct;
				}

				track.add(theId);
				return new Question(q, answers, correctIndex, type, tmm);
			}
		} catch (Exception e) {
			System.out.print("Expection: " + e);
		}
		return null;
	}

	// method to close the connect with the database
	public void closeConnection() {
		try {
			c.close();
		} catch (Exception e) {
			// error
		}
	}
}
