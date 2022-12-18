package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;
import tp1.p2.logic.gameobjects.WallNut;

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
		return false;
	}
	@Override
	public int getCost() {
		return COST;
	}
	/**
	 * Retorna el NAME del objeto
	 * 
	 * @return NAME
	 */
	@Override
	public String getName() {
		return Messages.WALL_NUT_NAME;
	}
	/**
	 * Retorna el SIMBOL del objeto
	 * 
	 * @return SYMBOL
	 */
	@Override
	protected String getSymbol() {
		return Messages.WALL_NUT_SYMBOL;
	}
	/**
	 * Retorna la DESCRIPTION del objeto
	 * 
	 * @return DESCRIPTION
	 */
	@Override
	public String getDescription() {
		return Messages.PLANT_DESCRIPTION.formatted(getName(), COST,DAMAGE,ENDURANCE);
	}
	@Override
	public void update() 
	{
		
	}
	@Override
	public Plant copy(GameWorld game, int col, int row) {
		Plant plant= new WallNut(game, col, row);
		return plant;
	}
}