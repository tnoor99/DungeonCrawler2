import java.util.Scanner;
import java.io.PrintWriter;

/**
 * this class for each enemy, gives it a name, a health status, the value of it's equipped item,
 * sets it to true so it's alive, and sets it's location with x and y coordinates
 *
 */

public class Enemy {
	private String name;
	private int health;
	private int damage;
	private boolean alive;
	private int locX;
	private int locY;

	/**
	 * this method sets the enemy's information equal to the following parameters
	 *
	 * @param n, string that stores the name
	 * @param h, int that stores health
	 * @param d, int that holds the damage
	 * @param x, int that holds x coordinate of enemy
	 * @param y, int that holds y coordinate of enemy
	 */

	public Enemy(String n, int h, int d, int x, int y) {
		this.name = n;
		this.health = h;
		this.damage = d;
		this.alive = true;
		this.locX = x;
		this.locY = y;
	}

	/**
	 * this method loads constructor
	 *
	 * @param in, Scanner that takes in the data to the file 
	 */

	public Enemy(Scanner in) {
		name = in.nextLine();
		health = in.nextInt();
		in.nextLine();
		damage = in.nextInt();
		in.nextLine();
		alive = in.nextBoolean();
		in.nextLine();
		locX = in.nextInt();
		in.nextLine();
		locY = in.nextInt();
		in.nextLine();

	}
	
	/**
  	 * this method returns the name of the enemy
    	 *
    	 * @return enemy name
    	 */

	public String getName() {
		return this.name;
	}

	/**
    	 * returns the health status of the enemy
    	 *
    	 * @return enemy's health
    	 */

   	public int getHealth() {
		 return this.health;
	}

	/**
	 * this method returns the strength value of the item equipped
	 *
	 * @return damage of the equipped item
	 */

	public int getDamage() {
		return this.damage;
	}

	/**
	 * this method returns if the enemy is dead or alive
	 *
	 * @return if the enemy is dead or alive
	 */

	public Boolean getAlive() {
		return this.alive;
	}
	
	/**
	 * this returns the x coordinate of the enemy
	 *
	 * @return x coordinate of enemy
	 */

	public int getLocX() {
		return this.locX;
	}
	
	/**
         * this returns the y coordinate of the enemy
         *
         * @return y coordinate of enemy
         */

	public int getLocY() {
		return this.locY;
	}

	/**
	 * this method sets the new health status of the enemy after the attacks in battle
	 *
	 * @param newHealth int that holds the new health after attack 
	 */

	public void setHealth(int newHealth) {
		this.health = newHealth;
	}

	/**
	 * this method sets the state of the enemy as alive or dead after the attacks
	 *
	 * @param newAlive boolean
	 */

	public void setAlive(Boolean newAlive) {
		this.alive = newAlive;	
	}

	/**
	 * this sets the x coordinate of the enemy
	 *
	 * @param newLocX, int
	 */

	public void setLocX(int newLocX) {
		this.locX = newLocX;
	}


	/**
         * this sets the y coordinate of the enemy
         *
         * @param newLocY, int
         */

	public void setLocY(int newLocY) {
		this.locY = newLocY;
	}
	
	/**
	 * this method saves the enemy's health status and more data of the enemy
	 *
	 * @param out, PrintWriter that links to the file which stores the enemy's information/stats
	 * @param last, Boolean 
	 */

	public void save(PrintWriter out, Boolean last) {
		out.println(name);
		out.println(health);
		out.println(damage);
		out.println(alive);
		out.println(locX);
		out.println(locY);
		if (last) {
			out.println("XXXXX");
		} else {
			out.println(" ");
		}

	}

}
