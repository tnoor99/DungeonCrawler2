
/* This class primarily houses the different values that make up a specific Item. It's methods consist of a constructor to set values according to
given parameters, a series of getters for accessing said values, and a toString method to create an easily readable print format */

import java.io.PrintWriter;
import java.util.Scanner;
/**
 * This class stores and handles the values that make up items.
 */
public class Item {

	private ItemType type;
	private String name;
	private int weight;
	private int value;
	private int strength;
	private int locX;
	private int locY;
	private Scanner in = new Scanner(System.in);

/**
 * This method constructs a new item according to the given parameters.
 *
 * @param type ItemType designating the type item.
 * @param name String designating the name of the item.
 * @param weight Int designating the weight of the item.
 * @param value Int designating the value of the item.
 * @param strength Int designating the strength of the item.
 * @param locX Int designating the x coordinate of the item.
 * @param locY Int designating the y coordinate of the item.
 */
	public Item(ItemType type, String name, int weight, int value, int strength, int locX, int locY) {
		this.type = type;
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.strength = strength;
		this.locX = locX;
		this.locY = locY;
	}

/**
 * This method constructs an item based on the data stored in a save file.
 *
 * @param in Scanner connected to the file storing the saved items values.
 */
	public Item(Scanner in) {
	
		type = ItemType.valueOf(in.nextLine());
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
		in.nextLine();
	}

/**
 * This method returns the weight of the item.
 *
 * @return Returns int designating the weight of the item.
 */
	public int getWeight() {
		return this.weight;
	}

/**
 * This method returns the value of the item.
 *
 * @return Returns int designating the value of the item.
 */
	public int getValue() {
		return this.value;
	}

/**
 * This method returns the name of the item.
 *
 * @return Returns string designating the name of the item.
 */
	public String getName() {
		return this.name;
	}

/**
 * This method returns the type of the item.
 *
 * @return Returns ItemType designating the type of the item.
 */
	public ItemType getType() {
		return this.type;
	}

/**
 * This method returns the x coordinate of the item.
 *
 * @return Returns int designating the x coordinate of the item.
 */
	public int getLocX() {
		return this.locX;
	}

/**
 * This method returns the y coordinate of the item.
 *
 * @return Returns int designating the y coordinate of the item.
 */
	public int getLocY() {
		return this.locY;
	}

/**
 * This method returns the strength of the item.
 *
 * @return Returns int designating the strength of the item.
 */
	public int getStrength() {
		return this.strength;
	}

/**
 * This method updates the item's x coordinate.
 *
 * @param newLocX Int designating the new value for the item's x coordinate.
 */
	public void setLocX(int newLocX) {
		this.locX = newLocX;
	}

/**
 * This method updates the item's y coordinate.
 *
 * @param newLocY Int designating the new value for the item's y coordinate.
 */
	public void setLocY(int newLocY) {
		this.locY = newLocY;
	}

/**
 * This method returns a string contaning the item's values in an easily readable format.
 *
 * @return Returns a string containing the item's values.
 */
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

/**
 * This method saves the contents of the item to a specified file so that it may be loaded later.
 *
 * @param out PrintWriter connected to the save file that is to be written to.
 * @param last Boolean designating whether the item is the last of it's group to be saved. This is used for formatting the save file.
 */
	public void save(PrintWriter out, Boolean last) {
		out.println(type);
		out.println(name);
		out.println(weight);
		out.println(value);
		out.println(strength);
		out.println(locX);
		out.println(locY);
		if (last) {
			out.println("XXXXX");
		} else {
			out.println(" ");
		}
	}


}
