
/* This class primarily houses the different values that make up a specific Item. It's methods consist of a constructor to set values according to
given parameters, a series of getters for accessing said values, and a toString method to create an easily readable print format */

import java.io.PrintWriter;
import java.util.Scanner;
public class Item {

	private ItemType type;
	private String name;
	private int weight;
	private int value;
	private int strength;
	private int locX;
	private int locY;
	private Scanner in = new Scanner(System.in);

// Constructs an Item according to the given parameters
	public Item(ItemType type, String name, int weight, int value, int strength, int locX, int locY) {
		this.type = type;
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.strength = strength;
		this.locX = locX;
		this.locY = locY;
	}

// Load constructor for Item class
	public Item(Scanner in) {
	
		ItemType type = ItemType.valueOf(in.nextLine());
                name = in.nextLine();
                weight = in.nextInt();
                in.nextLine();
                value = in.nextInt();
                in.nextLine();
                strength = in.nextInt();
                in.nextLine();
                locX = in.nextInt();
                in.nextLine();
                locY = in.nextInt();
	}

// Returns Item's weight
	public int getWeight() {
		return this.weight;
	}

// Returns Item's value
	public int getValue() {
		return this.value;
	}

// Returns Item's name
	public String getName() {
		return this.name;
	}

// Returns Item's type
	public ItemType getType() {
		return this.type;
	}
// returns item's locX
	public int getLocX() {
		return this.locX;
	}
// returns item's locY
	public int getLocY() {
		return this.locY;
	}
// returns item's strength
	public int getStrength() {
		return this.strength;
	}
// sets item's locX
	public void setLocX(int newLocX) {
		this.locX = newLocX;
	}
// sets item's locY
	public void setLocY(int newLocY) {
		this.locY = newLocY;
	}

// Returns a string conisting of the Item's values in an easily readable format
	public String toString() {
		String str = this.name;
		str += " | " + this.weight;
		str += " | " + this.value;
		str += " | ";
		if (this.type == ItemType.Weapon) {
			str += this.strength;
		} else if (this.type == ItemType.Armor) {
			str += this.strength;
		} else {
			str += "n/a";
		}
		return str;
	}


	public void save(PrintWriter out) {
		out.println(type);
		out.println(name);
		out.println(weight);
		out.println(value);
		out.println(strength);
		out.println(locX);
		out.println(locY);
	}


}
