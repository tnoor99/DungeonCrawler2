import java.util.ArrayList;
import java.util.Scanner;
import ansi_terminal.*;
import java.io.PrintWriter;

/**
 * This method handles the players inventory, storing values and objects, and modifying these values and objects when prompted. It includes a
 * list of items that the inventory is holding, a max weight that can be carried, and the items currently equipped.
 */
class Inventory {

	private ArrayList<Item> items = new ArrayList<Item>();
	private int maxWeight;
	private Item equippedWeapon;
	private Item equippedArmor;
	private Scanner scnr = new Scanner(System.in);
	private Scanner enterScnr = new Scanner(System.in);
	private Scanner in = new Scanner(System.in);

/**
 * This method constructs an Inventory with a given weight limit.
 *
 * @param maxWeigth Int designating the max weight limit of the inventory.
 */
	public Inventory(int maxWeight) {
		this.maxWeight = maxWeight;
	}

/**
 * This method constructs an Inventory according to the values and items stored in a provided save file.
 *
 * @param in Scanner which is connected to the file storing the saved inventory.
 */
	public Inventory(Scanner in) {
		maxWeight = in.nextInt();
		in.nextLine();
		equippedWeapon = new Item(in);
		in.nextLine();
		equippedArmor = new Item(in);
		in.nextLine();
		
		Boolean stillRunning = true;
		String header = "";
		while (stillRunning) {
			header = in.nextLine();
			if (header.equals("XXXXX")) {
				break;
			} else {
				items.add(new Item(in));
			}
		}
		in.close();
	}

/**
 * This method adds a provided item to the inventory. However, if adding the item would cause the total weight of the inventory to exceed
 * the max weight of the inventory, the item will not be added.
 *
 * @param item Item that is to be added.
 * @return Returns boolean true if the item was successfully added, and false otherwise.
 */
	public boolean add(Item item) {
		if (item.getWeight() > (this.maxWeight - this.totalWeight())) {
			return false;
		}
		items.add(item);
		return true;
	}

/**
 * This method calculates the combined weight of every item in the inventory.
 *
 * @return Returns int designating the total weight of the inventory.
 */
	public int totalWeight() {
		int sum = 0;
		for (Item item : items) {
			sum += item.getWeight();
		}
		return sum;
	}

/**
 * This method prints the inventory's contents in an easily readable format. If the inventory is empty, a message will be printed saying so.
 */
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

/**
 * This displays the inventory's contents and prompts the user to select an item to drop, and removes said item from the inventory.
 */
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

/**
 * This method prints a list of every weapon in the inventory. It then asks prompts the user to select a weapon to equip, and sets EquippedWeapon to the selected weapon.
 */
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
	
		int eq = Integer.parseInt(Terminal.getLine(">"))-1;
		equippedWeapon = weapons.get(eq);
		System.out.print("\n\n\rYou have eqquiped: " + weapons.get(eq) + "\n\r");		
		Terminal.getLine("\nHit enter to continue... >");

	}

/**
 * This method prints a list of every armor in the inventory. It then asks prompts the user to select a armor to equip, and sets EquippedArmor to the selected armor.
 */
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

		int eq = Integer.parseInt(Terminal.getLine(">"))-1;
		equippedArmor = armors.get(eq);
		System.out.print("\n\n\rYou have eqquiped: " + armors.get(eq) + "\n\r");
		Terminal.getLine("\nHit enter to continue... >");

	}

/**
 * This method returns the currently equipped weapon.
 *
 * @return Returns Item which is the weapon equipped.
 */
	public Item getEquippedWeapon() {
		return this.equippedWeapon;
	}

/**
 * This method return the currently equipped armor.
 *
 * @return Returns Item which is the armor equipped.
 */
	public Item getEquippedArmor() {
		return this.equippedArmor;
	}

/**
 * This method updats the equipped weapon to a given item.
 *
 * @param i Item to be equipped.
 */
	public void setEquippedWeapon(Item i) {
		this.equippedWeapon = i;
	}

/**
 * This method updates tho equipped armor to a given item.
 *
 * @param i Item to be equipped.
 */
	public void setEquippedArmor(Item i) {
		this.equippedArmor = i;
	}

/**
 * This method returns the list of items in the inventory.
 *
 * @return Returns ArrayList<Item> containing items in the inventory.
 */
	public ArrayList<Item> getItems() {
		return this.items;
	}

/**
 * This method saves the contents of the inventory to a given save file.
 *
 * @param out PrintWriter connected to the save file that is to be written to.
 */
	public void save(PrintWriter out) {
	
		out.println(maxWeight);
		equippedWeapon.save(out, false);
		equippedArmor.save(out, false);

		if (items.size() > 0) {
			out.println(" ");
			for (int i = 0; i < items.size(); i++) {
				if (i == items.size()-1) {
					items.get(i).save(out, true);
				} else {
					items.get(i).save(out, false);
				}
				
			}
		} else { out.println(); }

	}

}
