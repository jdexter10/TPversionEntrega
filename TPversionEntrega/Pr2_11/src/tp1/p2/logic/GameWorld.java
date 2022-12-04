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

	/**
	 * Añade un sol al juego
	 */
	void addSun();
	/**
	 * Intenta coger un objeto del juego.
	 * 
	 * @param col Posición de la columna del objeto
	 * @param row Posición de la fila del objeto
	 * 
	 * @return <code>true</code> Si se ha encontrado el objeto, <code>false</code>
	 *         otherwise.
	 */
	boolean tryToCatchObject(int col, int row);

	/**
	 * Añade un item al contenedor.
	 * 
	 * @param item Item para añadir.
	 * 
	 * @return <code>true</code> si se ha podido añadir, <code>false</code>
	 *         otherwise.
	 */
	boolean addItem(GameObject item);
	/**
	 * Ejecuta la acción exit, saliendo del juego.
	 * 
	 */
	void playerQuits();
	
	/**
	 * Actualiza el juego, actualizando todos los objetos y datos del juego
	 */
	void update();
	
	/**
	 * Ejecuta el comando introducido.
	 * 
	 * @param command Comando a ejecutar.
	 * 
	 * @return <code>true</code> Si se debe imprimir el juego, <code>false</code>
	 *         otherwise.
	 */
	public boolean execute(Command command);
	/**
	 * Devuelve el item en la posicion introducida.
	 * 
	 * @param col Posición de la columna del objeto
	 * @param row Posición de la fila del objeto.
	 * 
	 * @return <code>GameItem</code> Si hay un item en esa posición, <code>null</code>
	 *         otherwise.
	 */
	GameItem getGameItemInPosition(int col, int row);
	/**
	 * Comprueba si la posición introducida está libre.
	 * 
	 * @param col Posición de la columna a revisar.
	 * @param row Posición de la fila a revisar.
	 * 
	 * @return <code>true</code> Si la posición está libre, <code>false</code>
	 *         otherwise.
	 */
	boolean isPositionEmpty(int col, int row);
	/**
	 * Comprueba si se puede introducir un GameObject. Si es así lo añade al contenedor.
	 * 
	 * @param gameObject Objeto del juego a introducir
	 * 
	 * @return <code>true</code> Si se ha podido introducir, <code>false</code>
	 *         otherwise.
	 */
	public boolean addNpc(GameObject gameObject);
	/**
	 * Comprueba si el precio de la planta a añadir es menor que el numero de monedas que hay en el juego. 
	 * 
	 * @param cost Coste de la planta que se quiere introducir
	 * 
	 * @return <code>true</code> Si se ha podido comprar <code>false</code>
	 *         otherwise.
	 */
	public boolean tryToBuy(int cost);
	/**
	 * Informa de que un zombie ha muerto al zombieManager
	 */
	void zombieDied();
	/**
	 * Resets the game.
	 */
	void reset();
	/**
	 * Resets the game with the provided level and seed.
	 * 
	 * @param level {@link Level} Used to initialize the game.
	 * @param seed Random seed Used to initialize the game.
	 */
	public void reset(Level level, long seed);
	/**
	 * Añade una acción que se ha llevado a cabo por un objeto del juego
	 * 
	 * @param gameAction Acción a añadir.
	 */
	public void pushAction(GameAction gameAction);
	/**
	 * Devuelve el GameObject en la posición introducida
	 * 
	 * @param col Posición de la columna introducida.
	 * @param row Posición de la fila introducida.
	 * 
	 * @return <code>GameObject</code> Si hay un objeto en la posición introducida<code>null</code>
	 *         otherwise.
	 */
	GameObject getObjectInPosition(int col, int row);
	/**
	 * Informa e introduce los soles que se han cogido al sunsManager.
	 * 
	 * @param sunValue Número de soles a introducir.
	 */
	void addCatchedSuns(int sunValue);
}
