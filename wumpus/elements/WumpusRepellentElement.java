package wumpus.elements;

import wumpus.main.WumpusGame;

// RoomElement that gives the player a repellent that allows them to survive one contact with the Wumpus
public class WumpusRepellentElement extends RoomElement {
	public void handle() {
		System.out.println("You find a stick of single-use Wumpus repellent, and apply it");
		WumpusGame.hasRepellent = true;
		WumpusGame.map.getRoom(WumpusGame.currentRoomIndex).removeElement();
	}
	
	public void printSenses() {
		System.out.println("Something smells weird nearby.");
	}
}
