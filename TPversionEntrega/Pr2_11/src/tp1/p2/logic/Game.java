
package tp1.p2.logic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Sun;
import tp1.p2.view.Messages;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.Level;

public class Game implements GameStatus, GameWorld{

	private long seed;

	private Level level;
	
	private int cycle = 0;

	private GameObjectContainer container;
	
	private boolean playerQuits;
	
	private int suncoins = START_SUNS;
	
	private Random rand;
	
	private ZombiesManager zombiesManager;
		
	private Deque<GameAction> actions;
	
	private SunsManager sunsManager;
	private int score;


	public Game(long seed, Level level) {
		
		this.seed = seed;
		this.level = level;
		

	}
	/**
	 * Resets the game.
	 */
	public void reset() {
		reset(this.level, this.seed);
	}

	/**
	 * Resets the game with the provided level and seed.
	 * 
	 * @param level {@link Level} Used to initialize the game.
	 * @param seed Random seed Used to initialize the game.
	 */
	public void reset(Level level, long seed) {
		System.out.println(String.format(Messages.CONFIGURED_LEVEL, level.name()));
		System.out.println(String.format(Messages.CONFIGURED_SEED, seed));
		suncoins = START_SUNS;
		rand = new Random(seed);
		cycle = 0;
		playerQuits = false;
		inicializar();
	}

	/**
	 * Update general, contiene el de los objetos, listas etc..
	 */
	public void update() {

	    // 1. Execute pending actions
			executePendingActions();

			// 2. Execute game Actions
			zombiesManager.addZombie();
			//suncoins += generatedSuns; revisar esto
			// 3. Game object updates
			container.update();
			sunsManager.update();
			// 4. & 5. Remove dead and execute pending actions
			boolean deadRemoved = true;
			while (deadRemoved || areTherePendingActions()) 
			{
				// Execute pending actions
				executePendingActions();
				// Remove dead 
				deadRemoved = this.container.removeDead();
			}
			this.cycle++;

			// 6. Notify commands that a new cycle started
			Command.newCycle();
	}
	/**
	 * inicializa las respectivas listas.
	 * 
	 */
	public void inicializar() {
		zombiesManager = new ZombiesManager(this,level,rand);
		container = new GameObjectContainer();
		sunsManager = new SunsManager(this,rand);
		actions = new ArrayDeque<>();
	}
	
	
	public void pushAction(GameAction gameAction) {
	    this.actions.addLast(gameAction);
	}

	private void executePendingActions() {
	   while (!this.actions.isEmpty()) {
	      GameAction action = this.actions.removeLast();
	      action.execute(this);
	   }
	}

	private boolean areTherePendingActions() {
	    return this.actions.size() > 0;
	}
	
	@Override
	public boolean execute(Command command) throws GameException 
	{
		boolean print = command.execute(this);
		return print;
	}
	/**
	 * Draw a cell of the game.
	 * 
	 * @param col Column of the cell to draw.
	 * @param row Row of the cell to draw.
	 * 
	 * @return a string that represents the content of the cell (col, row).
	 */
	@Override
	public String positionToString(int col, int row) 
	{
		return container.positionToString(col,row);
	}
	
	@Override
	public boolean isPositionEmpty(int col, int row) 
	{
		return container.isPositionEmpty(col, row);
	}
	
	@Override
	public boolean tryToCatchObject(int col, int row) {
		
		return container.catchObjects(col, row);
	}
	
	@Override
	public boolean addItem(GameObject item) {
		container.add(item);
		return true;
	}
	
	@Override
	public GameItem getGameItemInPosition(int col, int row) 
	{
		GameItem item = container.getGameItemInPosition(col, row); ;
		return item;
	}
	/**
	 * Get available suncoins.
	 * 
	 * @return available suncoins
	 */
	@Override
	public int getSuncoins() 
	{
		return this.suncoins;
	}
	/**
	 * Get the number of generated suns.
	 * 
	 * @return the number of generated suns
	 */
	@Override
	public int getGeneratedSuns() 
	{
		return sunsManager.getGeneratedSuns();
	}
	/**
	 * Get the number of caught suns.
	 * 
	 * @return the number of caught suns
	 */
	@Override
	public int getCaughtSuns() {
		return sunsManager.getCatchedSuns();
	}
	/**
	 * Revisa si el Jugador ha accionado exit() del game.
	 * 
	 * @return {@code true} Si Jugador exit() {@code false} sino.
	 */
	@Override
	public boolean isPlayerQuits() 
	{
		return this.playerQuits;
	}
	
	@Override
	public void playerQuits() 
	{
		this.playerQuits = true;
	}

	/**
	 * Get game cycles.
	 * 
	 * @return the game cycles
	 */
	@Override
	public int getCycle() 
	{
		return this.cycle;
	}
	/**
	 * Get the number of generated suns.
	 * 
	 * @return the number of generated suns
	 */
	@Override
	public int getRemainingZombies() 
	{
		return zombiesManager.getRemainingZombies();
	}
	
	@Override
	public boolean allZombiesDead() 
	{
		if(zombiesManager.getCurrentZombies() == 0 && zombiesManager.getRemainingZombies() == 0) 
		{
			return true;
		}
		return false;
	}
	
	@Override
	public boolean deadPlayer() 
	{
		for(GameObject g: container.getGameObjects()) 
		{
			if(g.getCol()== -1)
				return true;
		}
		return false;
	}
	
	@Override
	public boolean isFinished() 
	{
		boolean finished = false;
		if(deadPlayer() || allZombiesDead() ) 
		{
			finished = true;
		}
		return finished;
	}
	
	@Override
	public void addSun() {
		
		sunsManager.addSun();
		
	}
	
	@Override
	public boolean addNpc(GameObject gameObject) 
	{
		
		if(container.getObjectInPosition(gameObject.getCol(), gameObject.getRow()) == null) 
		{
			container.add(gameObject);
			return true;
		}
		else
		return false;
		
	}
	
	@Override
	public boolean tryToBuy(int cost) {
		boolean ok = false;
		if(cost <= suncoins)
		{
			suncoins -= cost;
			ok = true;
		}
		return ok;
	}
	
	@Override
	public void zombieDied() {
		zombiesManager.zombieDied();
		
	}
	
	@Override
	public GameObject getObjectInPosition(int col, int row) {
		return container.getObjectInPosition(col, row);
	}
	
	public void addCatchedSuns(int value) 
	{
		this.suncoins += value;
		sunsManager.setCatchedSuns(value);
	}
	@Override
	public void setScore(int points) 
	{
		score += points;	
	}
	
	@Override
	public String getLevelName() 
	{
		return this.level.name();
	}
	@Override
	public int getScore() {
		return score;
	}
}