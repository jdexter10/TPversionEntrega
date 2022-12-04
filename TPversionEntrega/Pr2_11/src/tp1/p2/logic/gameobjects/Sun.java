package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sun extends GameObject{
	
	private final static int SUN_LIVES = 10;
	
	private final static int SUN_VALUE = 10;
	public Sun(GameWorld game, int col, int row) 
	{
		super(game,col,row);
		lives = SUN_LIVES;
	}
	
	public String toString() {
		if (isAlive()) {
			return Messages.GAME_OBJECT_STATUS.formatted(getSymbol(),lives) ;
		} else {
			return "";
		}
	}
	/**
	 * Receive a zombie attack.
	 * 
	 * @param damage Received damage.
	 * 
	 * @return <code>true</code> if a plant has been attacked, <code>false</code>
	 *         otherwise.
	 */
	@Override
	public boolean receiveZombieAttack(int damage) {//No le atacan zombies
		return false;
	}
	/**
	 * Receive a plant attack.
	 * 
	 * @param damage Received damage.
	 * 
	 * @return <code>true</code> if a plant has been attacked, <code>false</code>
	 *         otherwise.
	 */
	@Override
	public boolean receivePlantAttack(int damage) {//No le atacan plantas
		return false;
	}

	@Override
	public boolean catchObject() {
		if(!isAlive())
			return false;
		
		game.addCatchedSuns(SUN_VALUE);//Añade el sol cogido al game
		this.lives = 0;//El sol desaparece ya que ha sido cogido
		return true;

	}
	/**
	 * Devuelve el símbolo del GameObject correspondiente 
	 * 
	 * @return <code>Símbolo</code> Símbolo correspondiente al GameObject.
	 */
	@Override
	protected String getSymbol() {
		return Messages.SUN_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.SUN_DESCRIPTION;
	}

	@Override
	public void update() {//Por cada ciclo el sol pierde "tiempo" de vida
		lives--;
	}

	@Override
	public void onEnter() {
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}
	
}
