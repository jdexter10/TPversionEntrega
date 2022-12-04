package tp1.p2.logic.actions;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;

public class ExplosionAction implements GameAction {

	private int col;

	private int row;

	private int damage;
	
	private boolean attackZombies;

	public ExplosionAction(int col, int row, int damage,boolean attackZombies) {
		this.col = col;
		this.row = row;
		this.damage = damage;
		this.attackZombies = attackZombies;
	}
	/**
	 * Ejecuta la acción de explotar. Hace daño a sus respectivos enemigos en las posiciones cercanas al que ejecuta la acción
	 * 
	 * @param game Game donde se está ejecutando el juego 
	 * 
	 */
	@Override
	public void execute(GameWorld game) {
		GameItem item;
		//Posiciones afectadas de arriba
		for(int i = col - 1; i <= col + 1;i++)
		{
			item = game.getGameItemInPosition(i, row - 1);
		    if(item != null) {  
		    	if(attackZombies)
		    		item.receivePlantAttack(damage);
		    	else
		    		item.receiveZombieAttack(damage);
		    		
		    }
		}
		//Posiciones afectafas a la izquierda y a la derecha
		for(int i = row; i <= row + 1;i++)
		{
			//Línea de la izquierda
			item = game.getGameItemInPosition(col - 1, i);
		    if(item != null) {  
		    	if(attackZombies)
		    		item.receivePlantAttack(damage);
		    	else
		    		item.receiveZombieAttack(damage);
		    }
		  //Línea de la derecha
		    item = game.getGameItemInPosition(col + 1,  i);
		    if(item != null) 
		    {
		    	if(attackZombies)
		    		item.receivePlantAttack(damage);
		    	else
		    		item.receiveZombieAttack(damage);
		    }
		    
		}
		//Posición por debajo de la explosión
		item = game.getGameItemInPosition(col, row + 1);
		if(item != null) 
	    {
			if(attackZombies)
	    		item.receivePlantAttack(damage);
	    	else
	    		item.receiveZombieAttack(damage);
	    }
	}

}
