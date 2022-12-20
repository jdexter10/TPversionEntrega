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
		for (Plant p : AVAILABLE_PLANTS) 
		{
			if(plantName.equals(p.getName()) || plantName.equals(p.getSymbol().toLowerCase()) || plantName.equals(p.getName().toLowerCase()))
				return true;
		}
		return false;
	}

	public static Plant spawnPlant(String plantName, GameWorld game, int col, int row) {
		Plant plant = null;
		if(!isValidPlant(plantName)) return plant;
		else 
		{
			for (Plant p : AVAILABLE_PLANTS) {
				if(plantName.equals(p.getName()) ||plantName.equals(p.getSymbol().toLowerCase()) || plantName.equals(p.getName().toLowerCase()))
					plant = p.copy(game,col,row);
			}
		}
		return plant;
	}

	public static List<Plant> getAvailablePlants() 
	{
		return Collections.unmodifiableList(AVAILABLE_PLANTS);
	}
	/*
	 * Avoid creating instances of this class
	 */
	private PlantFactory() 
	{
		
	}
}
