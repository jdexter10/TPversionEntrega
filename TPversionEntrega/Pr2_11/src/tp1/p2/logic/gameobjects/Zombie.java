package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Zombie extends GameObject {
		
	private static final int ENDURANCE = 5;
	private static final int DAMAGE = 1;
	private static final int SPEED = 3;	
	
	public Zombie(GameWorld game,int col, int row) 
	{
		super(game,col,row);
		this.lives = getEndurance();
	}
	public Zombie() 
	{
		
	}
		
	
	public String toString() 
	{
		return Messages.GAME_OBJECT_STATUS.formatted(getSymbol(),lives);
	}
	
	public void update() 
	{
		GameItem item = game.getGameItemInPosition(this.col - 1, row);
		if(isAlive())
		{
			 if(item != null)
			 {  
				item.receiveZombieAttack(getDamage());
			
				if((game.isPositionEmpty(this.col - 1,row)) || (!game.isPositionEmpty(this.col - 1,row) && !item.receivePlantAttack(0) && !item.receiveZombieAttack(0)))//Si la posición está libre o si en ella está un sol 
				{
					if(cooldownCycles == getSpeed())
					{
					col--;
					cooldownCycles = 0;
					}
					else
						cooldownCycles++;
				}
			}
			else
			{
				if(cooldownCycles == getSpeed())
				{
				col--;
				cooldownCycles = 0;
				}
				else
				{
					cooldownCycles++;
				}
			}
		}
	   
	}
	/**
	 * Retorna el NAME del objeto.
	 * 
	 * @return NAME
	 */
	public String getName() 
	{
		return Messages.ZOMBIE_NAME;
	}
	@Override
	public boolean catchObject() {
		return false;
	}
	/**
	 * Retorna el SYMBOL del objeto.
	 * 
	 * @return SYMBOL.
	 */
	@Override
	protected String getSymbol() {
		return Messages.ZOMBIE_SYMBOL;
	}
	@Override
	public String getDescription() {
		return Messages.ZOMBIE_DESCRIPTION.formatted(getName(),SPEED, DAMAGE,ENDURANCE);

	}
	
	public void onEnter() {}
	
	@Override
	public void onExit() {//Cuando muere se ejecuta su acción e informa al juego de que ha muerto
		delayedAction();
		game.zombieDied();//
	}
	/**
	 * Receive a zombie attack.
	 * 
	 * @param damage Received damage.
	 * 
	 * @return <code>true</code> if a plant has been attacked, <code>false</code>
	 *         otherwise.
	 */
	@Override
	public boolean receiveZombieAttack(int damage) {
		// TODO Auto-generated method stub
		return false;
	}
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
		this.lives -= damage;
		return true;
	}
	
	public int getSpeed() 
	{
		return SPEED;
	}
	public int getDamage() 
	{
		return DAMAGE;
	}
	public int getEndurance() 
	{
		return ENDURANCE;
	}
}

