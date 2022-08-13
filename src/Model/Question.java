package Model;

import java.io.*;
import javax.swing.*;

import View.TriviaMazeMain;

public class Question implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2463437303534008861L;
	private String myQuestion;
	private String[] myAnswers;
	private int myCorrect;
	public TriviaMazeMain tmm;
	
	public Question(String theQuestion, String[] theAnswers, int theCorrect, TriviaMazeMain tmm) {
		myQuestion = theQuestion;
		myAnswers = theAnswers;
		myCorrect = theCorrect;
		this.tmm = tmm;
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

}
