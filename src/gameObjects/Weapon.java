package gameObjects;

import main.Game;

public abstract class Weapon {
	int ammoCount;
	float range;
	int rateOfFire;
	Bullet bulletType;
	Game game;
	Player owner;

	public Weapon(Game game, int range, int rateOfFire, Player owner) {
		this.game = game;
		this.range = range;
		this.rateOfFire = rateOfFire;
		this.owner = owner;
	}

	public void fire() {

	}
}
