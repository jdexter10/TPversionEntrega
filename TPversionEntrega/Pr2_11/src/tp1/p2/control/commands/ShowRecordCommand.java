package tp1.p2.control.commands;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.RecordException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ShowRecordCommand extends Command {

	
	@Override
	protected String getName() {
		return Messages.COMMAND_SHOW_RECORD_NAME;
	}
	
	@Override
	protected String getShortcut() {
		return Messages.COMMAND_SHOW_RECORD_SHORTCUT;
	}
	
	@Override
	public String getDetails() {
		return Messages.COMMAND_SHOW_RECORD_DETAILS;
	}
	
	@Override
	public String getHelp() {
		return Messages.COMMAND_SHOW_RECORD_HELP;
	}
	/**
	 * Execute the command.
	 * 
	 * @param game Where to execute the command.
	 * 
	 * @return {@code true} if game board must be printed {@code false} otherwise.
	 */
	@SuppressWarnings("null")
	@Override
	public boolean execute(GameWorld game)throws GameException
	{
		Scanner scanner = null; 
		String levelName;
		int record = 0;
		boolean found = false;
		
		try {
			scanner = new Scanner(new BufferedReader(new FileReader(Messages.RECORD_FILENAME)));
			scanner.useLocale(Locale.US);
			while (scanner.hasNext() && !found) 
			{
				levelName = scanner.next();
				record = scanner.nextInt();
				if (levelName.equals(game.getLevelName())) 
				{
					found = true;
				}
			}
			System.out.println(Messages.CURRENT_RECORD.formatted(game.getLevelName(), record));

		} catch (InputMismatchException ime) 
		{
			throw new RecordException(Messages.RECORD_READ_ERROR);
		} 
		catch (FileNotFoundException e) 
		{
			throw new RecordException(Messages.RECORD_READ_ERROR);
		}
		return false;
	}

}
