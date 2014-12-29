package gameObjects;

import gfx.Sprite;

public class GunBullet extends Bullet {

	protected GunBullet(Sprite sprite, float x, float y, int dir) {
		super(sprite, x, y, dir);
		// TODO Auto-generated constructor stub
		this.speed = 1000;
		this.ttl = 50000;
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
	public void collidedWith(Entity other) {
		// TODO Auto-generated method stub
		if (other.getClass() == Zombie.class) {
			((Zombie) other).damage(1);
			ttl = 0;
		}
		if ((other.getClass() != Player.class)
				&& (other.getClass() != Corpse.class)) {
			ttl = 0;
		}

	}
}
