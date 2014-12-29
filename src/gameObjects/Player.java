package gameObjects;

import main.Game;

public class Player extends Entity {
	long moveSpeed = 100;
	int push = 10;
	public int health = 10;
	int dir = 0;
	Weapon wpn;
	Game game;
	int playerNumber;

	public Player(Game game, int x, int y, int playerNumber) {
		super(game.getSprite("player" + playerNumber + ".png"), x, y);
		this.playerNumber = playerNumber;
		this.game = game;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDir() {
		return dir;
	}

	public void moveUp() {
		this.setVerticalMovement(-moveSpeed);
		dir = 0;
	}

	public void moveDown() {
		this.setVerticalMovement(moveSpeed);
		dir = 1;
	}

	public void moveLeft() {
		this.setHorizontalMovement(-moveSpeed);
		dir = 2;
	}

	public void moveRight() {
		this.setHorizontalMovement(moveSpeed);
		dir = 3;
	}

	public void fire() {
		if (wpn != null) {
			this.wpn.fire();
		}
	}

	@Override
	public void collidedWith(Entity other) {
		if ((other.getClass() == AxeBullet.class)
				|| (other.getClass() == GunBullet.class)
				|| (other.getClass() == Corpse.class)) {
			return;
		}
		if ((other.getClass() == Door.class)) {
			game.nextRoom = ((Door) other).getRoom();
		}
		this.x += (this.x - other.x) / 5;
		this.y += (this.y - other.y) / 5;
	}

	public void damage(int damage) {
		health -= damage;
		game.soundManager.playPlayerSound();
		if (health < 0) {
			System.out.println("DEAAAD");
			game.removeEntity(this);
			game.layer0.add(new Corpse(game.getSprite("blod.png"), this.x,
					this.y));
			game.layer0.add(new Corpse(game.getSprite("player" + playerNumber
					+ "_dead.png"), this.x, this.y));
		}
	}

	@Override
	public void move(long delta) {
		// update the location of the entity based on move speeds
		x += (delta * dx) / 1000;
		y += (delta * dy) / 1000;
		dx = 0;
		dy = 0;
	}

	@Override
	public int doLogic() {
		return 0;
	}

	public void setWeapon(Weapon wpn) {
		this.wpn = wpn;
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
}
