package tp1.p2.logic;

import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.gameobjects.GameObject;

public interface GameStatus {


	/**
	 * Get game cycles.
	 * 
	 * @return the game cycles
	 */
	int getCycle();

	/**
	 * Get available suncoins
	 * 
	 * @return the available suncoins
	 */
	int getSuncoins();

	/**
	 * Get the number of generated suns.
	 * 
	 * @return the number of generated suns
	 */
	int getRemainingZombies();

	/**
	 * Draw a cell of the game.
	 * 
	 * @param col Column of the cell to draw.
	 * @param row Row of the cell to draw.
	 * 
	 * @return a string that represents the content of the cell (col, row).
	 */
	String positionToString(int col, int row);

	/**
	 * Get the number of generated suns.
	 * 
	 * @return the number of generated suns
	 */
	int getGeneratedSuns();

	/**
	 * Get the number of caught suns.
	 * 
	 * @return the number of caught suns
	 */
	int getCaughtSuns();

	/**
	 * Comprueba si el jugador se ha ido del juego
	 * 
	 * @return {@code true} Si el jugador se ha retirado {@code false} otherwise.
	 */
	boolean isPlayerQuits();
	/**
	 * Comprueba si todos los zombies han muerto
	 * 
	 * @return {@code true} Si todos los zombies han muerto {@code false} otherwise.
	 */
	boolean allZombiesDead();
	/**
	 * Comprueba si el jugador ha muerto
	 * 
	 * @return {@code true} Si los zombies han llegado al final {@code false} otherwise.
	 */
	boolean deadPlayer();
	/**
	 * Comprueba si el juego se ha terminado
	 * 
	 * @return {@code true} Si el jeugo se ha terminado{@code false} otherwise.
	 */
	boolean isFinished();


}
