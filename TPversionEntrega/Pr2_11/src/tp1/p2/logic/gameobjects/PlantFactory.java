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
		Plant aux = null;
		switch(plantName)//Creará una planta dependiendo del nombre introducido por consola 
		{
		case "s":
			aux = new Sunflower(game,col,row);//Añade un girasol
		break;
		case "p":
			aux = new Peashooter(game,col,row);//Añade un Lanza guisantes
		break;
		case "c":
			aux = new CherryBomb(game,col,row);//Añade una cereza
		break;
		case "w":
			aux = new WallNut(game,col,row);//Añade una nuez
			break;
		}
		return aux;	
		//cambiar case por if
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
