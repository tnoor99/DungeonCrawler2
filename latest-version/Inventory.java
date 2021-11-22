import java.util.ArrayList;
import java.util.Scanner;
import ansi_terminal.*;
import java.io.PrintWriter;

/* This class will house an ArrayList of Items, as well as a series of methods for altering the state of this ArrayList. Other methods include
   a method to print the contents of the ArrayList in an easily readable format, and equip methods to equip specific item types.*/

class Inventory {

	private ArrayList<Item> items = new ArrayList<Item>();
	private int maxWeight;
	private Item equippedWeapon;
	private Item equippedArmor;
	private Scanner scnr = new Scanner(System.in);
	private Scanner enterScnr = new Scanner(System.in);
	private Scanner in = new Scanner(System.in);

	// Constructs an Inventory with a given weight limit.
	public Inventory(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public Inventory(Scanner in) {
		maxWeight = in.nextInt();
		in.nextLine();
		equippedWeapon = new Item(in);
		in.nextLine();
		equippedArmor = new Item(in);
		in.nextLine();
		while (in.hasNextLine()) {
			items.add(new Item(in));
		}
		in.close();
	}

	/* Checks if a given item being added would excede weight limit. If so, item will not be added and the method will return false.
	   Otherwise, the item will be added to the items ArrayList, and the method will return true. */
	public boolean add(Item item) {
		if (item.getWeight() > (this.maxWeight - this.totalWeight())) {
			return false;
		}
		items.add(item);
		return true;
	}

	// Calculates combined weight of every Item in items ArrayList and returns this sum
	public int totalWeight() {
		int sum = 0;
		for (Item item : items) {
			sum += item.getWeight();
		}
		return sum;
	}

	// Prints every Item in items ArrayList in order. If items is empty, will print a message informing the user that the inventory is empty.
	public void print() {
		if (items.size() == 0) {
			System.out.println("...There's nothing in your inventory...This is awkward...\r");
			return;
		}

		int count = 1;
		System.out.print("\t[NAME] | [WEIGHT] | [VALUE] | [STRENGTH]\n\r");
		for (Item item : items) {
			System.out.print(count + ".\t");
			System.out.print(item + "\n\r");
			count++;
		}
	}

	// Prompts the user for which Item they would like to drop, and removes the specified Item from items ArrayList.
	public void drop() {
		System.out.println("\nWhich item would you like to drop?\n");
		this.print();
		System.out.print("\n>");
		int delInt = scnr.nextInt() - 1;
		Item delItem = items.get(delInt);
		items.remove(delInt);
		System.out.println("\nSuccessfully dropped: " + delItem);
		System.out.print("\n*Hit enter to continue*\n>");
		enterScnr.nextLine();

	}

	/* Prints a list of every Item of ItemType Weapon. If there are no weapons, tells user such and returns. Otherwise prompts the user for
	   which Item they would like to equip, and sets eqquipedWeapon to the specified Item. */
	public void equipWeapon() {	
		ArrayList<Item> weapons = new ArrayList<Item>();
		for (Item i : items) {
			if (i.getType() == ItemType.Weapon) {
				weapons.add(i);
			}
		}

		if (weapons.size() == 0) {
			System.out.print("\nYou have no weapons in your inventory!\n\r");
			Terminal.getLine("Hit enter to continue... >");
			return;
		}
		
		int count = 1;
		System.out.print("\nWhich weapon would you like to equip?\n\n\r");
		for (Item i : weapons) {
			System.out.print(count + ".\t" + i + "\n\r");
			count++;
		}
	
		int eq = Integer.parseInt(Terminal.getLine("\n>"))-1;
		equippedWeapon = weapons.get(eq);
		System.out.print("\n\rYou have eqquiped: " + weapons.get(eq) + "\n\r");		
		Terminal.getLine("\nHit enter to continue... >");

	}

/* Prints a list of every Item of ItemType Armor. Prompts the user for which Item they would like to equip, and sets eqquipedArmor to
the specified Item */
	public void equipArmor() {
		ArrayList<Item> armors = new ArrayList<Item>();
		for (Item i : items) {
			if (i.getType() == ItemType.Armor) {
				armors.add(i);
			}
		}

		if (armors.size() == 0) {
			System.out.print("\nYou have no armor in your inventory!\n\r");
			Terminal.getLine("Hit enter to continue... >");
			return;
		}
		
		int count = 1;
		System.out.print("\nWhich armor would you like to equip?\n\n\r");
		for (Item i : armors) {
			System.out.print(count + ".\t" + i + "\n\r");
			count++;
		}

		int eq = Integer.parseInt(Terminal.getLine("\n>"))-1;
		equippedArmor = armors.get(eq);
		System.out.print("\nYou have eqquiped: " + armors.get(eq) + "\n\r");
		Terminal.getLine("\nHit enter to continue... >");

	}
// returns equippedWeapon
	public Item getEquippedWeapon() {
		return this.equippedWeapon;
	}
// returns equippedArmor
	public Item getEquippedArmor() {
		return this.equippedArmor;
	}
// sets equippedWeapon to given item
	public void setEquippedWeapon(Item i) {
		this.equippedWeapon = i;
	}
// sets equippedArmor to given item
	public void setEquippedArmor(Item i) {
		this.equippedArmor = i;
	}
// returns list of items in inventory
	public ArrayList<Item> getItems() {
		return this.items;
	}

	public void save(PrintWriter out) {
	
		out.println(maxWeight);
		equippedWeapon.save(out);
		equippedArmor.save(out);
		for (Item i : items) {
			i.save(out);
			
		}

	}

}
