package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;
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
		
		//Si la posición está vacía o no es una planta
		if((game.isPositionEmpty(this.col,row)) || (!game.isPositionEmpty(this.col,row) && (item.receivePlantAttack(0) || item.receiveZombieAttack(0))) ) 
		{
			throw new CommandExecuteException(Messages.NO_CATCHABLE_IN_POSITION.formatted(this.col,this.row));
		}
		//Comprueba si ya se ha cogido un sol en el ciclo
		if(caughtSunThisCycle) 
		{
			throw new CommandExecuteException(Messages.SUN_ALREADY_CAUGHT);
		}
		
		caughtSunThisCycle = game.tryToCatchObject(col, row);
		
		
		return true;
	}
	
	@Override
	public Command create(String[] parameters) 
	{
		CatchCommand aux = new CatchCommand();
		int col, row;
		col = Integer.parseInt(parameters[1]);
		row = Integer.parseInt(parameters[2]);
			this.col = col;
			this.row = row;
			return this;
	}


}
