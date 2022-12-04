package tp1.p2.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.p2.logic.gameobjects.GameObject;

public class GameObjectContainer {

	private List<GameObject> gameObjects;

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}
	
	/**
	 * Devuelve la lista de los objetos del juego
	 * 
	 * @return La lista de objetos
	 */
	public List<GameObject> getGameObjects()
	{
		return gameObjects;
	}
	/**
	 * Añade un objeto y ejecuta su acción de entrada
	 * 
	 * @param gameObject El GameObject que va a ser añadido
	 */
	public void add(GameObject gameObject)
	{
		//Añade el  objeto a la lista
		gameObjects.add(gameObject);
		//El objeto ejecuta su acción de entrada
		gameObject.onEnter();
	}
	/**
	 * Comprueba si la posición está vacía
	 * 
	 * @param col Posición de la columna introducida.
	 * @param row Posición de la fila introducida.
	 * 
	 * @return <code>true</code> Si ls posición está vacía<code>false</code>
	 *         otherwise.
	 */
	public boolean isPositionEmpty(int col, int row) 
	{
		//Recorre todos los objetos de la lista
		for(GameObject o: gameObjects)
		{
			//Si alguno de ellos coincide en posición
			if(o.isInPosition(col, row)){
				return false;
			}
		}
			return true;
	}
	/**
	 * Elimina a los muertos de la lista
	 * 
	 * @return <code>true</code> Si se ha eliminado a un muerto<code>false</code>
	 *         otherwise.
	 */
	public boolean removeDead() 
	{
		boolean dead = false;
		//Recorre toda la lista
		for(int i = 0; i < gameObjects.size();i++) 
		{
			//Si un objeto de la lista no esta vivo
			if(!gameObjects.get(i).isAlive()) 
			{
				//Hace que ejecute su acción de morir
				gameObjects.get(i).onExit();
				//Lo quita de la lista
				gameObjects.remove(i);
				dead = true;
			}
		}

		return dead;
	}
	/**
	 * Devuelve el objeto en la posición introducida
	 * 
	 * @param col Posición de la columna introducida.
	 * @param row Posición de la fila introducida.
	 * 
	 * @return El GameObject en la posición. Si no hay devuelve null
	 */
	public GameObject getObjectInPosition(int col, int row) 
	{
		for(GameObject o: gameObjects) 
		{
			//Si el objeto coincide con la posición y si es una planta o un zombie
			if(o.isInPosition(col,row) && (o.receivePlantAttack(0)||o.receiveZombieAttack(0)))
				return o;
		}
		return null;
	}
	/**
	 * Actualiza todos los objetos de la lista
	 */
	public void update() 
	{
		for(int i = 0; i < gameObjects.size();i++) 
		{
			//Actualiza todos los objetos del juego
			gameObjects.get(i).update();
		}
		//Elimina los muertos de la lista
		removeDead();
	}
	/**
	 * Devuelve el status de los objetos de la lista
	 * 
	 * @param col Posición de la columna introducida.
	 * @param row Posición de la fila introducida.
	 * 
	 * @return El status de los objetos en la posición introducida. si no hay devuelve ""
	 */
	public String positionToString(int col, int row) 
	{
		GameItem o = getObjectInPosition(col,row);//Coge el objeto en la posición si lo hay
		GameItem s = getSunInPosition(col, row);//Coge el sol en la posición si lo hay
		
		if(s != null || o != null ) {
			if(s == null) 
				return o.toString();
			else if(o == null) 
				return s.toString();
			else
				return s.toString()  + o.toString();
				
		}
		return "";
	}
	/**
	 *Comprueba si hay un sol en la posición introducida y si es así lo devuelve
	 * 
	 * @param col Posición de la columna introducida.
	 * @param row Posición de la fila introducida.
	 * 
	 * @return El GameItem en la posición introducida
	 */
	public GameItem getGameItemInPosition(int col, int row) {
		
		
		for(GameObject o: gameObjects) {
			//si coinciden en posición lo devuelve	
			if(o.isInPosition(col, row))
			{	
				return o;	
			}
		}

		return null;
	}
	/**
	 * Comprueba si hay un sol en la posición introducida y si es así lo devuelve
	 * 
	 * @param col Posición de la columna introducida.
	 * @param row Posición de la fila introducida.
	 * 
	 * @return El sol en la posición introducida. Si no hay devuelve null
	 */
	public GameItem getSunInPosition(int col, int row) {
		
		
		for(GameObject o: gameObjects) {
			//Si coinciden en posición y si no es ni una planta ni un zombie			
			if(o.isInPosition(col, row) && !o.receivePlantAttack(0) && !o.receiveZombieAttack(0) ){
				
				return o;
				
			}
			
		}

		return null;
	}
	/**
	 * Ejecuta la acción de catch de todos los objetos de la lista
	 * 
	 * @param col Posición de la columna introducida.
	 * @param row Posición de la fila introducida.
	 * 
	  * @return <code>true</code> Si se ha cogido el objeto<code>false</code>
	 *         otherwise.
	 */
	public boolean catchObjects(int col, int row) 
	{
		int i = 0;
		boolean catched = false;
		while(!catched && i < gameObjects.size()) {
			//Si es un sol y coincide con la posición
			if(gameObjects.get(i).isInPosition(col, row) && !gameObjects.get(i).receivePlantAttack(0) && !gameObjects.get(i).receiveZombieAttack(0)) 
			{
				//Lo coge
				catched = gameObjects.get(i).catchObject();
			}
			i++;
		}
		return catched;
	}

}
