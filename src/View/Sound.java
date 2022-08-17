package View;

import java.io.*;
import javax.sound.sampled.*;

public class Sound {

	Clip myDoorSound;
	Clip myChestSound;
	Clip myMusic;
	Clip myWinSound;
	Clip myLoseSound;
	Clip myLockSound;
	Clip myClickSound;
	
	public Sound() {
		
		setDoorFile("sound effect/door sound.wav");
		setChestFile("sound effect/chest sound.wav");
		setMusicFile("sound effect/Medieval-Village-Theme.wav");
		setWinFile("sound effect/win.wav");
		setLoseFile("sound effect/lose.wav");
		setLockFile("sound effect/lock.wav");
		setClickFile("sound effect/click.wav");
		
	}

	/**
	 * Set music file for door sound effect.
	 * 
	 * @param theFilePath the wav file path
	 */
	public void setDoorFile(String theFilePath) {

		try {
			File file = new File(theFilePath);
			AudioInputStream AIS = AudioSystem.getAudioInputStream(file);
			myDoorSound = AudioSystem.getClip();
			myDoorSound.open(AIS);
		} 
		catch (Exception e) {
			System.out.println("DOOR ERROR");
		}
	}
	
	/**
	 * Set music file for chest sound effect.
	 * 
	 * @param theFilePath the wav file path
	 */
	public void setChestFile(String theFilePath) {

		try {
			File file = new File(theFilePath);
			AudioInputStream AIS = AudioSystem.getAudioInputStream(file);
			myChestSound = AudioSystem.getClip();
			myChestSound.open(AIS);
		} 
		catch (Exception e) {
			System.out.println("CHEST ERROR");
		}
	}
	
	/**
	 * Set music file for music sound effect.
	 * 
	 * @param theFilePath the wav file path
	 */
	public void setMusicFile(String theFilePath) {

		try {
			File file = new File(theFilePath);
			AudioInputStream AIS = AudioSystem.getAudioInputStream(file);
			myMusic = AudioSystem.getClip();
			myMusic.open(AIS);
		} 
		catch (Exception e) {
			System.out.println("MUSIC ERROR");
		}
	}
	
	/**
	 * Set music file for win sound effect.
	 * 
	 * @param theFilePath the wav file path
	 */
	public void setWinFile(String theFilePath) {

		try {
			File file = new File(theFilePath);
			AudioInputStream AIS = AudioSystem.getAudioInputStream(file);
			myWinSound = AudioSystem.getClip();
			myWinSound.open(AIS);
		} 
		catch (Exception e) {
			System.out.println("WIN ERROR");
		}
	}
	
	/**
	 * Set music file for lose sound effect.
	 * 
	 * @param theFilePath the wav file path
	 */
	public void setLoseFile(String theFilePath) {

		try {
			File file = new File(theFilePath);
			AudioInputStream AIS = AudioSystem.getAudioInputStream(file);
			myLoseSound = AudioSystem.getClip();
			myLoseSound.open(AIS);
		} 
		catch (Exception e) {
			System.out.println("LOSE ERROR");
		}
	}
	
	/**
	 * Set music file for lock sound effect.
	 * 
	 * @param theFilePath the wav file path
	 */
	public void setLockFile(String theFilePath) {

		try {
			File file = new File(theFilePath);
			AudioInputStream AIS = AudioSystem.getAudioInputStream(file);
			myLockSound = AudioSystem.getClip();
			myLockSound.open(AIS);
		} 
		catch (Exception e) {
			System.out.println("LOCK ERROR");
		}
	}
	
	/**
	 * Set music file for click sound effect.
	 * 
	 * @param theFilePath the wav file path
	 */
	public void setClickFile(String theFilePath) {

		try {
			File file = new File(theFilePath);
			AudioInputStream AIS = AudioSystem.getAudioInputStream(file);
			myClickSound = AudioSystem.getClip();
			myClickSound.open(AIS);
		} 
		catch (Exception e) {
			System.out.println("CLICK ERROR");
		}
	}
	
	/**
	 * To play choosen sound effect.
	 * 
	 * @param theType different sound effects
	 */
	public void play(String theType) {
		
		switch (theType) {

		case "DOOR":
			myDoorSound.setFramePosition(0);
			myDoorSound.start();
			break;
			
		case "CHEST":
			myChestSound.setFramePosition(0);
			myChestSound.start();
			break;
			
		case "MUSIC":
			myMusic.setFramePosition(1);
			myMusic.start();
			break;
			
		case "WIN":
			myWinSound.setFramePosition(2);
			myWinSound.start();
			break;
			
		case "LOSE":
			myLoseSound.setFramePosition(2);
			myLoseSound.start();
			break;
			
		case "LOCK":
			myLockSound.setFramePosition(0);
			myLockSound.start();
			break;
			
		case "CLICK":
			myClickSound.setFramePosition(0);
			myClickSound.start();
			break;
			
		}
	}
	
	/**
	 * Play the music in loop.
	 */
	public void loop() {
		
		myMusic.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * Stop the playing the music.
	 */
	public void stop() {
		
		myMusic.stop();
		myMusic.close();
	}
}
