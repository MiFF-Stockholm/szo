package gameObjects;

import main.Game;

public class Floor extends Entity {
	private Game game;

	public Floor(Game game, String ref, int x, int y) {
		super(game.getSprite(ref), x, y);
	}

	@Override
	public void collidedWith(Entity other) {
		// no collision needed
	}

	@Override
	public int doLogic() {
		// TODO Auto-generated method stub
		return 0;
	}

}
