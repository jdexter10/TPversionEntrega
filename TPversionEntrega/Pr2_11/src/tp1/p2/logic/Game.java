
package tp1.p2.logic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Sun;
import tp1.p2.view.Messages;
import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.Level;

public class Game implements GameStatus, GameWorld{

	private long seed;

	private Level level;
	
	private int cycle;

	private GameObjectContainer container;
	
	private boolean playerQuits;
	
	private int sunCoins;
	
	private Random rand;
	
	private ZombiesManager zombieManager;
		
	private Deque<GameAction> actions;
	
	private SunsManager sunsManager;


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
		sunCoins = START_SUNS;
		rand = new Random(seed);
		cycle = 0;
		playerQuits = false;
		zombieManager = new ZombiesManager(this,level,rand);
		container = new GameObjectContainer();
		actions = new ArrayDeque<>();
		sunsManager = new SunsManager(this,rand);

	}

	/**
	 * Actualiza el juego, actualizando todos los objetos y datos del juego
	 */
	public void update() {

	    // 1. Execute pending actions
			executePendingActions();

			// 2. Execute game Actions
			
			zombieManager.addZombie();
			//sunCoins += generatedSuns;
			// 3. Game object updates
			container.update();
			sunsManager.update();
			// 4. & 5. Remove dead and execute pending actions
			boolean deadRemoved = true;
			while (deadRemoved || areTherePendingActions()) {
				// Execute pending actions
				executePendingActions();
				// Remove dead 
				deadRemoved = this.container.removeDead();

			}
			//Actualiza el ciclo del juego
			this.cycle++;

			// 6. Notify commands that a new cycle started
			Command.newCycle();
	}
	/**
	 * Añade una acción que se ha llevado a cabo por un objeto del juego
	 * 
	 * @param gameAction Acción a añadir.
	 */
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
	/**
	 * Ejecuta el comando introducido.
	 * 
	 * @param command Comando a ejecutar.
	 * 
	 * @return <code>true</code> Si se debe imprimir el juego, <code>false</code>
	 *         otherwise.
	 */
	@Override
	public boolean execute(Command command) 
	{
		ExecutionResult exRes = command.execute(this);
		if(exRes.errorMessage() != null)
		System.out.println(exRes.errorMessage());
		return exRes.draw();
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
	/**
	 * Comprueba si la posición introducida está libre.
	 * 
	 * @param col Posición de la columna a revisar.
	 * @param row Posición de la fila a revisar.
	 * 
	 * @return <code>true</code> Si la posición está libre, <code>false</code>
	 *         otherwise.
	 */
	@Override
	public boolean isPositionEmpty(int col, int row) 
	{
		return container.isPositionEmpty(col, row);
	}
	/**
	 * Intenta coger un objeto del juego.
	 * 
	 * @param col Posición de la columna del objeto
	 * @param row Posición de la fila del objeto
	 * 
	 * @return <code>true</code> Si se ha encontrado el objeto, <code>false</code>
	 *         otherwise.
	 */
	@Override
	public boolean tryToCatchObject(int col, int row) {
		
		return container.catchObjects(col, row);
	}
	/**
	 * Añade un item al contenedor.
	 * 
	 * @param item Item para añadir.
	 * 
	 * @return <code>true</code> si se ha podido añadir, <code>false</code>
	 *         otherwise.
	 */
	@Override
	public boolean addItem(GameObject item) {
		container.add(item);
		return true;
	}
	/**
	 * Devuelve el item en la posición introducida.
	 * 
	 * @param col Posición de la columna del objeto
	 * @param row Posición de la fila del objeto.
	 * 
	 * @return <code>GameItem</code> Si hay un item en esa posición, <code>null</code>
	 *         otherwise.
	 */
	@Override
	public GameItem getGameItemInPosition(int col, int row) 
	{
		GameItem aux = container.getGameItemInPosition(col, row); ;
		return aux;
	}
	/**
	 * Get available suncoins
	 * 
	 * @return the available suncoins
	 */
	@Override
	public int getSuncoins() 
	{
		return this.sunCoins;
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
	 * Comprueba si el jugador se ha ido del juego
	 * 
	 * @return {@code true} Si el jugador se ha retirado {@code false} otherwise.
	 */
	@Override
	public boolean isPlayerQuits() 
	{
		return this.playerQuits;
	}
	/**
	 * Ejecuta la acción exit, saliendo del juego.
	 * 
	 */
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
		return zombieManager.getRemainingZombies();
	}
	/**
	 * Comprueba si todos los zombies han muerto
	 * 
	 * @return {@code true} Si todos los zombies han muerto {@code false} otherwise.
	 */
	@Override
	public boolean allZombiesDead() 
	{
		if(zombieManager.getCurrentZombies() == 0 && zombieManager.getRemainingZombies() == 0) 
		{
			return true;
		}
		return false;
	}
	/**
	 * Comprueba si el jugador ha muerto
	 * 
	 * @return {@code true} Si los zombies han llegado al final {@code false} otherwise.
	 */
	@Override
	public boolean deadPlayer() 
	{
		for(GameObject o: container.getGameObjects()) 
		{
			if(o.getCol()== -1)
				return true;
		}
		return false;
	}
	/**
	 * Comprueba si el juego se ha terminado
	 * 
	 * @return {@code true} Si el jeugo se ha terminado{@code false} otherwise.
	 */
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
	/**
	 * Añade un sol al juego
	 */
	@Override
	public void addSun() {
		
		sunsManager.addSun();
		
	}
	/**
	 * Comprueba si se puede introducir un GameObject. Si es así lo añade al contenedor.
	 * 
	 * @param gameObject Objeto del juego a introducir
	 * 
	 * @return <code>true</code> Si se ha podido introducir, <code>false</code>
	 *         otherwise.
	 */
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
	/**
	 * Comprueba si el precio de la planta a añadir es menor que el numero de monedas que hay en el juego. 
	 * 
	 * @param cost Coste de la planta que se quiere introducir
	 * 
	 * @return <code>true</code> Si se ha podido comprar <code>false</code>
	 *         otherwise.
	 */
	@Override
	public boolean tryToBuy(int cost) {
		if(cost > sunCoins)
			return false;
		else 
			sunCoins -= cost;
		return true;
	}
	/**
	 * Informa de que un zombie ha muerto al zombieManager
	 */
	@Override
	public void zombieDied() {
		zombieManager.zombieDied();
		
	}
	/**
	 * Devuelve el GameObject en la posición introducida
	 * 
	 * @param col Posición de la columna introducida.
	 * @param row Posición de la fila introducida.
	 * 
	 * @return <code>GameObject</code> Si hay un objeto en la posición introducida<code>null</code>
	 *         otherwise.
	 */
	@Override
	public GameObject getObjectInPosition(int col, int row) {
		return container.getObjectInPosition(col, row);
	}
	/**
	 * Informa e introduce los soles que se han cogido al sunsManager.
	 * 
	 * @param sunValue Número de soles a introducir.
	 */
	public void addCatchedSuns(int value) 
	{
		this.sunCoins += value;
		sunsManager.setCatchedSuns(value);
	}
}