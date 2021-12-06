import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.Scanner;

public class Room {

	private ArrayList<String> grid;
	private ArrayList<Enemy> enemies;
	private ArrayList<Item> groundItems;

	public Room(int type) {
		this.grid = getGridType(type);
		this.enemies = getEnemiesType(type);
		this.groundItems = getGroundItemsType(type);
	}

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

	public ArrayList<String> getGrid() {
		return grid;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public ArrayList<Item> getGroundItems() {
		return groundItems;
	}

	public boolean checkEnemiesDead() {
		boolean allDead = true;
		for (Enemy e : enemies) {
			if (e.getAlive()) { allDead = false; }
		}
		return allDead;
	}

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
