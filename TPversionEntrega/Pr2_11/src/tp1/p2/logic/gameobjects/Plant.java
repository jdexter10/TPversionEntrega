package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public abstract class Plant extends GameObject{
	
	
	public Plant(GameWorld game, int col, int row)
	{
		super(game,col,row);
	}
	public Plant() 
	{

	}
		
	public String toString() {
		if (isAlive()) 
		{
			return Messages.GAME_OBJECT_STATUS.formatted(getSymbol(),lives) ;
		} 
		else 
		{
			return "";
		}
	}
	public abstract int getCost();
	/**
	 * Retorna el NAME del objeto
	 * 
	 * @return NAME
	 */
	public abstract String getName(); 
	/**
	 * Receive a plant attack.
	 * 
	 * @param damage Received damage.
	 * 
	 * @return <code>true</code> if a plant has been attacked, <code>false</code>
	 *         otherwise.
	 */
	@Override
	public boolean receivePlantAttack(int damage) {	
		return false;
	}
	
	@Override
	public boolean receiveZombieAttack(int damage) {
		//Recibe daño si es un zombie el que ataca
		receiveDamage(damage);
		return true;
	}
	
	@Override
	public void onEnter()
	{
		
	}
	
	@Override
	public void onExit() 
	{
		
	}
}
	
	
