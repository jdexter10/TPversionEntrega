package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;
import tp1.p2.logic.gameobjects.CherryBomb;

public class CherryBomb extends Plant {
	
	private static final int COST = 50;
	private static final int DAMAGE= 10;
	private static final int ENDURANCE = 2;
	private static final int ACTION_DELAY = 2;
	
	public CherryBomb(GameWorld game, int col, int row) 
	{
		super(game,col,row);
		this.lives = ENDURANCE;

	}
	public CherryBomb() 
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
		return Messages.CHERRY_BOMB_NAME;
	}
	
	@Override
	protected String getSymbol() {
		if(cooldownCycles != 2) 
			return Messages.CHERRY_BOMB_SYMBOL;
		//Si la planta va a explotar en el siguiente ciclo la "c" minúscula de su symbol se convertirá en mayúscula
		else
			return "C";
		
	}
	
	@Override
	public String getDescription() {
		return Messages.PLANT_DESCRIPTION.formatted(getName(), COST,DAMAGE,ENDURANCE);
	}

	@Override
	public void update() {
		boolean carryAction = cooldownCycles == ACTION_DELAY;
		if(isAlive() && carryAction) {
			delayedAction();
			lives = 0;
	    }
		else 
		{
			cooldownCycles++;
		}
	}
	/**
	 * Ejecuta la accion que lleva a cabo.
	 */
	@Override
	public void delayedAction() 
	{
		ExplosionAction explosionAction = new ExplosionAction(col,row,DAMAGE,true);
		game.pushAction(explosionAction);
	}
	@Override
	public Plant copy(GameWorld game, int col, int row) {
		Plant plant= new CherryBomb(game, col, row);
		return plant;
	}
}
