import java.io.PrintWriter;
import java.util.Scanner;

/**
 * this class, for every player created in both Game.java and Main.java, takes a string name as a parameter,
 * creates a new personal inventory for said player, sets its inital state to be alive (true),
 * initializes its health to 100, and saves the player's information and stats.
 *
 */

public class Player {
    private String name;
    private int health;
    private int damage;
    private int armor;
    private Inventory playerInventory;
    private Boolean alive;
    private int locX;
    private int locY;

    /**
     * instantiates what is needed for the player in the game, creates an inventory
     * for the player, sets the state of the player to be alive,
     * and sets the health to 100.
     *
     * @param n, string that is for the name of the player
     */

    public Player(String n) {
        this.name = n;
	this.playerInventory = new Inventory(100);
	this.alive = true;
	this.health = 100;
    }
   
   /**
    * this method loads the constructor for player information.
    *
    * @param in, Scanner takes in the data to the file that stores player information
    */
   
    public Player(Scanner in) {
	    name = in.nextLine();
	    health = in.nextInt();
	    in.nextLine();
	    damage = in.nextInt();
	    in.nextLine();
	    armor = in.nextInt();
	    in.nextLine();
	    alive = in.nextBoolean();
	    in.nextLine();
	    locX = in.nextInt();
	    in.nextLine();
	    locY = in.nextInt();
	    in.nextLine();
	    playerInventory = new Inventory(in);
    }

    /**
     * this method returns the name of the player
     *
     * @return player name
     */

    public String getName() {
        return this.name;
    }

    /**
     * returns the player's health status
     *
     * @return player health
     */

    public int getHealth() {
        return this.health;
    }

    /**
     * this method returns int that represents the damage of the weapon
     *
     * @return damage of the weapon
     */

    public int getDamage() {
        return this.damage;
    }

    /**
     * this method returns int that represents equipped armor's strength value
     *
     * @return strength value of armor
     */

    public int getArmor() {
        return this.armor;
    }

    /**
     * allows for access to player's inventory 
     *
     * @return player's inventory
     */
 
    public Inventory getInv() {
        return this.playerInventory;
    }

    /**
     * returns the player's state of being either dead or alive
     *
     * @return if player is alive or dead
     */

    public Boolean getAlive() {
        return this.alive;
    }

    /**
     * this method returns the x coordinate of the player
     *
     * @return x coordinate of player
     */

    public int getLocX() {
        return this.locX;
    }

    /**
     * this method returns the y coordinate of the player
     *
     * @return y coordinate of player
     */

    public int getLocY() {
        return this.locY;
    }

    /**
     * this method while in battle sets the player's health stats after attacks
     *
     * @param newHealth int that holds the new health after attacks
     */

    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    /**
     * this methods sets the damage
     *
     * @param newDamage int that holds the damage once attack has occured
     */

    public void setDamage(int newDamage) {
        this.damage = newDamage;
    }


    /**
     * this method sets the armor
     *
     * @param newArmor int that has any newly equipped armor
     */

    public void setArmor(int newArmor) {
        this.armor = newArmor;
    }

    /**
     * this methods sets thhe state of the player, whether it's alive or not
     *
     * @param newAlive boolean
     */

     public void setAlive(Boolean newAlive) {
        this.alive = newAlive;
    }

    /**
     * sets the player's x coordinate
     *
     * @param newLocX int
     */

    public void setLocX(int newLocX) {
        this.locX = newLocX;
    }

    /**
     * sets player's y coordinate/**
     * 
     * @param newLocY int
     */

    public void setLocY(int newLocY) {
        this.locY = newLocY;
    }

    /**
     * this method saves a player's information/stats
     *
     * @param out, PrintWriter that links to the file which stores the player's information/stats
     */

    public void save(PrintWriter out) {
	    out.println(name);
	    out.println(health);
	    out.println(damage);
	    out.println(armor);
	    out.println(alive);
	    out.println(locX);
	    out.println(locY);
	    playerInventory.save(out);
    }

}
