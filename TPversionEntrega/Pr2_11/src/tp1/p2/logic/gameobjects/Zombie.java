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
	public Zombie() {
		// TODO Auto-generated constructor stub
	}
		
	
	public String toString() 
	{
		return Messages.GAME_OBJECT_STATUS.formatted(getSymbol(),lives);
	}
	
	public void update() 
	{
		GameItem item = game.getGameItemInPosition(this.col - 1, row);//Coge al objeto que está en la posición en la que puede atacar
		if(isAlive())
		{
			 if(item != null)//Si hay un objeto
			 {  
				item.receiveZombieAttack(getDamage());//Lo ataca
			
				if((game.isPositionEmpty(this.col - 1,row)) || (!game.isPositionEmpty(this.col - 1,row) && !item.receivePlantAttack(0) && !item.receiveZombieAttack(0)))//Si la posición está libre o si en ella está un sol 
				{
					if(coolDown == getSpeed())//Si no está en coolDwon de moverse
					{
					col--;//Se mueve
					coolDown = 0;//Se resetea el coolDown de moverse
					}
					else
						coolDown++;//Si no ataca ni se mueve se actualiza su coolDown
				}
			}
			else//Si hay un objeto enfrente actualiza su coolDown
			{
				if(coolDown == getSpeed())//Si no está en coolDwon de moverse
				{
				col--;//Se mueve
				coolDown = 0;//Se resetea el coolDown de moverse
				}
				else
					coolDown++;//Si no ataca ni se mueve se actualiza su coolDown
			}
		}
	   
	}
	/**
	 * Devuelve el nombre del GameObject correspondiente 
	 * 
	 * @return Nombre
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
	 * Devuelve el símbolo del GameObject correspondiente 
	 * 
	 * @return <code>Símbolo</code> Símbolo correspondiente al GameObject.
	 */
	@Override
	protected String getSymbol() {
		return Messages.ZOMBIE_SYMBOL;
	}
	@Override
	public String getDescription() {
		return Messages.ZOMBIE_DESCRIPTION.formatted(getName(),SPEED, DAMAGE,ENDURANCE);

	}
	/**
	 * Realiza las acciones correspondientes una vez añadido el objeto
	 */
	public void onEnter() {}
	/**
	 * Realiza las acciones correspondientes una vez eliminado el objeto
	 */
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

