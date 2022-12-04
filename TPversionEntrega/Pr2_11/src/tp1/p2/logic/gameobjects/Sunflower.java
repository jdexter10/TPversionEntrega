package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sunflower extends Plant {
	
	private static final int ADD_SUNS = 10;
	private static final int COST = 10;
	private static final int DAMAGE= 0;
	private static final int ENDURANCE = 3;
	private static final int GENERATE_SUNS = 3;
	
	public Sunflower(GameWorld game, int col, int row) 
	{
		super(game,col,row);
		this.lives = ENDURANCE;
	}

	public Sunflower() 
	{
		
	}	

	
	public void update() 
	{
		if(cooldownCycles == GENERATE_SUNS && isAlive())//Si está viva y no está en cooldownCycles
		{
			game.addSun();//Añade un sol al juego
			cooldownCycles = 0;//Se resetea el cooldownCycles
		}
		cooldownCycles++;
	}
	
	@Override
	public boolean catchObject() 
	{
		return false;
	}
	/**
	 * Devuelve el símbolo del GameObject correspondiente 
	 * 
	 * @return <code>Símbolo</code> Símbolo correspondiente al GameObject.
	 */
	@Override
	protected String getSymbol() {
		return Messages.SUNFLOWER_SYMBOL;
	}
	/**
	 * Devuelve la descripción del GameObject correspondiente 
	 * 
	 * @return <code>Descripción</code> Descripción correspondiente al GameObject.
	 */
	@Override
	public String getDescription() 
	{
		return Messages.PLANT_DESCRIPTION.formatted(getName(), COST,DAMAGE,ENDURANCE);
	}
	/**
	 * Devuelve el nombre del GameObject correspondiente 
	 * 
	 * @return Nombre
	 */
	@Override
	public String getName() 
	{
		return Messages.PEASHOOTER_NAME;
	}

	@Override
	public int getCost() {
		return COST;
	}

}