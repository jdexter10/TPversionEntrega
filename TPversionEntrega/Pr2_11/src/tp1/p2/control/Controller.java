package tp1.p2.control;

import static tp1.p2.view.Messages.debug;
import static tp1.p2.view.Messages.error;

import java.io.IOException;
import java.util.Scanner;

import tp1.p2.logic.Game;
import tp1.p2.view.GamePrinter;
import tp1.p2.view.Messages;
import tp1.p2.control.exceptions.RecordException;
import tp1.p2.control.exceptions.GameException;

/**
 * Accepts user input and coordinates the game execution logic.
 */
public class Controller {

	private Game game;

	private Scanner scanner;

	private GamePrinter gamePrinter;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		this.gamePrinter = new GamePrinter(game);
	}

	/**
	 * Draw / Paint the game.
	 */
	private void printGame() {
		System.out.println(gamePrinter);
	}
	
	public void printRecord() throws RecordException, IOException
	{
		System.out.println(gamePrinter.setRecord())
;	}

	/**
	 * Prints the final message once the match is finished.
	 */
	public void printEndMessage() {
		System.out.println(gamePrinter.endMessage());
	}

	/**
	 * Show prompt and request command.
	 *
	 * @return the player command as words
	 */
	private String[] prompt() {
		System.out.print(Messages.PROMPT);
		String line = scanner.nextLine();
		String[] words = line.toLowerCase().trim().split("\\s+");

		System.out.println(debug(line));

		return words;
	}

	/**
	 * Runs the game logic.
	 */
	public void run() throws GameException, IOException {
		boolean refreshDisplay = true;
		try 
		{
			game.reset();
		}
		catch(GameException g)
		{
			g.getMessage();
		}
		
		while (!game.isFinished() && !game.isPlayerQuits()) 
		{

			// 1. Draw
			if (refreshDisplay) 
			{
				printGame();
			}

			// 2. User action
			String[] words = prompt();

			if (words.length == 0) 
			{
				System.out.println(error(Messages.UNKNOWN_COMMAND));
			} 
			else {
				try 
				{
					refreshDisplay = false;
				
				Command command = Command.parse(words);
				refreshDisplay = game.execute(command);
				} 
				catch (GameException e) 
				{
					System.out.println(error(e.getMessage()));
				}
			}
		}

		if (refreshDisplay) {
			printGame();
		}
		printRecord();
		printEndMessage();
	}

}
