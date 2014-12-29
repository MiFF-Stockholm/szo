package gameObjects;

import gfx.Sprite;

public class Bullet extends Entity {
	int ttl;
	int count;
	int speed;
	int dir;

	protected Bullet(Sprite sprite, float x, float y, int dir) {
		super(sprite, x, y);
		this.dir = dir;
		if (dir == 0) {
			this.setVerticalMovement(-speed);
		} else if (dir == 1) {
			this.setVerticalMovement(speed);
		} else if (dir == 2) {
			this.setHorizontalMovement(-speed);
		} else if (dir == 3) {
			this.setHorizontalMovement(speed);
		}
	}

	@Override
	public void draw() {
		if (dir == 0) {
			sprite.draw((int) x, (int) y, 0);
		} else if (dir == 1) {
			sprite.draw((int) x, (int) y, 180);
		} else if (dir == 2) {
			sprite.draw((int) x, (int) y, 270);
		} else if (dir == 3) {
			sprite.draw((int) x, (int) y, 90);
		} else {
			sprite.draw((int) x, (int) y);
		}
	}

	@Override
	public void collidedWith(Entity other) {
		// TODO Auto-generated method stub
	}

	@Override
	public int doLogic() {
		int rval = 0;
		if (count > ttl) {
			rval = -1;
		}
		count++;
		return rval;
	}
}
