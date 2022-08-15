package Model;

import java.io.*;
import javax.swing.*;

import View.TriviaMazeMain;

public class Question implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2463437303534008861L;
	
	public enum QuestionType {TF, MC,SA};
	
	private QuestionType myType;
	
	private String myQuestion;
	private String[] myAnswers;
	private int myCorrect;
	public TriviaMazeMain tmm;
	
	public Question(String theQuestion, String[] theAnswers, int theCorrect, String theType, TriviaMazeMain tmm) {
		myQuestion = theQuestion;
		myAnswers = theAnswers;
		myCorrect = theCorrect;
		this.tmm = tmm;
		switch(theType) {
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
	
	public QuestionType getType() {
		return myType;
	}
	
	public boolean attempt(int theInput) {
		boolean correctAnswer = false;
		if (theInput == myCorrect) {
			correctAnswer = true;
		}
		return correctAnswer;
	}
	
	public void textQuestion(JTextArea theMessageText, JLabel[] theAnswerLabels) {
		
		theMessageText.selectAll();
		theMessageText.replaceSelection(myQuestion);
		theMessageText.setVisible(true);
		
		for (int i = 0; i < myAnswers.length; i++) {
			theAnswerLabels[i].setText(myAnswers[i]);
			theAnswerLabels[i].setVisible(true);
		}
	}
	
	public void textAnswers(JButton theEnterB, JLabel theQuestionLabel, JTextArea theMessageText) {
		
		for (int i = 0; i < theEnterB.getAncestorListeners().length; i++) {
			if (theEnterB.getActionListeners().length != 0) {
				theEnterB.removeActionListener(theEnterB.getActionListeners()[0]);
				theEnterB.setVisible(false);
			}
			if (i == myCorrect) {
				theMessageText.selectAll();
				theMessageText.replaceSelection("Correct");
				theMessageText.setVisible(true);
			} else {
				theMessageText.selectAll();
				theMessageText.replaceSelection("Wrong");
				theMessageText.setVisible(true);
			}
		}
	}
	
	//lil
	protected String getQuestion() {
		String temp = "";
		switch(myType) {
		case MC:
			temp = myQuestion;
			for(int i = 0; i < 4; i++) {
				temp = temp + "\n" + (i+1) + ") " + myAnswers[i]; 
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
	String getAnswer(final int theIndex) {
		return myAnswers[theIndex];
	}

}
