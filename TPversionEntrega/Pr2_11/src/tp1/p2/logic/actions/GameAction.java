package tp1.p2.logic.actions;

import tp1.p2.logic.GameWorld;

public interface GameAction {
	/**
	 * Define la acción a ejecutar
	 * 
	 * @param game Game donde se está ejecutando el juego 
	 * 
	 */
	void execute(GameWorld game);
}
