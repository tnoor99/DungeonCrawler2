import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class handles all of the rooms making up the game, as well as constructing, saving, and loading said rooms.
 */
public class Map {

	private ArrayList<Room> rooms;
	private int currentRoom;
/**
 * This method constructs a default set of rooms to make up the map.
 */
	public Map() {
		rooms = new ArrayList<Room>();
		rooms.add(new Room(1));
		rooms.add(new Room(2));
		rooms.add(new Room(3));
		currentRoom = 0;
	}
/**
 * This method constructs map based on room data stored in save files.
 *
 * @param inR1 Scanner connected to file containing data for the first room.
 * @param inR2 Scanner connected to file containing data for the second room.
 * @param inR3 Scanner connected to file containing data for the third room.
 */
	public Map(Scanner inR1, Scanner inR2, Scanner inR3) {
		rooms = new ArrayList<Room>();
		currentRoom = inR1.nextInt();
		inR1.nextLine();
		rooms.add(new Room(1, inR1));
		inR2.nextLine();
		rooms.add(new Room(2, inR2));
		inR3.nextLine();
		rooms.add(new Room(3, inR3));

	}
/**
 * This method directs a PrintWriter to a specified room to save the values of said room.
 *
 * @param out PrintWriter connected to the file the room is to be saved to.
 * @param r Int designating which of the three rooms is to be saved. Needs to be either 1, 2, or 3.
 */
	public void save(PrintWriter out, int r) {
		rooms.get(r-1).save(out, currentRoom);
	}
/**
 * This method returns one of the three rooms the map is storing.
 *
 * @param roomNum Int designating which of the three rooms is to be returned. Needs to be either 1, 2, or 3.
 * @return Returns the specified Room.
 */
	public Room getRoom(int roomNum) {
		return rooms.get(roomNum);
	}
/**
 * This method returns the room that the player is currently in.
 *
 * @return Retruns Room which the playes is currently in.
 */
	public Room getCurrentRoom() {
		return rooms.get(currentRoom);
	}
/**
 * This method updates the current room.
 *
 * @param r Int designating which room is now the current room. Must be either 1, 2, or 3.
 */
	public void setCurrentRoom(int r) {
		currentRoom = r;
	}
/**
 * This methods checks every enemy of every room to see if any are still alive.
 *
 * @return Returns boolean true if all enemies are dead, and false otherwise.
 */
	public boolean checkAllEnemiesDead() {
		boolean allDead = true;
		for (Room r : rooms) {
			if(!r.checkEnemiesDead()) { allDead = false; }
		}
		return allDead;
	}

}



// below is the old map class
/*
public class Map {
	
	private ArrayList<String> grid;
// creates the map
	public Map() {
		this.grid = new ArrayList<String>();
		this.grid.add("%%%%%%%%%%                          ");
		this.grid.add("%        %                          ");
		this.grid.add("%        %     %%%%%%%%%%%%%%%%%%%%%");
		this.grid.add("%        %     %                   %");
		this.grid.add("%        %     %                   %");
		this.grid.add("%        %     %                   %");
		this.grid.add("%        %%%%%%%                   %");
		this.grid.add("%                                  %");
		this.grid.add("%        %%%%%%%                   %");
		this.grid.add("%%%%%%%%%%     %                   %");
		this.grid.add("               %                   %");
		this.grid.add("               %                   %");
		this.grid.add("               %% %%%%%%%%%%%%%%%%%%");
		this.grid.add("                % %                 ");
		this.grid.add("            %%%%% %%%%%             ");
		this.grid.add("            %         %             ");
		this.grid.add("            %         %             ");
		this.grid.add("            %         %             ");
		this.grid.add("            %%%%%%%%%%%             ");
	}
// returns the ArrayList storing the map
	public ArrayList<String> getGrid() {
		return grid;
	}

}
*/
