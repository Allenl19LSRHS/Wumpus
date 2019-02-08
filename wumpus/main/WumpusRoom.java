package wumpus.main;
import wumpus.elements.*;

/* WumpusRoom -- This class represents a single room on the map; a room corresponds to one vertex of a dodecahedron.
 * The class helps in navigating the map and handling encounters when the player moves. 
 */
public class WumpusRoom {

		private int myIndex;		//  the rooms are numbered 1-20; the index refers to that number
		private int[] neighbors;	// index values for neighboring rooms in order: N, E, S, W
		
		private RoomElement myElement;
		private boolean visited = false;
		
		
		WumpusRoom(int ndx, int n, int e, int s, int w) {
			myIndex = ndx;
			neighbors = new int[WumpusMap.N_DIRECTIONS + 1];
			neighbors[WumpusMap.NORTH] = n;
			neighbors[WumpusMap.EAST] = e;
			neighbors[WumpusMap.SOUTH] = s;
			neighbors[WumpusMap.WEST] = w; 
		}
		
		// returns the index of the room
		int getIndex() {
			return myIndex;
		}
		
		// returns the element in the room
		RoomElement getElement() {
			return myElement;
		}
		
		// sets the room element type
		void setElement(RoomElement elem) {
			myElement = elem;
		}
		
		// returns the room in the given direction from this room
		WumpusRoom roomInDirection(int dir) {
			return WumpusGame.map.getRoom(neighbors[dir]);
		}
		
		// prints all the info about the room (elements etc)
		void printInfo() {
			// basic info, plus the potential directions to go
			System.out.print("You are in room " + String.valueOf(myIndex) +". Exits:");
			for (int i = 1; i <= WumpusMap.N_DIRECTIONS; i++) {
				WumpusRoom room = roomInDirection(i);
				if (room != null) {
					System.out.print(" " + WumpusMap.directionName(i));
				}
			}
			System.out.println();
			
			// if the player visited the room before
			if (visited) {
				System.out.println("You feel like you've here before...");
			}
			
			// prints out the element info for the surrounding rooms
			for (int i = 1; i <= WumpusMap.N_DIRECTIONS; i++) {
				WumpusRoom room = roomInDirection(i);
				if (room != null && room.myElement != null) {
					room.myElement.printSenses();
				} else if (room != null) {
					
					// lots of complicated stuff to try to detect life signs in the surrounding rooms up to 2 away
					if (WumpusGame.hasScanner) { 
						
						if (room.myElement instanceof WumpusElement || room.myElement instanceof BatsElement) {
							System.out.println("Life signs detected!");
						}
						
						for (int q = 1; q <= WumpusMap.N_DIRECTIONS; q++) {
							WumpusRoom secRoom = room.roomInDirection(q);
							if (secRoom != null && secRoom.myElement != null) {
								if (secRoom.myElement instanceof WumpusElement || secRoom.myElement instanceof BatsElement) {
									System.out.println("Life signs detected!");
								}
							}
						}
					}
				}
			}
			visited = true;
			System.out.println();
		}
		
		// runs the element's handle code
		void handleElement() {
			if (myElement != null) {
				myElement.handle();
			}
		}
		
		// checks if the room has been visited
		boolean getVisited() {
			return visited;
		}
		
		// removes the element from the room
		public void removeElement() {
			myElement = null;
		}
}
