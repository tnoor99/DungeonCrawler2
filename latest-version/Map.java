import java.util.ArrayList;
import java.io.PrintWriter;

// this class store the map that the player, enemies, and items will be located on

// new map class.
public class Map {

	private ArrayList<Room> rooms;
	private int currentRoom;

	public Map() {
		rooms = new ArrayList<Room>();
		rooms.add(new Room(1));
		rooms.add(new Room(2));
		rooms.add(new Room(3));
		currentRoom = 0;
	}
	
	public void save(PrintWriter out, int r) {
		rooms.get(r-1).save(out, currentRoom);
	}

	public Room getRoom(int roomNum) {
		return rooms.get(roomNum);
	}
	
	public Room getCurrentRoom() {
		return rooms.get(currentRoom);
	}

	public void setCurrentRoom(int r) {
		currentRoom = r;
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
