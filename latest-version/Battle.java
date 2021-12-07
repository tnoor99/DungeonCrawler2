import ansi_terminal.*;
//upon running into an enemy in the dungeon, this class is evoked in the Game.java class where it readies the player for battle. The startBattle() method gets the strength of the equipped weapon from the inventory and uses an if/else 
//statement to send the player into the round() method. In there while the player and enemy are both alive the attacks begin and new health stats are calculated. If this.Round(p, e) returns true that means the player killed the enemy because
//its health stat was less than 1. If it returns false that means the player died. 

/**
 * This class handles battles between the player and enemies.
 */
public class Battle {

	public Battle() {
		//
	}
	/**
	 * Gets the strength of the equipped weapon and armor from the inventory before sending the player into battle/round().
	 *
	 * @param p the player entering the battle.
	 * @param e the enemy that is being fought.
	 */
	 public void startBattle(Player p, Enemy e) {
	 	Terminal.clear();
		p.getInv().getEquippedWeapon();
		p.getInv().getEquippedArmor();
		p.setDamage(p.getInv().getEquippedWeapon().getStrength()); //gets equipped weapon's strength for battle
		p.setArmor(p.getInv().getEquippedArmor().getStrength()); //gets equipped armor's strength for battle
		if(this.Round(p, e)) {
			System.out.print("You killed " + e.getName() + "!\n\n\r");
			Terminal.getLine("Hit enter to continue... >");
		} else {
			System.out.println(e.getName() + " killed you!\n\rThanks for playing!\r");
			Terminal.cookedMode();
			System.exit(1);
		}

		
    
    	}	
    
	/**
	 * While the player and enemy are both alive the attacks begin and new health stats are calculated.
	 *
	 * @return true if the player killed the enemy and its health stat was less than 0. Or return false if the player died.
	 */
    	public boolean Round(Player p, Enemy e) {
        	while(p.getAlive() && e.getAlive()) {
            		e.setHealth(e.getHealth() - p.getDamage()); //calculating enemy's health
            		System.out.printf("You attack %s for %d damage.", e.getName(), p.getDamage());
			System.out.print("\n\r");
            		p.setHealth(p.getHealth() - (e.getDamage() - p.getArmor())); //calculating player's health
            		System.out.printf("%n%s hits you for %d damage.", e.getName(), e.getDamage()-p.getInv().getEquippedArmor().getStrength());
			System.out.print("\n\r");
			if (p.getHealth() < 0 && e.getHealth() < 0) {
            			System.out.printf("%nYou now have 0 health. %s now has 0 health.", e.getName());
			} else if (p.getHealth() < 0) {
            			System.out.printf("%nYou now have 0 health. %s now has %d health.", e.getName(), e.getHealth());
			} else if (e.getHealth() < 0) {
            			System.out.printf("%nYou now have %d health. %s now has 0 health.", p.getHealth(), e.getName());
			} else {
            			System.out.printf("%nYou now have %d health. %s now has %d health.", p.getHealth(), e.getName(), e.getHealth());
			}
			System.out.print("\n\r");
			Terminal.getLine("Hit enter to continue... >");
			System.out.print("\n\r");
			if (p.getHealth() < 1) {
				p.setAlive(false);
				return false; //player died. Round() is false
			}
			if (e.getHealth() < 1) {
				e.setAlive(false);
				e.setLocX(-1);
				e.setLocY(-1);
				return true; //enemy died. Round() is true
			}
        	}
		return false;
	}	
}

