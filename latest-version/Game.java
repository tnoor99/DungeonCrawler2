import ansi_terminal.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class handles the major functions of the game, such as printing game commands, printing the current world, moving/updating entities, processing player input,
 * starting battles, and directing game save and load processes.
 */
public class Game {

	private Map theMap;
	private Player p;
	//private Enemy e1;
	//private Enemy e2;
	//private Item groundItem1;

// instantiates all entities that will be present in the game
/**
 * This method instantiates all the objects needed to start the game.
 * 
 * @param name String designating the player's choice of name.
 */
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
		p.getInv().add(new Item(ItemType.Weapon, "Bow", 10, 5, 10, -1, -1));
		p.getInv().add(new Item(ItemType.Armor, "Leather Armor Set", 20, 5, 3, -1, -1));
		p.getInv().setEquippedWeapon(p.getInv().getItems().get(0));
		p.getInv().setEquippedArmor(p.getInv().getItems().get(1));
	}


/**
 * This method prints game commands and then begins a loop which runs the game. Each iteration of the loop will prompt the player for input
 * and update the world and its entities accordingly. It will also start battles or end the game if conditions for such are met.
 */
	public void startGame() {

		boolean running = true;
		int move = 0;
		printCommands();
		boolean loading = false;
		while (running) {
			if (theMap.checkAllEnemiesDead()) {
				Terminal.clear();
				System.out.println("Congratulations, you've killed every enemy and beaten the game!\r");
				System.out.println("Thanks for playing!\n\r");
				Terminal.cookedMode();
				System.exit(1);
			}
			
			if (!loading) {
				moveEnemies(theMap.getCurrentRoom().getEnemies());
			} else { loading = false; }

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
									System.out.println("\n\rYou picked up the item.");
									Terminal.getLine("\n\rHit enter to continue... >");
									theMap.getCurrentRoom().getGroundItems().remove(count);
									break;
								} else {
									System.out.println("\n\rThe item was too heavy to pick up.\n\rTry dropping some items first.");
									Terminal.getLine("\n\rHit enter to continue... >");
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
					Terminal.clear();
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
					loading = true;
					break;

				case w: // warp to different room if standing on portal
					if (Character.toString(theMap.getCurrentRoom().getGrid().get(p.getLocY()).charAt(p.getLocX())).equals("1")) {
						theMap.setCurrentRoom(0);
						loading = true;
					} else if (Character.toString(theMap.getCurrentRoom().getGrid().get(p.getLocY()).charAt(p.getLocX())).equals("2")) {
						theMap.setCurrentRoom(1);
						loading = true;
					} else if (Character.toString(theMap.getCurrentRoom().getGrid().get(p.getLocY()).charAt(p.getLocX())).equals("3")) {
						theMap.setCurrentRoom(2);
						loading = true;
					}
					break;
				
				case d: // drop item (only if item is not equipped)
					Terminal.clear();
					p.getInv().print();
					String dropChoiceString = Terminal.getLine("Which item would you like to drop? >");
					int dropChoice = Integer.parseInt(dropChoiceString);
					System.out.println("\r");
					if ((p.getInv().getItems().get(dropChoice-1).getName()).equals(p.getInv().getEquippedWeapon().getName())) {
						System.out.println("You can't drop this weapon... You have it equipped!\n\rYou must find and equip another weapon first.\n\r");
						Terminal.getLine("Hit enter to continue... >");
						break;
					} else if ((p.getInv().getItems().get(dropChoice-1).getName()).equals(p.getInv().getEquippedArmor().getName())) {
						System.out.println("You can't drop this armor... You have it equipped!\n\rYou must find and equip another armor first.\n\r");
						Terminal.getLine("Hit enter to continue... >");
						break;
					} 
					
					Boolean breakOut = false;
					for (Item i : theMap.getCurrentRoom().getGroundItems()) {
						if (p.getLocX() == i.getLocX() && p.getLocY() == i.getLocY()) {
							System.out.println("You can't drop an item here... You're already standing on top of one!\n\r");
							Terminal.getLine("Hit enter to continue... >");
							breakOut = true;
						}
					}
					if (breakOut) { break; }

					p.getInv().getItems().get(dropChoice-1).setLocX(p.getLocX());
					p.getInv().getItems().get(dropChoice-1).setLocY(p.getLocY());
					theMap.getCurrentRoom().getGroundItems().add(p.getInv().getItems().get(dropChoice-1));
					p.getInv().getItems().remove(dropChoice-1);
					
					Terminal.getLine("Hit enter to continue... >");
					break;

			}
			
		}

		Terminal.cookedMode();
	}

/**
 * This method prints the current room along with the player and any enemies, items, or warp points in the room.
 */
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

/**
 * This method prints the commands the player has to choose from.
 */
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
		System.out.print("D : drop an item\n\r");
		System.out.print("C : change equipped weapon or armor\n\r");
		System.out.print("W : take the warp you are standing on\n\r");
		System.out.print("S : save the game\n\r");
		System.out.print("L : load the save file\n\r");
		System.out.print("ESC : quit game\n\n\r");

		Terminal.getLine("Hit enter to continue... >");
	}

/**
 * This method updates the positions of all living enemies in the current room. Enemy movements are random.
 *
 * @param enemies ArrayList<Enemy> containing list of enemies that are to be moved.
 */
	public void moveEnemies(ArrayList<Enemy> enemies) {
		Random rand = new Random();
		int move;
		for (Enemy e : enemies) {
			int dir = rand.nextInt(4);
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



/**
 * This method directs the game's save process, writing relevant data to a series of files which are read when a saved game is loaded.
 */
	public void saveGame() {
		PrintWriter outpf = null;
		PrintWriter outr1 = null;
		PrintWriter outr2 = null;
		PrintWriter outr3 = null;

		try {
			File pf = new File("player.txt");
			File r1 = new File("room1.txt");
			File r2 = new File("room2.txt");
			File r3 = new File("room3.txt");

			outpf = new PrintWriter(pf);
			outr1 = new PrintWriter(r1);
			outr2 = new PrintWriter(r2);
			outr3 = new PrintWriter(r3);

		} catch (FileNotFoundException e) {
			System.out.println("File not found. ");	
		}
		
		p.save(outpf);
		outpf.close();

		theMap.save(outr1, 1);
		outr1.close();
		theMap.save(outr2, 2);
		outr2.close();
		theMap.save(outr3, 3);
		outr3.close();

	}

/**
 * This method directs the game's loading process, reading from a series of files in order to reconstruct a saved game.
 */
	public void loadGame() {
		Scanner inP = null;
		Scanner inR1 = null;
		Scanner inR2 = null;
		Scanner inR3 = null;

		try {
			File pf = new File("player.txt");
			File r1 = new File("room1.txt");
			File r2 = new File("room2.txt");
			File r3 = new File("room3.txt");
		

			inP = new Scanner(pf);
			inR1 = new Scanner(r1);
			inR2 = new Scanner(r2);
			inR3 = new Scanner(r3);

		} catch (FileNotFoundException e) {
			System.out.println("File not found. ");
		}
		
		theMap = new Map(inR1, inR2, inR3);

		p = new Player(inP);
	

	}




/**
 * This method checks whether a battle is to be started with an enemy.
 * 
 * @param enemies ArrayList<Enemy> containing the enemies of the current room.
 * @param locX Int designating the player's x coordinate on the map.
 * @param locY Int designating the player's y coordinate on the map.
 * @return Returns boolean true if a battle is to be started (if player and enemy are at same location), and false otherwise.
 */
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
/**
 * This method checks whether there is an enemy standing at a given location.
 *
 * @param enemies ArrayList<Enemy> containing the enemies that are to be checked.
 * @param locX Int designating the x coordinate of the location being checked.
 * @param locY Int designating the y coordinate of the location being checked.
 * @return Returns boolean true if there is an enemy standing at the given location, and false otherwise.
 */
	public Boolean isEnemyHere(ArrayList<Enemy> enemies, int locX, int locY) {
		for (Enemy e : enemies) {
			if (locX == e.getLocX() && locY == e.getLocY()) {
				return true;
			}
		}
		return false;
	}


}
