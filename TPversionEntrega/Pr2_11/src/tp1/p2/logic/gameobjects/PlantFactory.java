package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.logic.GameWorld;

public class PlantFactory {

	/* @formatter:off */
	private static final List<Plant> AVAILABLE_PLANTS = Arrays.asList(
		new Sunflower(),
		new Peashooter(),
		new WallNut(),
		new CherryBomb()
	);
	/* @formatter:on */

	public static boolean isValidPlant(String plantName) {
		for (Plant p : AVAILABLE_PLANTS) {
			if(p.getName() == plantName)
				return true;
		}

		return false;
	}

	public static Plant spawnPlant(String plantName, GameWorld game, int col, int row) {
		Plant plant = null;
		if(plantName == "s")
		{
			plant = new Sunflower(game,col,row);
		}
		else if(plantName == "p")
		{
			plant = new Peashooter(game,col,row);
		}
		else if(plantName == "c")
		{
			plant = new CherryBomb(game,col,row);
		}
		else if(plantName == "w")
		{
			plant = new WallNut(game,col,row);
		}
		return plant;	
	}

	public static List<Plant> getAvailablePlants() {
		return Collections.unmodifiableList(AVAILABLE_PLANTS);
	}

	/*
	 * Avoid creating instances of this class
	 */
	private PlantFactory() 
	{
		
	}
}
