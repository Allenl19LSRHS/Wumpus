package wumpus.elements;
import wumpus.main.*;

// RoomElement that kills you
public class PitElement extends RoomElement {
	public void handle() {
		System.out.println("You fall into a bottemless pit!");
		WumpusGame.gameActive = false;
	}
	
	public void printSenses() {
		System.out.println("You feel a draft.");
	}
}
