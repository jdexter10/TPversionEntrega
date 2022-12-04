package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class NoneCommand extends Command {

	public NoneCommand() {

	}
	
	@Override
	protected String getName() {
		return Messages.COMMAND_NONE_NAME;
	}
	
	@Override
	protected String getShortcut() {
		return Messages.COMMAND_NONE_SHORTCUT;
	}
	
	@Override
	public String getDetails() {
		return Messages.COMMAND_NONE_DETAILS;
	}
	
	@Override
	public String getHelp() {
		return Messages.COMMAND_NONE_HELP;
	}
	/**
	 * Execute the command.
	 * 
	 * @param game Where to execute the command.
	 * 
	 * @return {@code true} if game board must be printed {@code false} otherwise.
	 */
	@Override
	public ExecutionResult execute(GameWorld game) {
		ExecutionResult none = new ExecutionResult(true);
		return none;
	}

}
