package Controller;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Question;
import Model.TriviaMazeMain;

public class SqliteDB {
	Connection c = null;
	Statement stmt = null;
	private ArrayList<Integer> track = new ArrayList<>();


	//constructor that sets up the connect to the SQLite database, allowing for interactin with it from within the code
	public SqliteDB(){
		//try to connect to DB
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:trivia.sqlite");
			System.out.println("connected to db");
		} catch (Exception e) {
			System.out.println("Expection: " + e.getMessage());
		}
	}

	//quick lil method that the user just calls to generate a random question from the database, using the method from which you can generate an question object from with the id of it
	//makes sure that the question returned isn't one that has been generated before, using an arraylist that keeps track of what ids have been used to create a question object
	public Question getRandomQuestion(TriviaMazeMain tmm) throws SQLException {
		this.stmt = c.createStatement();
		ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) AS total FROM TriviaQuestions");
		int total = rs2.getInt("total");
		Random rand = new Random();
		int ran = rand.nextInt(total) + 1;
		while(track.contains(ran)) {
			ran = rand.nextInt(total) + 1;
		}
		return getFromID(ran, tmm);
	}


	//method that creates a question object based on a certain id that is passed through from the user
	public Question getFromID(final int theId, TriviaMazeMain tmm) {
		try {
			this.stmt =c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM TriviaQuestions WHERE id = " + theId); //gets all the information from the row with this id
			if(rs.next()) {
				String q = rs.getString("Question");
				String type = rs.getString("Type");
				String correct = rs.getString("Answer");
				String[] answers = new String[4];
				Random rand = new Random();
				int correctIndex = rand.nextInt(4) + 1;
				for(int i = 1; i <= 3; i++) {
					if(correctIndex == i) {
						answers[i-1] = correct;
						answers[3] = rs.getString("Wrong"+i);
					}
					else {
						answers[i-1] = rs.getString("Wrong"+i);						
					}
				}
				track.add(theId);
				return new Question(q, answers, (correctIndex - 1), type, tmm);
			}
		} catch (Exception e) {
			System.out.print("Expection: " + e);
		}
		return null;
	}

	//method to close the connect with the database
	public void closeConnection() {
		try {
			c.close();
		} catch(Exception e) {
			//error
		}
	}
}
