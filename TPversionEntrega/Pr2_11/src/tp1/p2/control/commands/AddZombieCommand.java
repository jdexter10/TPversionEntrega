package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;

public class AddZombieCommand extends Command {

	private int zombieIdx;

	private int col;

	private int row;

	public AddZombieCommand() {

	}

	private AddZombieCommand(int zombieIdx, int col, int row) {
		this.zombieIdx = zombieIdx;
		this.col = col;
		this.row = row;
	}
	/**
	 * Recibe el nombre del comando
	 * 
	 * 
	 * @return Un String con el nombre del comando
	 */
	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_ZOMBIE_NAME;
	}
	/**
	 * Recibe el shortCut del comando
	 * 
	 * 
	 * @return Un String con el shortCut del comando
	 */
	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_ZOMBIE_SHORTCUT;
	}
	/**
	 * Recibe los detalles del comando
	 * 
	 * 
	 * @return Un String con los detalles del comando
	 */
	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_ZOMBIE_DETAILS;
	}
	/**
	 * Recibe la información del comando
	 * 
	 * 
	 * @return Un String con la información del comando
	 */
	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_ZOMBIE_HELP;
	}
	/**
	 * Comprueba si se puede añadir el zombie. Si es así la añade
	 * 
	 * @param game Juego
	 * 
	 * @return ExecutionResult con true o con el mensaje de error
	 */
	@Override
	public ExecutionResult execute(GameWorld game) {
		GameItem item = game.getGameItemInPosition(col , row);
		
		//Si los valores introducidos no se encuentran entre los predeterminados
		if(this.zombieIdx < 0 || this.zombieIdx >=  ZombieFactory.getAvailableZombies().size()||this.col >= game.NUM_COLS || this.row >= game.NUM_ROWS || this.col < 0 || this.row < 0) 
		{
			return new ExecutionResult(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		
		//Si hay un zombie o una planta en la posición introducida
		else if(!game.isPositionEmpty(this.col,row) && (item.receivePlantAttack(0) || item.receiveZombieAttack(0))) 
		{
			return new ExecutionResult(Messages.INVALID_POSITION);
		}
		//Crea el zombie con los valores introducidos

		Zombie zombie = ZombieFactory.spawnZombie(this.zombieIdx, game, this.col, this.row);
		//Añade el zombie
		game.addNpc(zombie);
		return new ExecutionResult(true);
	}
	/**
	 * Crea el comando según los parámetros introducidos por consola
	 * 
	 * @param parameters Parametros introducidos por consola
	 * 
	 * @return El comando creado
	 */
	@Override
	public Command create(String[] parameters) {
		AddZombieCommand aux = new AddZombieCommand();
		int zombieIdx, col,row;
		zombieIdx = Integer.parseInt(parameters[1]);
		col = Integer.parseInt(parameters[2]);
		row = Integer.parseInt(parameters[3]);
		
		
			this.zombieIdx= zombieIdx;
			this.col = col;
			this.row = row;
		return this;
	}

}
