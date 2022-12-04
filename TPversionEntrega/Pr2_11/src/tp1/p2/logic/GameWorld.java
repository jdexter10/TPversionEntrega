package tp1.p2.logic;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.Level;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Zombie;

public interface GameWorld {
	
	public static final int NUM_COLS = 8;
	
	public static final int NUM_ROWS = 4;
	
	public static final int START_SUNS = 50;

	
	void addSun();
	
	boolean tryToCatchObject(int col, int row);

	
	boolean addItem(GameObject item);
	
	void playerQuits();
	
	
	void update();
	
	public boolean execute(Command command);
	
	GameItem getGameItemInPosition(int col, int row);
	
	boolean isPositionEmpty(int col, int row);
	
	public boolean addNpc(GameObject gameObject);
	
	public boolean tryToBuy(int cost);
	
	void zombieDied();
	
	void reset();
	
	public void reset(Level level, long seed);
	
	public void pushAction(GameAction gameAction);
	
	GameObject getObjectInPosition(int col, int row);
	
	void addCatchedSuns(int sunValue);
}
