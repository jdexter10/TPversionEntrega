package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class AddPlantCommand extends Command implements Cloneable {

	private int col;

	private int row;

	private String plantName;

	private boolean consumeCoins;

	public AddPlantCommand() {
		this(true);
	}
	public AddPlantCommand(String name, int col, int row) 
	{
		this.plantName = name;
		this.col = col;
		this.row = row;
	}
	public AddPlantCommand(boolean consumeCoins) {
		this.consumeCoins = consumeCoins;
	}
	/**
	 * Recibe el nombre del comando
	 * 
	 * 
	 * @return Un String con el nombre del comando
	 */
	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_NAME;
	}
	/**
	 * Recibe el shortCut del comando
	 * 
	 * 
	 * @return Un String con el shortCut del comando
	 */
	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_SHORTCUT;
	}
	/**
	 * Recibe los detalles del comando
	 * 
	 * 
	 * @return Un String con los detalles del comando
	 */
	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_DETAILS;
	}
	/**
	 * Recibe la información del comando
	 * 
	 * 
	 * @return Un String con la información del comando
	 */
	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_HELP;
	}
	/**
	 * Comprueba si se puede añadir la planta. Si es así la añade
	 * 
	 * @param game Juego
	 * 
	 * @return ExecutionResult con true o con el mensaje de error
	 */
	@Override
	public ExecutionResult execute(GameWorld game) {
		GameItem item = game.getGameItemInPosition(col , row);
		
		//Si los valores introducidos no se encuentran entre los predeterminados
		if(this.col >= GameWorld.NUM_COLS || this.row >= GameWorld.NUM_ROWS || this.col < 0 || this.row < 0) 
		{
			return new ExecutionResult(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		
		//Si hay un zombie o una planta en la posición introducida
		else if(!game.isPositionEmpty(this.col,row) && (item.receivePlantAttack(0) || item.receiveZombieAttack(0))) 
		{
			return new ExecutionResult(Messages.INVALID_POSITION);
		}
		
		//Crea la planta con los valores introducidos
		Plant plant = PlantFactory.spawnPlant(this.plantName, game, this.col, this.row);
		
		//Comprueba si hay suficientes sunCoins para comprar la planta
		if(!game.tryToBuy(plant.getCost())) 
		{
			return new ExecutionResult(Messages.NOT_ENOUGH_COINS);
		}
		
		//Añade la planta al juego
		game.addNpc(plant);
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
		AddPlantCommand aux = new AddPlantCommand(false);
		int col,row;
		col = Integer.parseInt(parameters[2]);
		row = Integer.parseInt(parameters[3]);
		
		
		try {
			aux = new AddPlantCommand(true);

			this.plantName = parameters[1];
			this.col = col;
			this.row = row;
			aux = (AddPlantCommand)this.clone();
		} 
		catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
		return aux;
	}

}
