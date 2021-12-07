import java.util.ArrayList;
import ansi_terminal.*;

/**
 * This class prints a brief plot to the dungeon crawler game and introduces the Narnia theme. It prompts for a player name, and then creates and starts the game.
 */
public class Main {

	/**
	 * Prints the plot, prompts for player name, and starts game.
	 *
	 * @param args the command line arguments.
	 */
	public static void main(String[] args) {
		Terminal.rawMode();
		System.out.println("\nThe portal to Narnia seems to have broken. Professor Kirke instructs you to figure out what happened.\r");
		System.out.println("The wardrobe itself seems to be transporting, but to where?\n\r");

		String name = Terminal.getLine("What is your name? >");
		Game g = new Game(name);
		g.startGame();
	}

}
