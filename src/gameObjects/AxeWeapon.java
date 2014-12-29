package gameObjects;

import gfx.Sprite;
import main.Game;

import org.lwjgl.Sys;

public class AxeWeapon extends Weapon {
	private final static int range = 100;
	private final static int rateOfFire = 100;
	private long timeSinceFire = Sys.getTime();
	Sprite bulletSprite;

	public AxeWeapon(Game game, Player own) {
		super(game, range, rateOfFire, own);
		this.owner = own;
		bulletSprite = game.getSprite("axe.png");
	}

	@Override
	public void fire() {
		// Sprite sprite, int x, int y, int speed, int dir
		if ((Sys.getTime() - timeSinceFire) > 300) {
			timeSinceFire = Sys.getTime();
			int dir = owner.getDir();
			AxeBullet blt = new AxeBullet(bulletSprite, owner.x, owner.y, dir);
			game.layer2.add(blt);
		}
	}
}
