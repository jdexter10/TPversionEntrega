package tp1.p2.logic;

import java.util.Random;

import tp1.p2.control.Level;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.control.exceptions.GameException;

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
	
	public void update() throws GameException
	{
		addZombie();
	}
	
	private boolean shouldAddZombie() {
		return rand.nextDouble() < level.getZombieFrequency();
	}
	
	private int randomZombieRow() {
		return rand.nextInt(GameWorld.NUM_ROWS);
	}
	
	public boolean addZombie() throws GameException {
		int row = randomZombieRow();
		return addZombie(row);
	}
	
	public boolean addZombie(int row) throws GameException {
		boolean canAdd = getRemainingZombies() > 0 && shouldAddZombie()
				&& isPositionEmpty(GameWorld.NUM_COLS, row);

		if(canAdd) 
		{
			
			int rndm = (int) (Math.random()* 100);
			int zombieIdx = rndm % 4;
			Zombie zombie = ZombieFactory.spawnZombie(zombieIdx,game, Game.NUM_COLS, row);
			game.addNpc(zombie);
			remainingZombies--;
			zombiesAlived++;

		}
		return canAdd;
	}
	/**
	 * Retorna los zombies en el print actual.
	 * @return zombiesAlived in position.
	 */
	public int getCurrentZombies() 
	{
		return this.zombiesAlived;
	}
	
	public boolean isPositionEmpty(int col, int row) 
	{
		return game.isPositionEmpty(col, row);
	}
	
	int getRemainingZombies() 
	{
		return this.remainingZombies;
	}
	
	public void zombieDied() 
	{
		zombiesAlived--;
	}
	
}