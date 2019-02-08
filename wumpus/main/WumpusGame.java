package wumpus.main;
/* class WumpusGame -- main game class; provides main loop and some utilities.  */

import java.util.Scanner;
import wumpus.elements.*;

public class WumpusGame {

	// Setup basic variables
	public static Scanner scan = new Scanner(System.in);
	public static WumpusMap map = new WumpusMap();
	public static boolean gameActive = true;
	public static boolean hasScanner = false;
	public static int arrows = 5;
	public static boolean hasRepellent = false;
	
	public static int currentRoomIndex = 1;

	// Shoots an arrow in the given direction on command
	public static void shootArrow(String input) {
		int spacePos = input.indexOf(' ');
		if (spacePos == -1 || spacePos == input.length() - 1) {
			System.out.println("Huh?");
			return;
		}
		String direction = input.substring(spacePos + 1);
		int dirNum = WumpusMap.directionNumber(direction);
		if (dirNum == 0) {
			System.out.println("Huh?");   // bad shoot direction
		} else {
			WumpusRoom room = currentRoom();
			WumpusRoom targetRoom = room.roomInDirection(dirNum); 
			if (targetRoom != null) {
				arrows -= 1;
				if (targetRoom.getElement() != null && (targetRoom.getElement() instanceof WumpusElement)) {
					System.out.println("You shoot the wumpus.  Victory!!");
					gameActive = false;
				} else {
					System.out.println("You missed and scared the wumpus.");
					map.moveWumpus();
					System.out.println("You have " + arrows + " arrows left");
				}
			} else {
				System.out.println("Can't fire that way.");
			}
		}
	}
	
	// method to return the current room index
	public static WumpusRoom currentRoom() {
		return map.getRoom(currentRoomIndex);
	}
	
	// method to display what the player has in their inventory
	public static void displayInv() {
		System.out.println("Inventory:");
		System.out.println(arrows + " arrows");
		if (hasScanner) {
			System.out.println("Life signs detector");
		}
		if (hasRepellent) {
			System.out.println("Wumpus Repellent");
		}
	}
	
	public static void main(String[] args) {
		
		// Add all the elements to the rooms
		map.addElement(new WumpusElement());
		map.addElement(new PitElement());
		map.addElement(new PitElement());
		map.addElement(new BatsElement());
		map.addElement(new ScannerElement());
		map.addElement(new ArrowsElement());
		map.addElement(new ArrowsElement());
		map.addElement(new WumpusRepellentElement());
		
		// Puts the player in an empty room, and begins the game
		currentRoomIndex = map.randomEmptyRoom();
		System.out.println("Welcome to Hunt the Wumpus. Good luck!");
		
		// until the game is over
		do  {
			// Print all the info for the current room
			map.getRoom(currentRoomIndex).printInfo();
			
			// Handle user input
			String userInput = scan.nextLine();
			int direction = 0;
			
			// checks for the shoot command
			if (userInput.startsWith("shoot")) {
				shootArrow(userInput);
			} else if ((direction = WumpusMap.directionNumber(userInput)) != 0) {
				// Move to the room in the direction commanded
				WumpusRoom nowRoom = map.getRoom(currentRoomIndex);
				WumpusRoom targetRoom = nowRoom.roomInDirection(direction);
				if (targetRoom != null) {
					currentRoomIndex = targetRoom.getIndex();
					targetRoom.handleElement();
				} else {
					// Improper format/invalid room
					System.out.println("You can't move in that direction.");
				}
			} else if (userInput.equals("bye")) {
				// end the game
				gameActive = false;
			} else if (userInput.equals("i") || userInput.equals("I") || userInput.equals("inv") || userInput.equals("inventory")) {
				// display inventory
				displayInv();
			} else {
				// wrong format
				System.out.println("Command not understood.");
			}
		} while (gameActive);
		System.out.println("GAME OVER");
	}

}
