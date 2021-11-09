// Item.java
// this class represents a single item, it could be an equippable
// thing like weapon or ring, or something generic

public class Item {
    // what sort of item it is
    private ItemType type;

    // the name of the item as shown to the user
    private String name;

    // how much it weighs (player can only carry so much)
    private int weight;

    // how much the item is worth for buying/selling
    private int value;

    // the item's strength - this differs based on the type
    // for a weapon, it's damage
    // for armor, it's protection
    private int strength;

    public Item(ItemType type, String name, int weight, int value, int strength) {
        this.type = type;
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.strength = strength;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public int getStrength() {
        return strength;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    @Override
    public String toString() {
        return name + " " + weight + " " + value + " " + strength;
    }
}

