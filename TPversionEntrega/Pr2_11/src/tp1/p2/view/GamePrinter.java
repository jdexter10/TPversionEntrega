package tp1.p2.view;

import static tp1.utils.StringUtils.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import tp1.p2.logic.GameStatus;
import tp1.p2.logic.GameWorld;
import tp1.p2.control.exceptions.RecordException;
import tp1.p2.view.Messages;
import tp1.utils.StringUtils;

public class GamePrinter {

	private static final String SPACE = " ";

	private static final String CELL_BORDER_CHAR = "-";

	private static final String VERTICAL_DELIMITER = "|";

	private static final String NEW_LINE = System.lineSeparator();

	private static final int MARGIN_SIZE = 2;

	private static final String MARGIN = repeat(SPACE, MARGIN_SIZE);

	private static final String EXTENDED_MARGIN = repeat(SPACE, MARGIN_SIZE+2);

	private static final int CELL_SIZE = 13;

	private static final String CELL_BORDER = repeat(CELL_BORDER_CHAR, CELL_SIZE);

	private static final String ROW_BORDER = SPACE + repeat(CELL_BORDER + SPACE, GameWorld.NUM_COLS);

	private static final String IDENTED_ROW_BORDER = String.format("%n%s%s%n", EXTENDED_MARGIN, ROW_BORDER);

	private GameStatus game;

	public GamePrinter(GameStatus game) {

		this.game = game;


	}

	/**
	 * Builds a string that represent the game status: cycles, suncoins, remaining zombies.
	 * 
	 * @return the string that represents the game status.
	 */
	protected String getInfo() {
		StringBuilder buffer = new StringBuilder();
		
		buffer.append(Messages.NUMBER_OF_CYCLES + SPACE + game.getCycle() +  NEW_LINE);

		buffer.append(Messages.NUMBER_OF_COINS + SPACE + game.getSuncoins() +  NEW_LINE);
		
		buffer.append(Messages.REMAINING_ZOMBIES + SPACE + game.getRemainingZombies() +  NEW_LINE);

		return buffer.toString();
	}

	/**
	 * Builds complete representation (status+board) of the game.
	 * 
	 * @return a string representation of the game.
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();

		// Game Status

		str.append(getInfo());
		
		str.append(EXTENDED_MARGIN);
		for (int col = 0; col <= GameWorld.NUM_COLS; col++) {
			str.append(StringUtils.centre(Integer.toString(col), CELL_SIZE+1));
		}

		// Paint game board

		str.append(IDENTED_ROW_BORDER);

		for (int row = 0; row < GameWorld.NUM_ROWS; row++) {
			
			str.append(MARGIN).append(Integer.toString(row)).append(SPACE).append(VERTICAL_DELIMITER);
			
			for (int col = 0; col <= GameWorld.NUM_COLS; col++) {
				str.append(StringUtils.centre(game.positionToString(col, row), CELL_SIZE));
				if(col < GameWorld.NUM_COLS) 
				{
					str.append(VERTICAL_DELIMITER);
				}
			}
				str.append(IDENTED_ROW_BORDER);
		}

		return str.toString();
	}

	/**
	 * Builds the message to be printed once the game has finished.
	 * 
	 * @return a string representing a message to be printed once the game has finished.
	 */
	public String endMessage() {
		StringBuilder buffer = new StringBuilder(Messages.GAME_OVER);

		if(this.game.deadPlayer()) 
		{
			buffer.append(NEW_LINE + Messages.ZOMBIES_WIN);
		}
		else if(this.game.allZombiesDead()) 
		{
			buffer.append(NEW_LINE + Messages.PLAYER_WINS);
		}
		else if(this.game.isPlayerQuits()) 
		{
			buffer.append(NEW_LINE + Messages.PLAYER_QUITS);
		}
		return buffer.toString();
	}


public String setRecord() throws RecordException, IOException 
	{
		StringBuilder buffer = new StringBuilder("");
		Scanner scanner = null; 
		String levelName;
		String tmpName = "temporal.txt";
	
		int record = 0;
	
		boolean bigger = false;
		boolean writeNewRecord = false;
	
			try {
				File newFile = new File(tmpName);
				FileWriter fileWriter= new FileWriter(newFile, false);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				PrintWriter printWriter = new PrintWriter(bufferedWriter);
				File actualFile = new File(Messages.RECORD_FILENAME);
		
				scanner = new Scanner(new BufferedReader(new FileReader(Messages.RECORD_FILENAME)));
				scanner.useLocale(Locale.US);
				while (scanner.hasNext()) 
				{
					levelName = scanner.next();
				record = scanner.nextInt();
				if (levelName.equals(game.getLevelName()))
				{
					if(game.getScore() > record) 
					{
						bigger = true;
						writeNewRecord = true;
					}
				}
			
				printWriter.print(levelName + SPACE);
			
				if(writeNewRecord) 
				{
					printWriter.println(game.getScore());
					writeNewRecord = false;
				}
				else 
					printWriter.println(record);
				}
				scanner.close();
				printWriter.flush();
				printWriter.close();
				actualFile.delete();
				File dump = new File(Messages.RECORD_FILENAME);
				newFile.renameTo(dump);

			} catch (InputMismatchException ime) 
			{
				throw new RecordException(Messages.RECORD_READ_ERROR + " or " + Messages.RECORD_WRITE_ERROR);
			} catch (FileNotFoundException e) {
				throw new RecordException(Messages.RECORD_READ_ERROR + " or " + Messages.RECORD_WRITE_ERROR);
			}
			catch(IOException  ioe) 
			{
				throw new RecordException(Messages.RECORD_READ_ERROR + " or " + Messages.RECORD_WRITE_ERROR);
			}
			if(bigger) 
			{
		
				buffer.append(Messages.NEW_RECORD);
				buffer.append(game.getLevelName());
				buffer.append(SPACE);
				buffer.append(game.getScore());
			}
	
			return buffer.toString();
	}
}

