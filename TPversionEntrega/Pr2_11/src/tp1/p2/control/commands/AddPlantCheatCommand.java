package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.gameobjects.GameObject;

public class AddPlantCheatCommand extends Command{
	
	private int col;

	private int row;

	private String plantName;
	
	public AddPlantCheatCommand() {
	}
	public AddPlantCheatCommand(String name, int col, int row) 
	{
		this.plantName = name;
		this.col = col;
		this.row = row;
	}
	
	@Override
	protected String getName() {
		return Messages.COMMAND_CHEAT_PLANT_NAME;
	}
	
	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CHEAT_PLANT_SHORTCUT;
	}
	
	@Override
	public String getDetails() {
		return Messages.COMMAND_CHEAT_PLANT_DETAILS;
	}
	
	@Override
	public String getHelp() {
		return Messages.COMMAND_CHEAT_PLANT_HELP;
	}

	
	@Override
	public boolean execute(GameWorld game) throws GameException {
		GameObject object = game.getObjectInPosition(col , row);

		
		if(this.col >= GameWorld.NUM_COLS || this.row >= GameWorld.NUM_ROWS || this.col < 0 || this.row < 0)
		{
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		
		
		else if(!game.isPositionEmpty(this.col,row) && (object.fillsPosition()))
		{
			throw new CommandExecuteException(Messages.INVALID_POSITION.formatted(this.col, this.row));
		}
		
		Plant plant = PlantFactory.spawnPlant(this.plantName, game, this.col, this.row);
		if(plant == null)
		{
			throw new CommandExecuteException(Messages.ERROR.formatted("Invalid plant name"));
		}
		game.addNpc(plant);
		game.update();
		return true;
	}
	
	@Override
	public Command create(String[] parameters)throws GameException {
		int col,row;
		col = Integer.parseInt(parameters[2]);
		row = Integer.parseInt(parameters[3]);
		
		this.plantName = parameters[1];
		this.col = col;
		this.row = row;
		return this;
	}
}
