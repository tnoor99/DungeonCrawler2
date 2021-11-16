//for each enemy, this class gives the enemy a name, a health status, the value of it's equipped item, sets it to true so it's alive, and sets it's location with x & y coordinates
public class Enemy {
	private String name;
	private int health;
	private int damage;
	private boolean alive;
	private int locX;
	private int locY;


	public Enemy(String n, int h, int d, int x, int y) {
		this.name = n;
		this.health = h;
		this.damage = d;
		this.alive = true;
		this.locX = x;
		this.locY = y;
	}
	
	//this returns the enemy's name
	public String getName() {
		return this.name;
	}

	//this returns the enemy's health status
   	public int getHealth() {
		 return this.health;
	}

	//this returns the strength value of the item equipped
	public int getDamage() {
		return this.damage;
	}

	//this returns if the enemy is dead or alive
	public Boolean getAlive() {
		return this.alive;
	}
	
	//this returns the x coordinate of the enemy
	public int getLocX() {
		return this.locX;
	}
	
	//this returns the y coordinate of the enemy
	public int getLocY() {
		return this.locY;
	}

	//this sets the new health status of the enemy after the attacks in battle
	public void setHealth(int newHealth) {
		this.health = newHealth;
	}

	//this sets the enemmy as alive or dead after the attacks
	public void setAlive(Boolean newAlive) {
		this.alive = newAlive;	
	}

	//this sets the x coordinate of the enemy
	public void setLocX(int newLocX) {
		this.locX = newLocX;
	}

	//this sets the y coordinate of the enemy
    	public void setLocY(int newLocY) {
		this.locY = newLocY;
	}

}
