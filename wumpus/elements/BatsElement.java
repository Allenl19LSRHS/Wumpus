package wumpus.elements;
import wumpus.main.*;

// RoomElement that has bats that carry you to a new random empty room
public class BatsElement extends RoomElement {
	public void handle() {
		System.out.println("Bats pick you up and carry you off!");
		int newRoom = WumpusGame.map.randomEmptyRoom();
		WumpusGame.currentRoomIndex = newRoom;
	}
	
	public void printSenses() {
		System.out.println("You hear fluttering.");
	}
}
