import ansi_terminal.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* This class handles the major functions of the game, such as creating entities and updating their locations,
handling player input, printing the map, printing commands. */

public class Game {

	private Map theMap;
	private Player p;
	//private Enemy e1;
	//private Enemy e2;
	//private Item groundItem1;

// instantiates all entities that will be present in the game
	public Game(String name) {
		theMap = new Map();
		p = new Player(name);
		p.setLocX(1);
		p.setLocY(1);
		//e1 = new Enemy("Katniss", 100, 10, 6, 5);
		//e2 = new Enemy("President Snow", 200, 15, 2, 7);
		//e1.setAlive(false);
		//e2.setAlive(false);
		//groundItem1 = new Item(ItemType.Other, "Test", 0, 0, 0, 7, 7);
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
			moveEnemies(theMap.getCurrentRoom().getEnemies());

			battleChecker(theMap.getCurrentRoom().getEnemies(), p.getLocX(), p.getLocY());

			this.printWorld();

			Key key = Terminal.getKey();
			switch (key) {
				case ESCAPE: // quit game
					System.out.println("\n\rThanks for playing!\r");
					Terminal.cookedMode();
					System.exit(1);
				case UP: // move up
					move = p.getLocY() - 1;
					if (Character.toString(theMap.getCurrentRoom().getGrid().get(move).charAt(p.getLocX())).equals("%")) break; // blocks walking through wall
					if (battleChecker(theMap.getCurrentRoom().getEnemies(), p.getLocX(), move)) {
						break;
					}
					p.setLocY(move);
					break;
				case DOWN: // move down
					move = p.getLocY() + 1;
					if (Character.toString(theMap.getCurrentRoom().getGrid().get(move).charAt(p.getLocX())).equals("%")) break; // blocks walking through wall
					if (battleChecker(theMap.getCurrentRoom().getEnemies(), p.getLocX(), move)) {
						break;
					}
					p.setLocY(move);
					break;
				case LEFT: // move left
					move = p.getLocX() - 1;
					if (Character.toString(theMap.getCurrentRoom().getGrid().get(p.getLocY()).charAt(move)).equals("%")) break; // blocks walking through wall	
					if (battleChecker(theMap.getCurrentRoom().getEnemies(), move, p.getLocY())) {
						break;
					}
					p.setLocX(move);
					break;
				case RIGHT: // move right
					move = p.getLocX() + 1;	
					if (Character.toString(theMap.getCurrentRoom().getGrid().get(p.getLocY()).charAt(move)).equals("%")) break; // blocks walking through wall
					if (battleChecker(theMap.getCurrentRoom().getEnemies(), move, p.getLocY())) {
						break;
					}
					p.setLocX(move);
					break;
				case FORWARDSLASH: // view commands
					this.printCommands();
					break;
				case p: // inspect weapon below and pick it up if player wants
					String pickUp = "";
					int count = -1;
					for (Item i : theMap.getCurrentRoom().getGroundItems()) {
						count++;
						if (p.getLocX() == i.getLocX() && p.getLocY() == i.getLocY()) {
							Terminal.clear();
							System.out.print("You inspect an item at your feet...\n\n\r");
							System.out.print(i + "\n\n\r");
							pickUp = Terminal.getLine("Do you want to pick it up? (y/n) >");
							if (pickUp.equals("y") || pickUp.equals("Y")) {
								if (p.getInv().add(i)) {
									System.out.print("\n\n\rYou picked up the item.");
									theMap.getCurrentRoom().getGroundItems().remove(count);
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
				case s: // save game
					saveGame();
					break;
				case l: // load game
					loadGame();
					break;

				case w:
					if (Character.toString(theMap.getCurrentRoom().getGrid().get(p.getLocY()).charAt(p.getLocX())).equals("1")) {
						theMap.setCurrentRoom(0);
					} else if (Character.toString(theMap.getCurrentRoom().getGrid().get(p.getLocY()).charAt(p.getLocX())).equals("2")) {
						theMap.setCurrentRoom(1);
					} else if (Character.toString(theMap.getCurrentRoom().getGrid().get(p.getLocY()).charAt(p.getLocX())).equals("3")) {
						theMap.setCurrentRoom(2);
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
		for (int y = 0; y < theMap.getCurrentRoom().getGrid().size(); y++) {
			for (int x = 0; x < theMap.getCurrentRoom().getGrid().get(y).length(); x++) {
				printGround = true;
				if (x == p.getLocX() && y == p.getLocY()) { // print player
					System.out.print("@");
					printGround = false;
				} else if (isEnemyHere(theMap.getCurrentRoom().getEnemies(), x, y)) { // print enemies
					System.out.print("X");
					printGround = false;
				} else if (theMap.getCurrentRoom().getGroundItems().size() > 0) { // print items
					for (Item i : theMap.getCurrentRoom().getGroundItems()) {
						if (x == i.getLocX() && y == i.getLocY()) {
							System.out.print("?");
							printGround = false;
						}
					}
				}
				if (printGround) { // print map
					pixel = theMap.getCurrentRoom().getGrid().get(y).charAt(x);
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
		System.out.print("S : save the game\n\n\r");
		System.out.print("L : load the game\n\n\r");

		Terminal.getLine("Hit enter to continue... >");
	}

// updates the positions of all living enemies - enemy movement is determined by a random number generator
	public void moveEnemies(ArrayList<Enemy> enemies) {
		Random rand = new Random();
		int dir = rand.nextInt(4);
		int move;
		for (Enemy e : enemies) {
			if (e.getAlive()) {
				if (dir == 0) {
					move = e.getLocY()-1;
					if (!(Character.toString(theMap.getCurrentRoom().getGrid().get(move).charAt(e.getLocX())).equals("%"))) { // blocks walking through wall
						e.setLocY(move);
					}
				} else if (dir == 1) {
					move = e.getLocY()+1;
					if (!(Character.toString(theMap.getCurrentRoom().getGrid().get(move).charAt(e.getLocX())).equals("%"))) { // blocks walking through wall
						e.setLocY(move);
					}
				} else if (dir == 2) {
					move = e.getLocX()-1;
					if (!(Character.toString(theMap.getCurrentRoom().getGrid().get(e.getLocY()).charAt(move)).equals("%"))) { // blocks walking through wall
						e.setLocX(move);
					}
				} else {
					move = e.getLocX()+1;
					if (!(Character.toString(theMap.getCurrentRoom().getGrid().get(e.getLocY()).charAt(move)).equals("%"))) { // blocks walking through wall
						e.setLocX(move);
					}
				}
			}
		}
	}




	public void saveGame() {
		PrintWriter out = null;
		PrintWriter outpf = null;
		PrintWriter outr1 = null;
		PrintWriter outr2 = null;
		PrintWriter outr3 = null;

	try {
		File i = new File("inventory.txt");
		File pf = new File("player.txt");
		File r1 = new File("room1.txt");
		File r2 = new File("room2.txt");
		File r3 = new File("room3.txt");

		out = new PrintWriter(i);
		outpf = new PrintWriter(pf);
		outr1 = new PrintWriter(r1);
		outr2 = new PrintWriter(r2);
		outr3 = new PrintWriter(r3);

	} catch (FileNotFoundException e) {
		System.out.println("File not found. ");	
	}

	p.getInv().save(out);
	out.close();

		
	p.save(outpf);
	outpf.close();

	//theMap.save(outr1, 1);
	outr1.close();
	//theMap.save(outr2, 2);
	outr2.close();
	//theMap.save(outr3, 3);
	outr3.close();

	}


	public void loadGame() {
		Scanner in = null;

	try { 
		File f = new File("save.txt");
		in = new Scanner(f);
	} catch (FileNotFoundException e) {
		System.out.println("File not found. ");
	}
	//Player p = new Player(in);
	//Player p.getInv() = new Player p.getInv(in);


	}





	public Boolean battleChecker(ArrayList<Enemy> enemies, int locX, int locY) {
		Battle b = new Battle();
		for (Enemy e : enemies) {
			if (locX == e.getLocX() && locY == e.getLocY()) {
				b.startBattle(p, e);
				return true;
			}
		}
		return false;
	}

	public Boolean isEnemyHere(ArrayList<Enemy> enemies, int locX, int locY) {
		for (Enemy e : enemies) {
			if (locX == e.getLocX() && locY == e.getLocY()) {
				return true;
			}
		}
		return false;
	}


}
