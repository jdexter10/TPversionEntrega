package tp1.p2.control;

import static tp1.p2.view.Messages.error;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.control.commands.AddPlantCheatCommand;
import tp1.p2.control.commands.AddPlantCommand;
import tp1.p2.control.commands.AddZombieCommand;
import tp1.p2.control.commands.CatchCommand;
import tp1.p2.control.commands.ExitCommand;
import tp1.p2.control.commands.HelpCommand;
import tp1.p2.control.commands.ListPlantsCommand;
import tp1.p2.control.commands.ListZombiesCommand;
import tp1.p2.control.commands.NoneCommand;
import tp1.p2.control.commands.ResetCommand;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

/**
 * Represents a user action in the game.
 *
 */
public abstract class Command {

	/* @formatter:off */
	private static final List<Command> AVAILABLE_COMMANDS = Arrays.asList(
			new AddPlantCommand(),
			new ListPlantsCommand(),
			new ResetCommand(),
			new HelpCommand(),
			new ExitCommand(),
			new NoneCommand(),
			new ListZombiesCommand(),
			new AddZombieCommand(),
			new AddPlantCheatCommand(),
			new CatchCommand()
	);
	/* @formatter:on */

	private static Command defaultCommand = new NoneCommand();
	
	public static Command parse(String[] commandWords) {
		if (commandWords.length == 1 && commandWords[0].isEmpty()) {
			return defaultCommand;
		}

		for (Command command : AVAILABLE_COMMANDS) {
			if (command.matchCommand(commandWords[0])) {
				return command;
			}
		}
		System.out.println(error(Messages.UNKNOWN_COMMAND));
		return null;
	}

	public static Iterable<Command> getAvailableCommands() {
		return Collections.unmodifiableList(AVAILABLE_COMMANDS);
	}
	
	/**
	 * Recibe el nombre del comando
	 * 
	 * 
	 * @return Un String con el nombre del comando
	 */
	abstract protected String getName();
	
	/**
	 * Recibe el shortCut del comando
	 * 
	 * 
	 * @return Un String con el shortCut del comando
	 */
	abstract protected String getShortcut();
	/**
	 * Recibe los detalles del comando
	 * 
	 * 
	 * @return Un String con los detalles del comando
	 */
	abstract public String getDetails();
	/**
	 * Recibe la información del comando
	 * 
	 * 
	 * @return Un String con la información del comando
	 */
	abstract public String getHelp();
	/**
	 * Comprueba si es la acción por defecto
	 * 
	 * @return <code>true</code> Si es el commando por deafualt <code>false</code>
	 *         otherwise.
	 */
	public boolean isDefaultAction() {
		return Command.defaultCommand == this;
	}
	/**
	 * Comprueba si el comando introducido por consola coincide con alguno
	 * 
	 * @param token Nombre del comando introducido por consola
	 * 
	 * @return <code>true</code> Si hay un comando que coincide con el introducido <code>false</code>
	 *         otherwise.
	 */
	public boolean matchCommand(String token) {
		String shortcut = getShortcut();
		String name = getName();
		return shortcut.equalsIgnoreCase(token) || name.equalsIgnoreCase(token)
				|| (isDefaultAction() && "".equals(token));
	}

	/**
	 * Execute the command.
	 * 
	 * @param game Where to execute the command.
	 * 
	 * @return {@code true} if game board must be printed {@code false} otherwise.
	 */
	public abstract ExecutionResult execute(GameWorld game);
	/**
	 * Crea el comando según los parámetros introducidos por consola
	 * 
	 * @param parameters Parametros introducidos por consola
	 * 
	 * @return El comando creado
	 */
	public Command create(String[] parameters) {
		return this;
	}
	/**
	 * Informa a los comandos de que ha comenzado un nuevo ciclo y ejecuta la acción que corresponde
	 */
	public static void newCycle() {
		for(Command c : AVAILABLE_COMMANDS) {
			c.newCycleStarted();
		}
	}
	/**
	 * Ejecuta la acción correspondiente al nuevo ciclo
	 */
	protected void newCycleStarted() {
		
	}

}