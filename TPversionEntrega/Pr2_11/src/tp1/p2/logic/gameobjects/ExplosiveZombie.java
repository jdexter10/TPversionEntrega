package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class ExplosiveZombie extends Zombie{
	
	private final static int EXPLOSION_DAMAGE = 3;
	
	public ExplosiveZombie(GameWorld game, int col, int row) 
	{
		super(game,col,row);
	}
	public ExplosiveZombie() 
	{
		
	}
	/**
	 * Retorna el NAME del objeto.
	 * 
	 * @return Nombre
	 */
	@Override
	public String getName() 
	{
		return Messages.EXPLOSIVE_ZOMBIE_NAME;
	}
	/**
	 * Devuelve el símbolo del GameObject correspondiente 
	 * 
	 * @return <code>Simbolo</code> Símbolo correspondiente al GameObject.
	 */
	@Override
	protected String getSymbol() {
		return Messages.EXPLOSIVE_ZOMBIE_SYMBOL;
	}
	/**
	 * Lleva a cabo la accion.
	 */
	@Override
	public void delayedAction()
	{
		//Crea una ExplosionAction que afecta a las plantas
		ExplosionAction explosionAction = new ExplosionAction(col,row,EXPLOSION_DAMAGE,false);
		
		//La anade al array de acciones
		game.pushAction(explosionAction);
		
	}

}

