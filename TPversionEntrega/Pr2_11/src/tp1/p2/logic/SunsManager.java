package tp1.p2.logic;

import java.util.Random;

import tp1.p2.logic.gameobjects.Sun;

public class SunsManager {

	private static final int COOLDOWN_RANDOM_SUN = 5;

	private GameWorld game;

	private Random rand;

	private int cooldown;
	
	private int catchedSuns;
	
	private int generatedSuns;

	public SunsManager(GameWorld game, Random rand) {
		this.game = game;
		this.rand = rand;
		this.cooldown = COOLDOWN_RANDOM_SUN;
		this.catchedSuns = 0;
		this.generatedSuns = 0;
	}
	/**
	 * Retorna los suns Catched.
	 * 
	 * @return Soles recogidos en game 
	 */
	public int getCatchedSuns() {
		return this.catchedSuns;
	}
	/**
	 * Retorna los suns generated.
	 * 
	 * @return Soles generados en game
	 */
	public int getGeneratedSuns() {
		return this.generatedSuns;
	}
	/**
	 * Actualiza el valor de suns sumado a los recogidos.
	 * 
	 * @param suns += soles cogidos
	 * 
	 */
	public void setCatchedSuns(int suns) 
	{
		this.catchedSuns += suns;
	}
	/**
	 *Actualiza el valor de suns sumado a los generados.
	 * 
	 * @param suns += soles generados
	 * 
	 */
	public void setGeneratedSuns(int suns) 
	{
		this.generatedSuns += suns;
	}
	
	public void update() {
		if (cooldown == 0)
		{
			addSun();
			cooldown = COOLDOWN_RANDOM_SUN;
		} 
		else 
		{
			cooldown--;
		}
	}
	
	private int getRandomInt(int bound) {
		return this.rand.nextInt(bound);
	}
	
	public void addSun() {
		int col = getRandomInt(GameWorld.NUM_COLS);
		int row = getRandomInt(GameWorld.NUM_ROWS);
		game.addItem(new Sun(this.game, col, row));
	}
}
