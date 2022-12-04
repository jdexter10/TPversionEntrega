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
	 * Devuelve los soles recogidos durante el juego
	 * 
	 * @return los soles recogidos durante el juego 
	 */
	public int getCatchedSuns() {
		return this.catchedSuns;
	}
	/**
	 * Devuelve los soles generados durante el juego
	 * 
	 * @return los soles generados durante el juego 
	 */
	public int getGeneratedSuns() {
		return this.generatedSuns;
	}
	/**
	 * Cambia el valor de los soles cogidos durante el juego
	 * 
	 * @param suns Soles a sumar
	 * 
	 */
	public void setCatchedSuns(int suns) 
	{
		this.catchedSuns += suns;
	}
	/**
	 * Cambia el valor de los soles generados durante el juego
	 * 
	 * @param suns Soles a sumar
	 * 
	 */
	public void setGeneratedSuns(int suns) 
	{
		this.generatedSuns += suns;
	}
	/**
	 * Añade un nuevo sol al juego si la aparición de soles aleatorios no está en cooldown
	 * 
	 */
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
	/**
	 * Devuelve un número aleatorio
	 * 
	 * @param bound nextInt
	 * 
	 * @return Un número random
	 */
	private int getRandomInt(int bound) {
		return this.rand.nextInt(bound);
	}
	/**
	 * Añade un nuevo sol al juego
	 */
	public void addSun() {
		int col = getRandomInt(GameWorld.NUM_COLS);
		int row = getRandomInt(GameWorld.NUM_ROWS);
		game.addItem(new Sun(this.game, col, row));
	}
}
