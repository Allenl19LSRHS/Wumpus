package wumpus.elements;
import wumpus.main.*;

// RoomElement for the Wumpus. If player has repellent, they survive contact, otherwise they die
public class WumpusElement extends RoomElement {

	public void handle() {
		if (WumpusGame.hasRepellent) {
			System.out.println("You walk into the same room as the Wumpus, but your repellent scares it off!");
			WumpusGame.map.moveWumpus();
			WumpusGame.hasRepellent = false;
			System.out.println("Your Wumpus Repellent has worn off.");
		} else {
			System.out.println("You are eaten by the wumpus!");
			WumpusGame.gameActive = false;
		}
	}
	
	public void printSenses() {
		System.out.println("You smell a wumpus.");
	}
}
