package tp1.p2.logic.actions;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.GameObject;

public class ExplosionAction implements GameAction {

	private int col;

	private int row;

	private int damage;
	
	private boolean attackZombies;
	
	private final static int POINTS = 20;

	public ExplosionAction(int col, int row, int damage,boolean attackZombies) {
		this.col = col;
		this.row = row;
		this.damage = damage;
		this.attackZombies = attackZombies;
	}
	
	@Override
	public void execute(GameWorld game) {
		GameObject object;
		for(int i = col - 1; i <= col + 1;i++)
		{
			object = game.getObjectInPosition(i, row - 1);
		    if(object != null) {  
		    	if(attackZombies) 
		    	{
		    		object.receivePlantAttack(damage);
		    		if(!object.isAlive()) 
		    		{
		    			game.setScore(POINTS);
		    		}
		    	}
		    	else
		    		object.receiveZombieAttack(damage);
		    		
		    }
		}
		for(int i = row; i <= row + 1;i++)
		{
			object = game.getObjectInPosition(col - 1, i);
		    if(object != null) {  
		    	if(attackZombies){
		    		object.receivePlantAttack(damage);
		    		if(!object.isAlive()) 
		    		{
		    			game.setScore(20);
		    		}
		    	}
		    	else
		    		object.receiveZombieAttack(damage);
		    }
		    object = game.getObjectInPosition(col + 1,  i);
		    if(object != null) 
		    {
		    	if(attackZombies){
		    		object.receivePlantAttack(damage);
		    		if(!object.isAlive()) 
		    		{
		    			game.setScore(20);
		    		}
		    	}
		    	else
		    		object.receiveZombieAttack(damage);
		    }
		    
		}
		object = game.getObjectInPosition(col, row + 1);
		if(object != null) 
	    {
			if(attackZombies){
	    		object.receivePlantAttack(damage);
	    		if(!object.isAlive()) 
	    		{
	    			game.setScore(20);
	    		}
	    	}
	    	else
	    		object.receiveZombieAttack(damage);
	    }
	}

}
