package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sporty extends Zombie{
	
	private final static int ENDURANCE = 2;
	private final int SPEED = 1;
	
	public Sporty(GameWorld game, int col, int row) 
	{
		super(game,col,row);
	}
	public Sporty() 
	{
		
	}
	/**
	 * Devuelve el nombre del GameObject correspondiente 
	 * 
	 * @return Nombre
	 */
	@Override
	public String getName() 
	{
		return Messages.SPORTY_ZOMBIE_NAME;
	}
	/**
	 * Devuelve el símbolo del GameObject correspondiente 
	 * 
	 * @return <code>Símbolo</code> Símbolo correspondiente al GameObject.
	 */
	@Override
	protected String getSymbol() {
		return Messages.SPORTY_ZOMBIE_SYMBOL;
	}
	@Override 
	public int getSpeed() 
	{
		return SPEED;
	}
	@Override 
	public int getEndurance() 
	{
		return ENDURANCE;
	}
}

