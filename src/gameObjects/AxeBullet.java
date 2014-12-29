package gameObjects;

import gfx.Sprite;

public class AxeBullet extends Bullet {
	protected AxeBullet(Sprite sprite, float x, float y, int dir) {
		super(sprite, x, y, dir);
		this.speed = 0;
		this.ttl = 5;

		if (dir == 0) {
			this.setVerticalMovement(-speed);
			this.y -= 64;
		} else if (dir == 1) {
			this.setVerticalMovement(speed);
			this.y += 64;
		} else if (dir == 2) {
			this.setHorizontalMovement(-speed);
			this.x -= 64;
		} else if (dir == 3) {
			this.setHorizontalMovement(speed);
			this.x += 64;
		}
	}

	@Override
	public void collidedWith(Entity other) {
		// TODO Auto-generated method stub
		if (other.getClass() == Zombie.class) {
			((Zombie) other).damage(1);
		}
	}
}
