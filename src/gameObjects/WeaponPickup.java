package gameObjects;

import gfx.Sprite;

public class WeaponPickup extends Entity {
	Weapon wpn;
	boolean destroy = false;

	public WeaponPickup(Sprite sprite, int x, int y, Weapon wpn) {
		super(sprite, x, y);
		// TODO Auto-generated constructor stub
		this.wpn = wpn;
	}

	@Override
	public void collidedWith(Entity other) {
		// TODO Auto-generated method stub
		if (other.getClass() == Player.class) {
			wpn.owner = (Player) other;
			((Player) other).setWeapon(wpn);
			destroy = true;
		}
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

}
