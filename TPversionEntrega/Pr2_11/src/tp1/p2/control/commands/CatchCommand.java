package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

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
	/**
	 * Recibe el nombre del comando
	 * 
	 * 
	 * @return Un String con el nombre del comando
	 */
	@Override
	protected String getName() {
		return Messages.COMMAND_CATCH_NAME;
	}
	/**
	 * Recibe el shortCut del comando
	 * 
	 * 
	 * @return Un String con el shortCut del comando
	 */
	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CATCH_SHORTCUT;
	}
	/**
	 * Recibe los detalles del comando
	 * 
	 * 
	 * @return Un String con los detalles del comando
	 */
	@Override
	public String getDetails() {
		return Messages.COMMAND_CATCH_DETAILS;
	}
	/**
	 * Recibe la información del comando
	 * 
	 * 
	 * @return Un String con la información del comando
	 */
	@Override
	public String getHelp() {
		return Messages.COMMAND_CATCH_HELP;
	}
	/**
	 * Comprueba si se puede coger un Sun. Si es así lo coge
	 * 
	 * @param game Juego
	 * 
	 * @return ExecutionResult con true o con el mensaje de error
	 */
	@Override
	public ExecutionResult execute(GameWorld game) {
		GameItem item = game.getGameItemInPosition(col , row);
		if(this.col >= GameWorld.NUM_COLS || this.row >= GameWorld.NUM_ROWS || this.col < 0 || this.row < 0) 
		{
			return new ExecutionResult(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		//Si la posición está vacía o no es una planta
		if((game.isPositionEmpty(this.col,row)) || (!game.isPositionEmpty(this.col,row) && (item.receivePlantAttack(0) || item.receiveZombieAttack(0))) ) 
		{
			return new ExecutionResult(Messages.NO_CATCHABLE_IN_POSITION.formatted(this.col,this.row));
		}
		//Comprueba si ya se ha cogido un sol en el ciclo
		if(caughtSunThisCycle) 
		{
			return new ExecutionResult(Messages.SUN_ALREADY_CAUGHT);
		}
		
		caughtSunThisCycle = game.tryToCatchObject(col, row);
		
		
		return new ExecutionResult(false);
	}
	/**
	 * Crea el comando según los parámetros introducidos por consola
	 * 
	 * @param parameters Parametros introducidos por consola
	 * 
	 * @return El comando creado
	 */
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
