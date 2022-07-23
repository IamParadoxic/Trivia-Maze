package GUI;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqliteDB {
	Connection c = null;
	Statement stmt = null;

	
	//constructor that sets up the connect to the SQLite database, allowing for interactin with it from within the code
	SqliteDB(){
		//try to connect to DB
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:trivia.sqlite");
			System.out.println("connected to db");
		} catch (Exception e) {
			System.out.println("Expection: " + e.getMessage());
		}
	}
	
	//method that gets certain information from the database based on the id and the info string parameters. 
	public String getFromID(final int theId, final String theInfo) {
		try {
			this.stmt =c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM TriviaQuestions WHERE id = " + theId); //gets all the information from the row with this id
			while(rs.next()) {
				return rs.getString(theInfo); //returns the coresponding info is in the column pointed to in this string here
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
