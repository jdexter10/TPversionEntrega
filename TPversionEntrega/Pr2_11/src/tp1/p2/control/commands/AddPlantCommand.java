package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;
import tp1.p2.control.commands.AddPlantCommand;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.NotEnoughCoinsException;
import tp1.p2.logic.gameobjects.GameObject;

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
	
	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_NAME;
	}
	
	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_SHORTCUT;
	}
	
	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_DETAILS;
	}
	
	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_HELP;
	}
	
	@Override
	public boolean execute(GameWorld game) throws GameException {
		GameObject object = game.getObjectInPosition(col , row);
		
		if(!game.isPositionEmpty(this.col,row) && (object.fillsPosition())) 
		{
			throw new CommandExecuteException(Messages.INVALID_POSITION.formatted(this.col, this.row));
		}
		
		Plant plant = PlantFactory.spawnPlant(this.plantName, game, this.col, this.row);
		
		if(plant == null)
		{
			throw new CommandExecuteException(Messages.ERROR.formatted("Invalid plant name"));
		}
		
		try 
		{
			game.tryToBuy(plant.getCost());
			game.addNpc(plant);
			game.update();
		}
		catch(NotEnoughCoinsException coinsE) 
		{
			throw new NotEnoughCoinsException(Messages.NOT_ENOUGH_COINS);
		}
	
		return true;
	}
	
	@Override
	public Command create(String[] parameters) throws GameException{
		AddPlantCommand plant = new AddPlantCommand(false);
		int col,row;
		try {
			plant = new AddPlantCommand(true);
			col = Integer.parseInt(parameters[2]);
			row = Integer.parseInt(parameters[3]);
			this.plantName = parameters[1];
			this.col = col;
			this.row = row;
			plant = (AddPlantCommand)this.clone();
		} 
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		catch(NumberFormatException nfe) 
		{
			throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[1], parameters[2]), nfe);
		}
		if(this.col >= GameWorld.NUM_COLS || this.row >= GameWorld.NUM_ROWS || this.col < 0 || this.row < 0) 
		{
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER.formatted(this.col,this.row));
		}
		return plant;
	}

}
