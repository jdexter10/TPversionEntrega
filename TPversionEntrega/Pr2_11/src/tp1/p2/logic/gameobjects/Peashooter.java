package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Peashooter extends Plant {
	
	private static final int COST = 50;
	private static final int DAMAGE= 1;
	private static final int ENDURANCE = 3;
	private static final int ATTACK_SPEED = 1;
	
	public Peashooter(GameWorld game, int col, int row) 
	{
		super(game,col,row);
		this.lives = ENDURANCE;

	}
	public Peashooter() 
	{
		
	}
	
	//cambiar todos los comentarios del update();
	@Override
	public void update() 
	{
		boolean found = false;
		int i = this.col + 1;//Empieza a buscar desde la posicion siguiente a la que esta la peashooter
		if(isAlive()) 
		{  
			while(!found && i < game.NUM_COLS)//Mientras que no se haya encontrado un zombie al que disparar o mientras que no se hayan revisado todas las columnas de la misma fila
			{
				GameItem item = game.getGameItemInPosition(i, row);//Se comprueba si hay un objeto en la posicion buscada
			    if(item != null && cooldownCycles >= ATTACK_SPEED)//Si lo hay y la peashooter no está en cooldownCycles le dispara
			    {  
			    	found = item.receivePlantAttack(DAMAGE);//Le ataca y si no era un zombie se sigue el bucle
			    	if(found)
			    	{
			    		cooldownCycles = 0;//Si ha disparado a un zombie se resetea su cooldownCycles
			    	}
			    }
			    ++i;//Si no se ha encontrado se comprueba la siguiente fila
		    }
			if(!found)//Si no ha disparado se suma uno de cooldownCycles
			{
				cooldownCycles++;
			}
	    }
		
	}
	@Override
	public boolean catchObject() 
	{
		return false;
	}
	/**
	 * Retorna el SYMBOL del objeto.
	 * 
	 * @return <code>Simbolo</code> Simbolo correspondiente al GameObject.
	 */
	@Override
	protected String getSymbol() {
		return Messages.PEASHOOTER_SYMBOL;
	}
	/**
	 * Retorna el DESCRIPTION del objeto.
	 * 
	 * @return <code>Descripcion</code> Descripcion correspondiente al GameObject.
	 */
	@Override
	public String getDescription() 
	{
		return Messages.PLANT_DESCRIPTION.formatted(getName(), COST,DAMAGE,ENDURANCE);
	}
	/**
	 * Retorna del NAME del objeto.
	 * 
	 * @return Nombre
	 */
	@Override
	public String getName() 
	{
		return Messages.PEASHOOTER_NAME;
	}
	@Override
	public int getCost() {
		return COST;
	}
	

	
	
}