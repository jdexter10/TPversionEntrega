package tp1.p2.logic;

import java.util.Random;

import tp1.p2.control.Level;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;

/**
 * Manage zombies in the game.
 *
 */
public class ZombiesManager {

	private GameWorld game;

	private Level level;

	private Random rand;

	private int remainingZombies;
	
	private int zombiesAlived;


	public ZombiesManager(GameWorld game, Level level, Random rand) {
		this.game = game;
		this.level = level;
		this.rand = rand;
		this.zombiesAlived = 0;
		this.remainingZombies = level.getNumberOfZombies();
		this.rand = rand;
	}

	/**
	 * Checks if the game should add (if possible) a zombie to the game.
	 * 
	 * @return <code>true</code> if a zombie should be added to the game.
	 */
	private boolean shouldAddZombie() {
		return rand.nextDouble() < level.getZombieFrequency();
	}
	
	/**
	 * Return a random row within the board limits.
	 * 
	 * @return a random row.
	 */
	private int randomZombieRow() {
		return rand.nextInt(GameWorld.NUM_ROWS);
	}
	/**
	 * Añade un nuevo zombie al campo
	 * 
	 * @return {@code true} Si se ha añadido{@code false} otherwise.
	 */
	public boolean addZombie() {
		int row = randomZombieRow();
		return addZombie(row);
	}
	/**
	 * Comprueba y añade un zombie en la fila propuesta
	 * 
	 * @param row Fila en la que se queire añadir el zombie
	 * 
	 * @return {@code true} Si se ha añadido {@code false} otherwise.
	 */
	public boolean addZombie(int row) {
		boolean canAdd = getRemainingZombies() > 0 && shouldAddZombie()
				&& isPositionEmpty(GameWorld.NUM_COLS, row);

		if(canAdd) 
		{
			//Se obtiene un número random
			int rndm = (int) (Math.random()* 100);
			//Se obtiene un número entre 0 y 3 que será el id del zombie
			int zombieIdx = rndm % 4;
			//Se crea un zombie
			Zombie zombie = ZombieFactory.spawnZombie(zombieIdx,game, Game.NUM_COLS, row);
			//Se añade al juego
			game.addNpc(zombie);
			//Se resta de los zombies que quedan por salir
			remainingZombies--;
			//Se suman a los zombies que han entrado al campo
			zombiesAlived++;

		}
		return canAdd;
	}
	/**
	 * Devuelve los zombies que quedan en el campo
	 * 
	 * 
	 * @return Los zombies que quedan en el campo
	 */
	public int getCurrentZombies() 
	{
		return this.zombiesAlived;
	}
	/**
	 * Comprueba si la posición está vacía desde el game
	 * 
	 * @param col Columna introducida
	 * @param row Fila introducida
	 * 
	 * @return {@code true} Si la posición está vacía{@code false} otherwise.
	 */
	public boolean isPositionEmpty(int col, int row) 
	{
		return game.isPositionEmpty(col, row);
	}
	/**
	 * Devuelve el número de zombies que quedan por salir
	 * 
	 * @return Los zombies que quedan por salir
	 */
	int getRemainingZombies() 
	{
		return this.remainingZombies;
	}
	/**
	 * Baja en 1 el número de zombies en el campo
	 */
	public void zombieDied() 
	{
		zombiesAlived--;
	}
	
}