
//for every player created in both Game.java and Main.java, it takes a string name as a parameter, creates a new personal inventory for said player, sets its inital state to be alive (true), and initializes its health to 100.
public class Player {
    private String name;
    private int health;
    private int damage;
    private int armor;
    private Inventory playerInventory;
    private Boolean alive;
    private int locX;
    private int locY;

    public Player(String n) {
        this.name = n;
	this.playerInventory = new Inventory(100);
	this.alive = true;
	this.health = 100;
    }

    //returns player's name
    public String getName() {
        return this.name;
    }

    //returns player's health stat
    public int getHealth() {
        return this.health;
    }

    //returns int that represents equipped weapon's strength value
    public int getDamage() {
        return this.damage;
    }

    //returns int that represents equipped armor's strength value
    public int getArmor() {
        return this.armor;
    }

    //allows for access to player's inventory 
    public Inventory getInv() {
        return this.playerInventory;
    }

    //returns player's state of being either dead or alive
    public Boolean getAlive() {
        return this.alive;
    }

    //returns player's x coordinate
    public int getLocX() {
        return this.locX;
    }

    //returns player's y coordinate
    public int getLocY() {
        return this.locY;
    }

    //in battle it sets the player's health stats after attacks
    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    //sets damage
    public void setDamage(int newDamage) {
        this.damage = newDamage;
    }

    //sets armor 
    public void setArmor(int newArmor) {
        this.armor = newArmor;
    }

    //sets if player is either dead or alive
    public void setAlive(Boolean newAlive) {
        this.alive = newAlive;
    }

    //sets player's x coordinate
    public void setLocX(int newLocX) {
        this.locX = newLocX;
    }

    //sets player's y coordinate
    public void setLocY(int newLocY) {
        this.locY = newLocY;
    }

}
