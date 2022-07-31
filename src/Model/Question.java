package maze;

import java.io.Serializable;

public class Question implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2463437303534008861L;
	private String myQuestion;
	private String[] myAnswers;
	private int myCorrect;
	
	public Question(String theQuestion, String[] theAnswers, int theCorrect) {
		myQuestion = theQuestion;
		myAnswers = theAnswers;
		myCorrect = theCorrect;
	}
	
	public boolean attempt(int theInput) {
		boolean correctAnswer = false;
		if (theInput == myCorrect) {
			correctAnswer = true;
		}
		return correctAnswer;
	}
	
}
