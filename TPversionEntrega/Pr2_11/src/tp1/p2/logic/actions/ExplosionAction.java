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
	
	@Override
	public void execute(GameWorld game) {
		GameItem item;
		for(int i = col - 1; i <= col + 1;i++)
		{
			item = game.getGameItemInPosition(i, row - 1);
		    if(item != null) 
		    {  
		    	if(attackZombies)
		    	{
		    		item.receivePlantAttack(damage);
		    	}
		    	else
		    	{
		    		item.receiveZombieAttack(damage);
		    	}
		    }
		}
		for(int i = row; i <= row + 1; ++i)
		{
			item = game.getGameItemInPosition(col - 1, i);
		    if(item != null) {  
		    	if(attackZombies)
		    	{
		    		item.receivePlantAttack(damage);
		    	}
		    	else
		    	{
		    		item.receiveZombieAttack(damage);
		    	}	
		    }
		    item = game.getGameItemInPosition(col + 1,  i);
		    if(item != null) 
		    {
		    	if(attackZombies)
		    	{
		    		item.receivePlantAttack(damage);
		    	}
		    	else
		    	{
		    		item.receiveZombieAttack(damage);
		    	}
		    }
		    
		}
		item = game.getGameItemInPosition(col, row + 1);
		if(item != null) 
	    {
			if(attackZombies)
			{
				item.receivePlantAttack(damage);
			}
	    	else
	    	{
	    		item.receiveZombieAttack(damage);
	    	}
	    }
	}

}
