package gameObjects;

import main.Game;

public class Wall extends Entity {

	public Wall(Game game, String sprite, int x, int y) {
		super(game.getSprite(sprite), x, y);
	}

	@Override
	public void collidedWith(Entity other) {
		other.x -= (this.x - other.x) / Math.abs(this.x - other.x);
		// other.y -= (this.y - other.y) / Math.abs(this.y - other.y);
	}

	@Override
	public int doLogic() {
		// TODO Auto-generated method stub
		return 0;
	}

}
