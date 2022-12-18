package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;
import tp1.p2.control.commands.CatchCommand;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.GameException;

public class CatchCommand extends Command {

	private static boolean caughtSunThisCycle = false;

	private int col;

	private int row;

	public CatchCommand() {
		caughtSunThisCycle = false;
	}
	
	@Override
	protected void newCycleStarted() {
		caughtSunThisCycle = false;
	}

	private CatchCommand(int col, int row) {
		this.col = col;
		this.row = row;
		caughtSunThisCycle = false;
	}
	
	@Override
	protected String getName() {
		return Messages.COMMAND_CATCH_NAME;
	}
	
	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CATCH_SHORTCUT;
	}
	
	@Override
	public String getDetails() {
		return Messages.COMMAND_CATCH_DETAILS;
	}
	
	@Override
	public String getHelp() {
		return Messages.COMMAND_CATCH_HELP;
	}
	
	 
	@Override
	public boolean execute(GameWorld game) throws GameException{
		GameItem item = game.getGameItemInPosition(col , row);
		
		if((game.isPositionEmpty(this.col,row)) || (!game.isPositionEmpty(this.col,row) && (item.receivePlantAttack(0) || item.receiveZombieAttack(0))) ) 
		{
			throw new CommandExecuteException(Messages.NO_CATCHABLE_IN_POSITION.formatted(this.col,this.row));
		}
		//Solo un sol por ciclo se puede coger
		if(caughtSunThisCycle) 
		{
			throw new CommandExecuteException(Messages.SUN_ALREADY_CAUGHT);
		}
		caughtSunThisCycle = game.tryToCatchObject(col, row);
		return true;
	}
	
	@Override
	public Command create(String[] parameters) throws GameException 
	{
		Command ca = new CatchCommand();
		int col, row;
		try 
		{
			col = Integer.parseInt(parameters[1]);
			row = Integer.parseInt(parameters[2]);
			this.col = col;
			this.row = row;
			ca = new CatchCommand(col,row);
		}
		catch(NumberFormatException nfe) 
		{
		    throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[1], parameters[2]), nfe);
		}
		if(col >= GameWorld.NUM_COLS || row >= GameWorld.NUM_ROWS || col < 0 || row < 0) 
		{
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		return ca;		
	}
}
