import java.util.Random;


/**
 * This class creates a random set of values that can be used to create a new Item. It consits of a constructor which creates arrays of the
 * different possible item names, and the generate method which uses a random number generator to select a random set of values.
 */
public class ItemGenerator {
	
	private static String[] armors = new String[12];
	private static String[] weapons = new String[12];
	private static String[] others = new String[20];
	private static String[] adjectives = new String[12];

/**
 * Creates all the different name possibilities for armors, weapons, and others.
 */
	public ItemGenerator() {
		
		armors[0] = "Space Helmet";
		armors[1] = "Space Vest";
		armors[2] = "Space Pants";
		armors[3] = "Space Boots";
		armors[4] = "Starsteel Helmet";
		armors[5] = "Starsteel Vest";
		armors[6] = "Starsteel Leggings";
		armors[7] = "Starsteel Boots";
		armors[8] = "Electron Mask";
		armors[9] = "Electron Cloak";
		armors[10] = "Electron Trousers";
		armors[11] = "Electron Shoes";

		weapons[0] = "Laser Pistol";
		weapons[1] = "Laser Rifle";
		weapons[2] = "Plasma Pistol";
		weapons[3] = "Plasma Rifle";
		weapons[4] = "Meteorite Sword";
		weapons[5] = "Meteorite Dagger";
		weapons[6] = "Electron Sword";
		weapons[7] = "Electron Dagger";
		weapons[8] = "Proton Grenade";
		weapons[9] = "Electron Grenade";
		weapons[10] = "Starsteel Sword";
		weapons[11] = "Starsteel Dagger";

		others[0] = "Field Rations";
		others[1] = "Spaceship Keys";
		others[2] = "System Map";
		others[3] = "Glasses";
		others[4] = "Hologram Projector";
		others[5] = "Alien Necklace";
		others[6] = "Moon Rock";
		others[7] = "Spaceship Fuel";
		others[8] = "Battery";
	
		adjectives[0] = "Broken";
		adjectives[1] = "Pristine";
		adjectives[2] = "Smelly";
		adjectives[3] = "Ancient";
		adjectives[4] = "Shiny";
		adjectives[5] = "Damaged";
		adjectives[6] = "Stolen";
		adjectives[7] = "\"Borrowed\"";
		adjectives[8] = "Rare";
		adjectives[9] = "Common";
		adjectives[10] = "Legendary";
		adjectives[11] = "Haunted";
	}

	/**
	 * Uses a random number generator to return a set of randomly selected item values. Item types and names are ensured to be alligned.
	 *
	 * @return Item which is generated.
	 */
	public static Item generate() {
		Random rand = new Random();
		int typeNum = rand.nextInt(5);
		String name = "";
		int strength = 0;

		ItemType type;
		name += adjectives[rand.nextInt(12)] + " ";
		if (typeNum < 2) {
			type = ItemType.Weapon;
			name += weapons[rand.nextInt(12)];
			strength = rand.nextInt(80) + 10;

		}
		else if (typeNum < 4) {
			type = ItemType.Armor;
			name += armors[rand.nextInt(12)];
			strength = rand.nextInt(80) + 10;
		}
		else {
			type = ItemType.Other;
			name += others[rand.nextInt(9)];
		}
		
		int weight = rand.nextInt(20) + 1;
		int value = rand.nextInt(1001);

		return new Item(type, name, weight, value, strength, -1, -1);
	}

}
