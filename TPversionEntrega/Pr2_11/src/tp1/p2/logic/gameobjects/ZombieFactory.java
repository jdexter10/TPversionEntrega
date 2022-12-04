package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.logic.GameWorld;

public class ZombieFactory {

	/* @formatter:off */
	private static final List<Zombie> AVAILABLE_ZOMBIES = Arrays.asList(
		new Zombie(),
		new BucketHead(),
		new Sporty(),
		new ExplosiveZombie()
	);
	/* @formatter:on */

	public static boolean isValidZombie(int zombieIdx) {
		return zombieIdx >= 0 || zombieIdx < AVAILABLE_ZOMBIES.size();
	}

	public static Zombie spawnZombie(int zombieIdx, GameWorld game,int col, int row) 
	{
		Zombie zombie = null;
		//Creará una planta dependiendo del nombre introducido por consola 
		if(zombieIdx == 0) 
		{
			zombie = new Zombie (game, col, row);//Crea un zombie normal
		}
		else if(zombieIdx == 1)
		{
			zombie = new BucketHead (game, col, row);//Crea un Buckethead
		}
		else if(zombieIdx == 2)
		{
			zombie = new Sporty (game, col, row);//Crea un Sporty
		}
		else if(zombieIdx == 3)
		{
			zombie = new ExplosiveZombie (game, col, row);//Crea un ExplosiveZombie
		}
		return zombie;
	}

	public static List<Zombie> getAvailableZombies() {
		return Collections.unmodifiableList(AVAILABLE_ZOMBIES);
	}

	/*
	 * Avoid creating instances of this class
	 */
	private ZombieFactory()
	{
		
	}
}
