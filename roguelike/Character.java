// Character.java

import java.util.ArrayList;

import ansi_terminal.*;

public abstract class Character extends Entity {
    // the characters health points
    private int hp;

    public Character(int row, int col, char display, Color color, int hp) {
        super(row, col, display, color);
        this.hp = hp;
    }

    // get the hp, damage, protection and name of character
    public int getHealth() {
        return hp;
    }
    public abstract int getDamage();
    public abstract int getProtection();
    public abstract String getName();

    // do damage to another player, returns if they died
    private boolean dealDamage(Character other, Room room) {
        // this character does damage to the other character
        int damageDone = getDamage() - other.getProtection();

        // prevent negative damage
        if (damageDone < 0) {
            damageDone = 0;
        }

        // actually damage them
        other.hp -= damageDone;

        // prevent negative hp
        if (other.hp < 0) {
            other.hp = 0;
        }

        // print the info on this
        Terminal.warpCursor(room.getRows(), 0);
        if (other.hp > 0) {
            System.out.print(getName() + " does " + damageDone + " damage to " + other.getName()
                + ", leaving " + other.hp + " health.\n\r");
            return false;
        } else {
            System.out.print(getName() + " does " + damageDone + " damage to " + other.getName()
                + ", killing them.\n\r");
            return true;
        }
    }

    // this method performs one round of battle between two characters
    // return false if the player has died aas a result
    public boolean fight(Character other, Room room, ArrayList<Enemy> enemies) {
        // do damage to them first
        boolean killed = dealDamage(other, room);
        if (killed) {
            enemies.remove(other);
        }
        System.out.printf("Press any key to return...\n\r");
        Terminal.getKey();

        // don't allow dead enemies to fight back
        if (killed) {
            return true;
        }

        // now take damage from them
        if (other.dealDamage(this, room)) {
            return false;
        }
        System.out.printf("Press any key to return...\n\r");
        Terminal.getKey();
        return true;
    }
}

