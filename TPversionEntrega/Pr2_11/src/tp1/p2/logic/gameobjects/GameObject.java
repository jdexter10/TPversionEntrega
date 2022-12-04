	package tp1.p2.logic.gameobjects;

import static tp1.p2.view.Messages.status;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
//import tp1.p2.logic.actions.AddGameItemAction;

/**
 * Base class for game non playable character in the game.
 *
 */
public abstract class GameObject implements GameItem {

	protected GameWorld game;
	
	protected int col;
	protected int row;		
	
	protected int lives;
	protected int cooldownCycles;

	GameObject() 
	{
		
	}

	GameObject(GameWorld game, int col, int row) {
		this.game = game;
		this.col = col;
		this.row = row;
		this.cooldownCycles = 0;
	}
	/**
	 * Informa de si el GameObject se encuentra en la posición introducida 
	 * 
	 * @param col Posición de la columna introducida.
	 * @param row Posición de la fila introducida.
	 * 
	 * @return <code>true</code> Si el GameObject se encuentra en la posición introducida<code>false</code>
	 *         otherwise.
	 */
	public boolean isInPosition(int col, int row) {
		return this.col == col && this.row == row;
	}	
	/**
	 * Devuelve la columna de la posición del objeto
	 * @return <code>int</code> Columna a devolver.
	 */
	public int getCol() {	
		return col;
	}
	/**
	 * Devuelve la fila de la posición del objeto
	 * @return <code>int</code> Fila a devolver.
	 */
	public int getRow() {
		return row;
	}	
	/**
	 * Comprueba si el GameObject está vivo
	 * 
	 * @return <code>true</code> Si el GameObject está vivo<code>false</code>
	 *         otherwise.
	 */
	public boolean isAlive() 
	{
		boolean vivo;
		if(this.lives > 0)
		{
			vivo = true;
		}
		else
		{
			vivo = false;
		}
		return vivo;
	}
	/**
	 * Hace daño al GameObject 
	 * 
	 * @param damage Daño que recive el GameObject
	 */
	public void receiveDamage(int damage)
	{
		lives -= damage;
	}
	/**
	 * 
	 * Devuelve el símbolo del GameObject si éste está vivo.
	 * 
	 * @return <code>getSymbol()</code> Si el GameObject está vivo<code>""</code>
	 *         otherwise.
	 */
	public String toString() {
		if (isAlive()) 
		{
			return getSymbol();
		} 
		else 
		{
			return "";
		}
	}	
	/**
	 * Devuelve el símbolo del GameObject correspondiente 
	 * 
	 * @return <code>Símbolo</code> Símbolo correspondiente al GameObject.
	 */
	abstract protected String getSymbol();
	/**
	 * Devuelve la descripción del GameObject correspondiente 
	 * 
	 * @return <code>Descripción</code> Descripción correspondiente al GameObject.
	 */
	abstract public String getDescription();
	/**
	 * Actualiza la información del objeto
	 */
	abstract public void update();
	/**
	 * Realiza las acciones correspondientes una vez añadido el objeto
	 */
	abstract public void onEnter();
	/**
	 * Realiza las acciones correspondientes una vez eliminado el objeto
	 */
	abstract public void onExit();
	/**
	 * Ejecuta la acción que lleva a cabo.
	 */
	public void delayedAction()
	{
		
	}
	
}
