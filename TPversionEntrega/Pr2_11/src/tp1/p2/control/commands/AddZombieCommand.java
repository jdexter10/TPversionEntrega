package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPositionException;

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
	
	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_ZOMBIE_NAME;
	}
	
	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_ZOMBIE_SHORTCUT;
	}
	
	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_ZOMBIE_DETAILS;
	}
	
	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_ZOMBIE_HELP;
	}
	
	@Override
	public boolean execute(GameWorld game) throws GameException {
		GameItem item = game.getGameItemInPosition(col , row);
		
		//Si hay un zombie o una planta en la posición introducida
		if(!game.isPositionEmpty(this.col,row) && (item.receivePlantAttack(0) || item.receiveZombieAttack(0))) 
		{
			throw new InvalidPositionException(Messages.INVALID_POSITION);
		}
		//Crea el zombie con los valores introducidos

		Zombie zombie = ZombieFactory.spawnZombie(this.zombieIdx, game, this.col, this.row);
		//Añade el zombie
		game.addNpc(zombie);
		game.update();
		return true;
	}
	
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
