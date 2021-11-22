import java.util.ArrayList;

public class Room {

	private ArrayList<String> grid;
	private ArrayList<Enemy> enemies;
	private ArrayList<Item> groundItems;

	public Room(int type) {
		this.grid = getGridType(type);
		this.enemies = getEnemiesType(type);
		this.groundItems = getGroundItemsType(type);
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
			enemies.add(new Enemy("Type1", 50, 5, 4, 4));
		} else if (type == 2) {
			enemies.add(new Enemy("Type2", 50, 5, 15, 10));
		} else {
			enemies.add(new Enemy("Type3", 50, 5, 10, 7));
		}
		return enemies;
	}

	public ArrayList<Item> getGroundItemsType(int type) {
		ArrayList<Item> groundItems = new ArrayList<Item>();
		if (type == 1) {
			groundItems.add(new Item(ItemType.Other, "Type1", 0, 0, 0, 7, 2));
		} else if (type == 2) {
			groundItems.add(new Item(ItemType.Other, "Type2", 0, 0, 0, 7, 11));
		} else {
			groundItems.add(new Item(ItemType.Other, "Type3", 0, 0, 0, 17, 7));
		}
		return groundItems;
	}

}
