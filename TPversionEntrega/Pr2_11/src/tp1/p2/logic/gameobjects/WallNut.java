package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class WallNut extends Plant {
	
	private static final int COST = 50;
	private static final int DAMAGE= 0;
	private static final int ENDURANCE = 10;
	
	public WallNut(GameWorld game, int col, int row) 
	{
		super(game,col,row);
		this.lives = ENDURANCE;

	}
	public WallNut() 
	{
		
	}
	@Override
	public boolean catchObject() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int getCost() {
		return COST;
	}
	/**
	 * Devuelve el nombre del GameObject correspondiente 
	 * 
	 * @return Nombre
	 */
	@Override
	public String getName() {
		return Messages.WALL_NUT_NAME;
	}
	/**
	 * Devuelve el símbolo del GameObject correspondiente 
	 * 
	 * @return <code>Símbolo</code> Símbolo correspondiente al GameObject.
	 */
	@Override
	protected String getSymbol() {
		return Messages.WALL_NUT_SYMBOL;
	}
	/**
	 * Devuelve la descripción del GameObject correspondiente 
	 * 
	 * @return <code>Descripción</code> Descripción correspondiente al GameObject.
	 */
	@Override
	public String getDescription() {
		return Messages.PLANT_DESCRIPTION.formatted(getName(), COST,DAMAGE,ENDURANCE);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}