package tp1.p2.logic.gameobjects;


import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;
import tp1.p2.logic.gameobjects.Sunflower;

public class Sunflower extends Plant {
	
	private static final int COST = 20;
	private static final int DAMAGE= 0;
	private static final int ENDURANCE = 1;
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
			game.addSun();
			cooldownCycles = 0;
		}
		cooldownCycles++;
	}
	
	@Override
	public boolean catchObject() 
	{
		boolean ok = false;
		return ok;
	}
	
	@Override
	protected String getSymbol() {
		return Messages.SUNFLOWER_SYMBOL;
	}
	
	@Override
	public String getDescription() 
	{
		return Messages.PLANT_DESCRIPTION.formatted(getName(), COST,DAMAGE,ENDURANCE);
	}
	/**
	 * Retorna NAME del objeto
	 * @return NAME
	 */
	@Override
	public String getName() 
	{
		return Messages.SUNFLOWER_NAME;
	}

	@Override
	public int getCost() {
		return COST;
	}

	@Override
	public Plant copy(GameWorld game, int col, int row) {
		Plant plant= new Sunflower(game, col, row);
		return plant;
	}

}