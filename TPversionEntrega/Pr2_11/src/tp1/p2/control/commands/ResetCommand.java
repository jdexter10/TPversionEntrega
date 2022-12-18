package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.Level;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;
import tp1.p2.control.exceptions.GameException;

public class ResetCommand extends Command {

	private Level level;

	private long seed;

	public ResetCommand() {
	}

	public ResetCommand(Level level, long seed) {
		this.level = level;
		this.seed = seed;
	}
	
	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}
	
	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}
	
	@Override
	public String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}
	
	@Override
	public String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}
	
	@Override
public boolean execute(GameWorld game)throws GameException{
		
		if(this.level==null || this.seed <= 0) 
			game.reset();
		else 
		{
			game.reset(this.level, this.seed);
		}
		
		return false;
	}
	
	
	@Override
	public Command create(String[] parameters) throws GameException{
		
		
		if(parameters.length==3) {
			
			Level level = Level.valueOfIgnoreCase(parameters[1]);
			long seed = Long.parseLong(parameters[2]);
			this.level = level;
			this.seed = seed;
			
		}
		return this;
	}

}
