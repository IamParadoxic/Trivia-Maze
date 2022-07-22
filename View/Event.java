package GUI;

public class Event {

	TriviaMazeMain tmm;
	
	public Event(TriviaMazeMain tmm) {
		this.tmm = tmm;
	}
	
	public void openChest() {
		tmm.gui.chestB.setVisible(false);
	}
	
	public void goForward() {

	}
	
	public void goBack() {
		
	}
	
	public void goLeft() {
		
	}
	
	public void goRight() {
		
	}
}
