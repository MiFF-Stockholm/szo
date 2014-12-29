package gameObjects;

import gfx.Sprite;

import org.lwjgl.Sys;

import main.Game;

public class GunWeapon extends Weapon {

	private long timeSinceFire = Sys.getTime();
	Sprite bulletSprite;
	private final static int range = 100;
	private final static int rateOfFire = 100;

	public GunWeapon(Game game, Player own) {
		super(game, range, rateOfFire, own);
		bulletSprite = game.getSprite("projectile.png");
		// bulletSprite = new Sprite[4];
		// bulletSprite[0] = game.getSprite("projectile.png");
		// bulletSprite[0] = game.getSprite("projectile.png");
		// bulletSprite[0] = game.getSprite("projectile.png");
		// bulletSprite[0] = game.getSprite("projectile.png");
	}

	@Override
	public void fire() {
		// Sprite sprite, int x, int y, int speed, int dir
		if ((Sys.getTime() - timeSinceFire) > 300) {
			timeSinceFire = Sys.getTime();
			int dir = owner.getDir();
			GunBullet blt = new GunBullet(bulletSprite, owner.x, owner.y, dir);
			game.layer2.add(blt);
			game.soundManager.playGun();
		}
	}
}
