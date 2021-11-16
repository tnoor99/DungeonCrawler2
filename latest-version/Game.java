import ansi_terminal.*;
import java.util.ArrayList;
import java.util.Random;

/* This class handles the major functions of the game, such as creating entities and updating their locations,
handling player input, printing the map, printing commands. */

public class Game {

	private Map theMap;
	private Player p;
	private Enemy e1;
	private Enemy e2;
	//private Item groundItem1;
	private ArrayList<Item> groundItems;

// instantiates all entities that will be present in the game
	public Game(String name) {
		theMap = new Map();
		p = new Player(name);
		p.setLocX(3);
		p.setLocY(3);
		e1 = new Enemy("Katniss", 100, 10, 4, 7);
		e2 = new Enemy("President Snow", 200, 15, 25, 7);
		//groundItem1 = new Item(ItemType.Other, "Test", 0, 0, 0, 7, 7);
		groundItems = new ArrayList<Item>();
		groundItems.add(new Item(ItemType.Weapon, "Rock", 10, 1, 2, 7, 7));
		groundItems.add(new Item(ItemType.Armor, "Steel Helmet", 7, 5, 9, 20, 10));
		groundItems.add(new Item(ItemType.Other, "Mockingjay Pin", 1, 20, 0, 17, 16));
		p.getInv().add(new Item(ItemType.Weapon, "Bow", 5, 5, 10, -1, -1));
		p.getInv().add(new Item(ItemType.Armor, "Leather Chestplate", 3, 5, 3, -1, -1));
		p.getInv().setEquippedWeapon(p.getInv().getItems().get(0));
		p.getInv().setEquippedArmor(p.getInv().getItems().get(1));


	}

/* startGame is where the game loop is, which prints the current world along with it's entities, waits for player input,
and then updates the world and entities accordingly */
	public void startGame() {

		boolean running = true;
		int move = 0;
		printCommands();
		while (running) {
			moveEnemy(e1);
			moveEnemy(e2);
			if (p.getLocX() == e1.getLocX() && p.getLocY() == e1.getLocY()) { // if p and e1 on same spot
				Battle b = new Battle();
				b.startBattle(p, e1);
				continue;
			} else if (p.getLocX() == e2.getLocX() && p.getLocY() == e2.getLocY()) { // if p and e2 on same spot
				Battle b = new Battle();
				b.startBattle(p, e2);
				continue;
			}

			this.printWorld();

			Key key = Terminal.getKey();
			switch (key) {
				case ESCAPE: // quit game
					System.out.println("\n\rThanks for playing!\r");
					Terminal.cookedMode();
					System.exit(1);
				case UP: // move up
					move = p.getLocY();
					if (Character.toString(theMap.getGrid().get(move-1).charAt(p.getLocX())).equals("%")) break; // blocks walking through wall
					if (p.getLocX() == e1.getLocX() && (move-1) == e1.getLocY()) { // if walk into e1
						//start battle with e1
						Battle b = new Battle();
						b.startBattle(p, e1);
						break;
					}
					if (p.getLocX() == e2.getLocX() && (move-1) == e2.getLocY()) { // if walk into e2
						//start battle with e2
						Battle b = new Battle();
						b.startBattle(p, e2);
						break;
					}
					p.setLocY(move-1);
					break;
				case DOWN: // move down
					move = p.getLocY();
					if (Character.toString(theMap.getGrid().get(move+1).charAt(p.getLocX())).equals("%")) break; // blocks walking through wall
					if (p.getLocX() == e1.getLocX() && (move+1) == e1.getLocY()) { // if walk into e1
						//start battle with e1
						Battle b = new Battle();
						b.startBattle(p, e1);
						break;
					}
					if (p.getLocX() == e2.getLocX() && (move+1) == e2.getLocY()) { // if walk into e2
						//start battle with e2
						Battle b = new Battle();
						b.startBattle(p, e2);
						break;
					}
	
					p.setLocY(move+1);
					break;
				case LEFT: // move left
					move = p.getLocX();
					if (Character.toString(theMap.getGrid().get(p.getLocY()).charAt(move-1)).equals("%")) break; // blocks walking through wall
					if ((move-1) == e1.getLocX() && p.getLocY() == e1.getLocY()) { // if walk into e1
						//start battle with e1
						Battle b = new Battle();
						b.startBattle(p, e1);
						break;
					}
					if ((move-1) == e2.getLocX() && p.getLocY() == e2.getLocY()) { // if walk into e2
						//start battle with e2
						Battle b = new Battle();
						b.startBattle(p, e2);
						break;
					}
	
					p.setLocX(move-1);
					break;
				case RIGHT: // move right
					move = p.getLocX();
					if ((move+1) == e1.getLocX() && p.getLocY() == e1.getLocY()) { // if walk into e1
						//start battle with e1
						Battle b = new Battle();
						b.startBattle(p, e1);
						break;
					}
					if ((move+1) == e2.getLocX() && p.getLocY() == e2.getLocY()) { // if walk into e2
						//start battle with e2
						Battle b = new Battle();
						b.startBattle(p, e2);
						break;
					}
	
					if (Character.toString(theMap.getGrid().get(p.getLocY()).charAt(move+1)).equals("%")) break; // blocks walking through wall
					p.setLocX(move+1);
					break;
				case FORWARDSLASH: // view commands
					this.printCommands();
					break;
				case p: // inspect weapon below and pick it up if player wants
					String pickUp = "";
					int count = -1;
					for (Item i : groundItems) {
						count++;
						if (p.getLocX() == i.getLocX() && p.getLocY() == i.getLocY()) {
							Terminal.clear();
							System.out.print("You inspect an item at your feet...\n\n\r");
							System.out.print(i + "\n\n\r");
							pickUp = Terminal.getLine("Do you want to pick it up? (y/n) >");
							if (pickUp.equals("y") || pickUp.equals("Y")) {
								if (p.getInv().add(i)) {
									System.out.print("\n\n\rYou picked up the item.");
									groundItems.remove(count);
									break;
								} else {
									System.out.print("\n\n\rThe item was too heavy to pick up.");
									break;
								}
							}
						}
					}
					break;
				case i: // view inventory
					Terminal.clear();
					p.getInv().print();
					Terminal.getLine("\nHit enter to continue... >");
					break;
				case c: // change equipped weapon or armor
					String choice = Terminal.getLine("Would you like to equip a new weapon (1) or armor (2)? >");
					if (choice.equals("1")) {
						p.getInv().equipWeapon();

					} else if (choice.equals("2")) {
						p.getInv().equipArmor();
					}
					break;

			}
			
		}

		Terminal.cookedMode();
	}

// prints the game's map along with entities that are currently on it
	public void printWorld() {
		Terminal.clear();
		char pixel;
		boolean printGround;
		for (int y = 0; y < theMap.getGrid().size(); y++) {
			for (int x = 0; x < theMap.getGrid().get(y).length(); x++) {
				printGround = true;
				if (x == p.getLocX() && y == p.getLocY()) { // print player
					System.out.print("@");
					printGround = false;
				} else if ((x == e1.getLocX() && y == e1.getLocY()) || (x == e2.getLocX() && y == e2.getLocY())) { // print enemies
					System.out.print("X");
					printGround = false;
				} else if (groundItems.size() > 0) { // print items
					for (Item i : groundItems) {
						if (x == i.getLocX() && y == i.getLocY()) {
							System.out.print("?");
							printGround = false;
						}
					}
				}
				if (printGround) { // print map
					pixel = theMap.getGrid().get(y).charAt(x);
					System.out.print(pixel);
				}
			}
			System.out.print("\n\r");
		}
		
		System.out.println("Coordinates: (" + p.getLocX() + ", " + p.getLocY() + ")"); // print player's current coordinates
	
	}

// prints the commands the player has to choose from
	public void printCommands() {
		Terminal.clear();
		System.out.print("***COMMANDS***\n\n\r");
		System.out.print("\\ : show commands\n\r");
		System.out.print("UP ARROW : move up\n\r");
		System.out.print("DOWN ARROW : move down\n\r");
		System.out.print("LEFT ARROW : move left\n\r");
		System.out.print("RIGHT ARROW : move right\n\r");
		System.out.print("I : view inventory\n\r");
		System.out.print("P : inspect an item beneath you\n\r");
		System.out.print("ESC : quit game\n\r");
		System.out.print("C : change equipped weapon or armor\n\n\r");

		Terminal.getLine("Hit enter to continue... >");
	}

// updates the positions of all living enemies - enemy movement is determined by a random number generator
	public void moveEnemy(Enemy e) {
		Random rand = new Random();
		int dir = rand.nextInt(4);
		int move;
		if (e.getAlive()) {
			if (dir == 0) {
				move = e.getLocY()-1;
				if (!(Character.toString(theMap.getGrid().get(move).charAt(e.getLocX())).equals("%"))) { // blocks walking through wall
					e.setLocY(move);
				}
			} else if (dir == 1) {
				move = e.getLocY()+1;
				if (!(Character.toString(theMap.getGrid().get(move).charAt(e.getLocX())).equals("%"))) { // blocks walking through wall
					e.setLocY(move);
				}
			} else if (dir == 2) {
				move = e.getLocX()-1;
				if (!(Character.toString(theMap.getGrid().get(e.getLocY()).charAt(move)).equals("%"))) { // blocks walking through wall
					e.setLocX(move);
				}
			} else {
				move = e.getLocX()+1;
				if (!(Character.toString(theMap.getGrid().get(e.getLocY()).charAt(move)).equals("%"))) { // blocks walking through wall
					e.setLocX(move);
				}
			}
		}
	}

}
