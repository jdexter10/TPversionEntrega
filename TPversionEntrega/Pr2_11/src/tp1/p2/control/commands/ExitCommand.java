package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ExitCommand extends Command {

	/**
	 * Recibe el nombre del comando
	 * 
	 * 
	 * @return Un String con el nombre del comando
	 */
	@Override
	protected String getName() {
		return Messages.COMMAND_EXIT_NAME;
	}
	/**
	 * Recibe el shortCut del comando
	 * 
	 * 
	 * @return Un String con el shortCut del comando
	 */
	@Override
	protected String getShortcut() {
		return Messages.COMMAND_EXIT_SHORTCUT;
	}
	/**
	 * Recibe los detalles del comando
	 * 
	 * 
	 * @return Un String con los detalles del comando
	 */
	@Override
	public String getDetails() {
		return Messages.COMMAND_EXIT_DETAILS;
	}
	/**
	 * Recibe la información del comando
	 * 
	 * 
	 * @return Un String con la información del comando
	 */
	@Override
	public String getHelp() {
		return Messages.COMMAND_EXIT_HELP;
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
		game.playerQuits();
		return new ExecutionResult(true);
	}

}
