package wumpus.elements;

import wumpus.main.WumpusGame;


// RoomElement that is a chest with 3 extra arrows
public class ArrowsElement extends RoomElement {
	public void handle() {
		System.out.println("You find a chest with 3 more arrows!");
		WumpusGame.arrows += 3;
		WumpusGame.map.getRoom(WumpusGame.currentRoomIndex).removeElement();
		WumpusGame.displayInv();
	}
	
	public void printSenses() {
	}
}
