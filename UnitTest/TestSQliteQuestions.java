import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Model.Question;
import Model.SqliteDB;
import Model.Question.QuestionType;
import View.TriviaMazeMain;

class TestingQuestions {
	private final TriviaMazeMain myTmm = new TriviaMazeMain();;
	private static SqliteDB db;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		db = new SqliteDB();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		db.closeConnection();
	}


	
	@Test
	void shortAnswertTest1
		Question temp = db.getFromID(2, myTmm);
		assertTrue(temp.correctAnswer(0));
		assertEquals(QuestionType.SA, temp.getType());
	}
	
	@Test
	void trueFalseTest1
		Question temp = db.getFromID(28, myTmm);
		Question temp2 = db.getFromID(1, myTmm);
		assertTrue(temp.correctAnswer(1));
		assertTrue(temp2.correctAnswer(0));
		assertEquals(QuestionType.TF, temp.getType());
	}
	
	@Test
	void shortAnswerTest2
		Question temp = db.getFromID(2, myTmm);
		assertEquals("Brown", temp.getAnswer(0));
	}
	
	@Test
	void trueFalseTest2 {
		Question temp = db.getFromID(1, myTmm);
		assertEquals("True", temp.getAnswer(0));
		assertEquals("False", temp.getAnswer(1));
	}
	
	@Test
	void multipleChoiceTest
		Question temp = db.getFromID(3, myTmm);
		ArrayList<String> answers = new ArrayList<>();
		for(int i = 0; i <= 3; i++) {
			answers.add(temp.getAnswer(i));
		}
		assertEquals(QuestionType.MC, temp.getType());
		
		assertTrue(answers.contains("Eve"));
		assertTrue(answers.contains("Lilith"));
		assertTrue(answers.contains("Artemis"));
		assertTrue(answers.contains("Pandora"));
		
		assertTrue(temp.correctAnswer(answers.indexOf("Pandora")));
	}
	
	@Test
	void outOFBoundsIdTest1 {
		Question temp = db.getFromID(0, myTmm);
		assertNull(temp);
	}
	
	@Test
	void outOFBoundsIdTest2) {
		Question temp = db.getFromID(420, myTmm);
		assertNull(temp);
	}

}
