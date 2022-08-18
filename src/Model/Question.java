package Model;

import java.io.*;

import View.TriviaMazeMain;

public class Question implements Serializable {

	private static final long serialVersionUID = 4620495814422831485L;

	public enum QuestionType {
		TF, MC, SA
	};

	private QuestionType myType;
	private String myQuestion;
	private String[] myAnswers;
	private int myCorrect;

	public Question(String theQuestion, String[] theAnswers, int theCorrect, String theType, TriviaMazeMain theTmm) {

		myQuestion = theQuestion;
		myAnswers = theAnswers;
		myCorrect = theCorrect;

		switch (theType) {

		case "SA":
			myType = QuestionType.SA;
			break;

		case "MC":
			myType = QuestionType.MC;
			break;

		case "TF":
			myType = QuestionType.TF;
			break;

		default:
			myType = null;
		}
	}

	/**
	 * Get question type.
	 * 
	 * @return enum type
	 */
	public QuestionType getType() {
		
		return myType;
	}

	/**
	 * To see if the attempt is correct.
	 * 
	 * @param theInput
	 * @return true if correct
	 */
	public boolean correctAnswer(int theInput) {

		boolean correctAnswer = false;

		if (theInput == myCorrect) {
			correctAnswer = true;
		}

		return correctAnswer;
	}

	/**
	 * Get question
	 * 
	 * @return a question
	 */
	protected String getQuestion() {

		String temp = "";

		switch (myType) {

		case MC:
			temp = myQuestion;
			for (int i = 0; i < 4; i++) {
				temp = temp + "\n" + (i + 1) + ") " + myAnswers[i];
			}
			break;

		case SA:
			temp = myQuestion;
			break;

		case TF:
			temp = "True or False?\n" + myQuestion;
			break;
		}

		return temp;
	}

	/**
	 * Get Answer
	 * 
	 * @param theIndex index number
	 * @return corresponding answer
	 */
	protected String getAnswer(final int theIndex) {
		
		return myAnswers[theIndex];
	}

}
