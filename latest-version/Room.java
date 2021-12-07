import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class handles the components of a single room, including layout, enemies, and items.
 */

public class Room {

	private ArrayList<String> grid;
	private ArrayList<Enemy> enemies;
	private ArrayList<Item> groundItems;
/**
 * This method constructs one of three set types of rooms.
 *
 * @param type Int designating which type of room to construct. Needs to be either 1, 2, or 3.
 */
	public Room(int type) {
		this.grid = getGridType(type);
		this.enemies = getEnemiesType(type);
		this.groundItems = getGroundItemsType(type);
	}
/**
 * This method constructs a room based on data stored in a save file.
 *
 * @param type Int designating which type of room to construct. Needs to be either 1, 2, or 3.
 * @param in Scanner linked to the file storing the saved room's data.
 */
	public Room(int type, Scanner in) {
		this.grid = getGridType(type);
		this.enemies = new ArrayList<Enemy>();
		this.groundItems = new ArrayList<Item>(); 

		Boolean stillMore = true;
		String header = "";

		
		while (stillMore) {
			header = in.nextLine();
			if (header.equals("XXXXX")) {
				stillMore = false;
			} else {
				groundItems.add(new Item(in));
			}
		}
		stillMore = true;
		while (stillMore) {
			header = in.nextLine();
			if (header.equals("XXXXX")) {
				stillMore = false;
			} else {
				enemies.add(new Enemy(in));
			}
		}
	}
/**
 * This method saves the room's values to a file so that it can be loaded later on.
 *
 * @param out PrintWriter which is linked to the file where the room's values are to be stored.
 * @param currentRoom Int designating the room the player is currently in. Needs to be either 1, 2, or 3.
 */
	public void save(PrintWriter out, int currentRoom) {
		out.println(currentRoom);
		if (groundItems.size() > 0) {
			out.println(" ");
			for (int i = 0; i < groundItems.size(); i++) {
				if (i == groundItems.size()-1) {
					groundItems.get(i).save(out, true);
				} else {
					groundItems.get(i).save(out, false);
				}
			}
		} else { out.println("XXXXX"); }
		
		if (enemies.size() > 0) {
			out.println(" ");
			for (int e = 0; e < enemies.size(); e++) {
				if (e == enemies.size()-1) {
					enemies.get(e).save(out, true);
				} else {
					enemies.get(e).save(out, false);
				}
			}
		} else { out.println("XXXXX"); }
	    }
/**
 * This method returns the room's grid, which is an ArrayList of Strings storing the room's physical layout.
 *
 * @return Returns ArrayList<String> containing room's layout.
 */
	public ArrayList<String> getGrid() {
		return grid;
	}
/**
 * This method returns the list containing the room's enemies.
 *
 * @return Returns ArrayList<Enemy> containing all of the enemies in the room.
 */
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
/**
 * This method returns the list containing all the items on the ground in the room.
 *
 * @return Returns ArrayList<Item> containing the items currently on the ground in the room.
 */
	public ArrayList<Item> getGroundItems() {
		return groundItems;
	}
/**
 * This method checks if there are any living enemies remaining in the room.
 *
 * @return Returns boolean: true if all enemies in the room are dead, and false if not.
 */
	public boolean checkEnemiesDead() {
		boolean allDead = true;
		for (Enemy e : enemies) {
			if (e.getAlive()) { allDead = false; }
		}
		return allDead;
	}
/**
 * This method stores and returns one of three room layouts. This is used when constructing on of the three
 * default layouts.
 *
 * @param type Int designating desired room layout type. Needs to be either 1, 2, or 3.
 * @return Returns ArrayList<String> containing the layout of the specified room type.
 */
	public ArrayList<String> getGridType(int type) {
		ArrayList<String> grid = new ArrayList<String>();
		if (type == 1) {
			grid.add("%%%%%%%%%%                          ");
			grid.add("%        %                          ");
			grid.add("%        %                          ");
			grid.add("%        %                          ");
			grid.add("%        %                          ");
			grid.add("%        %                          ");
			grid.add("%        %                          ");
			grid.add("%      2 %                          ");
			grid.add("%        %                          ");
			grid.add("%%%%%%%%%%                          ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
		} else if (type == 2) {
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("     %%%%%%%%%%%%%%%%%%%%%%         ");
			grid.add("     %                    %         ");
			grid.add("     % 1               3  %         ");
			grid.add("     %                    %         ");
			grid.add("     %                    %         ");
			grid.add("     %                    %         ");
			grid.add("     %                    %         ");
			grid.add("     %                    %         ");
			grid.add("     %%%%%%%%%%%%%%%%%%%%%%         ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
		} else if (type == 3) {
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("          %%%%%%%%%%%%%%            ");
			grid.add("       %%%%            %%%%         ");
			grid.add("     %%%                  %%%       ");
			grid.add("    %%                      %%      ");
			grid.add("    %                  2     %      ");
			grid.add("    %%                      %%      ");
			grid.add("     %%%                  %%%       ");
			grid.add("       %%%%            %%%%         ");
			grid.add("          %%%%%%%%%%%%%%            ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
			grid.add("                                    ");
		}
		return grid;
	}
/**
 * This method stores and returns one of three default room enemy configurations.
 *
 * @param type Int designating the desired room enemy configuration. Needs to be either 1, 2, or 3.
 * @return Returns ArrayList<Enemy> contaning the enemies of the specified room type.
 */
	public ArrayList<Enemy> getEnemiesType(int type) {
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		if (type == 1) {
			enemies.add(new Enemy("Otmin Minotaur", 40, 10, 4, 4));
		} else if (type == 2) {
			enemies.add(new Enemy("Prince Caspian", 60, 20, 15, 10));
			//enemies.add(new Enemy("The White Witch", 150, 55, 15, 11));
		} else {
			enemies.add(new Enemy("The White Witch", 120, 35, 10, 7));
			//enemies.add(new Enemy("eType3", 50, 5, 10, 7));
		}
		return enemies;
	}
/**
 * This method stores and returns one of three default room item configurations.
 *
 * @param type Int designating the desired room item configuration. Needs to be either 1, 2, or 3.
 * @return Returns ArrayList<Item> containing the items of the specified room type.
 */
	public ArrayList<Item> getGroundItemsType(int type) {
		ArrayList<Item> groundItems = new ArrayList<Item>();
		if (type == 1) {
			groundItems.add(new Item(ItemType.Weapon, "Lucy's dagger", 10, 20, 15, 7, 2));
		} else if (type == 2) {
			groundItems.add(new Item(ItemType.Weapon, "Seven Swords", 20, 30, 40, 8, 11));
			groundItems.add(new Item(ItemType.Armor, "Chainmail Armor Set", 50, 30, 10, 23, 11));
			//groundItems.add(new Item(ItemType.Weapon, "Jadis' Wand", 45, 40, 80, 7, 12));
		} else {
			groundItems.add(new Item(ItemType.Weapon, "Jadis' Wand", 20, 40, 80, 6, 7));
			//groundItems.add(new Item(ItemType.Other, "iType3", 0, 0, 0, 17, 7));
		}
		return groundItems;
	}

}
