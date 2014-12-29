package gameObjects;

import gfx.Sprite;

public class HealthPickup extends Entity {
	boolean destroy = false;

	public HealthPickup(Sprite sprite, int x, int y) {
		super(sprite, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int doLogic() {
		// TODO Auto-generated method stub
		int retval = 0;
		if (destroy == true) {
			retval = -1;
		}
		return retval;
	}

	@Override
	public void collidedWith(Entity other) {
		// TODO Auto-generated method stub
		if (other.getClass() == Player.class) {
			((Player) other).health = 10;
			destroy = true;
		}
	}

}
