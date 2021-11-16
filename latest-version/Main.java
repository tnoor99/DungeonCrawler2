import java.util.ArrayList;
import ansi_terminal.*;

// this class prints a brief plot, prompts for a player name, and then creates and starts the game
public class Main {

// prints the plot, prompts for player name, and starts game
	public static void main(String[] args) {
		Terminal.rawMode();
		System.out.println("\nYour government has thrown you down a well and you wake up to find yourself in a dungeon.\r");
		System.out.println("Others find themselves in the same position, and you must fight eachother to the death.\n\r");

		String name = Terminal.getLine("What is your name? >");
		Game g = new Game(name);
		g.startGame();
	}

}
