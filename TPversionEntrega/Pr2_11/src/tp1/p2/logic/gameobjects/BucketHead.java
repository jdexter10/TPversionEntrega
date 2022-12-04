package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class BucketHead extends Zombie{
	
	private final static int SPEED = 4;
	private final static int ENDURANCE = 8;
			
	public BucketHead(GameWorld game, int col, int row) 
	{
		super(game,col,row);
	}
	public BucketHead() 
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
		return Messages.BUCKET_HEAD_ZOMBIE_NAME;
	}
	/**
	 * Devuelve el símbolo del GameObject correspondiente 
	 * 
	 * @return <code>Símbolo</code> Símbolo correspondiente al GameObject.
	 */
	@Override
	protected String getSymbol() {
		return Messages.BUCKET_HEAD_ZOMBIE_SYMBOL;
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

