package tp1.p2.logic.actions;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.GameObject;

public class AddGameItemAction implements GameAction {

	private GameObject go;

	public AddGameItemAction(GameObject gameObject) {
		this.go = gameObject;
	}

	@Override
	public void execute(GameWorld game) {
		game.addItem(go);
	}
}
