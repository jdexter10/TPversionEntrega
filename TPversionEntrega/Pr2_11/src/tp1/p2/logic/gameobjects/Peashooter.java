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
	@Override
	public void update() 
	{
		boolean found = false;
		int i = this.col + 1;//Empieza a buscar desde la posición siguiente a la que está la peashooter
		if(isAlive()) {  
			while(!found && i < game.NUM_COLS)//Mientras que no se haya encontrado un zombie al que disparar o mientras que no se hayan revisado todas las columnas de la misma fila
			{
				GameItem item = game.getGameItemInPosition(i, row);//Se comprueba si hay un objeto en la posición buscada
			    if(item != null && coolDown >= ATTACK_SPEED)//Si lo hay y la peashooter no está en cooldown le dispara
			    {  
			    	found = item.receivePlantAttack(DAMAGE);//Le ataca y si no era un zombie se sigue el bucle
			    	if(found)
			    	coolDown = 0;//Si ha disparado a un zombie se resetea su coolDwon
			    }
			    i++;//Si no se ha encontrado se comprueba la siguiente fila
		    }
				if(!found)//Si no ha disparado se suma uno de coolDown
					coolDown++;
	    }
		
	}
	@Override
	public boolean catchObject() 
	{
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Devuelve el símbolo del GameObject correspondiente 
	 * 
	 * @return <code>Símbolo</code> Símbolo correspondiente al GameObject.
	 */
	@Override
	protected String getSymbol() {
		return Messages.PEASHOOTER_SYMBOL;
	}
	/**
	 * Devuelve la descripción del GameObject correspondiente 
	 * 
	 * @return <code>Descripción</code> Descripción correspondiente al GameObject.
	 */
	@Override
	public String getDescription() 
	{
		return Messages.PLANT_DESCRIPTION.formatted(getName(), COST,DAMAGE,ENDURANCE);
	}
	/**
	 * Devuelve el nombre del GameObject correspondiente 
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