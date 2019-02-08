package wumpus.elements;
import wumpus.main.*;

// RoomElement that gives player a life signs detector, which can detect life signs up to 2 rooms away
public class ScannerElement extends RoomElement {
	public void handle() {
		System.out.println("The light was from a screen. It appears to be a life signs scanner, picking up living things up to 2 rooms away.");
		WumpusGame.hasScanner = true;
		WumpusGame.map.getRoom(WumpusGame.currentRoomIndex).removeElement();
		WumpusGame.displayInv();
	}
	
	public void printSenses() {
		System.out.println("See a light from one of the rooms.");
	}
}
