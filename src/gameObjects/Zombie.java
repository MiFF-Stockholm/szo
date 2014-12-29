package gameObjects;

import main.Game;

public class Zombie extends Entity {

	private static final float range = 50;
	private Game game;
	private int target;
	int health = 10;
	final int movespeed = 50;
	int dir = 0;

	public Zombie(Game game, int x, int y) {
		super(game.getSprite("zombie.png"), x, y);

		this.game = game;
	}

	@Override
	public void collidedWith(Entity other) {
		if (other.getClass() == Player.class) {
			((Player) other).damage(1);
		}

	}

	public void damage(int damage) {
		health -= damage;
		game.soundManager.runZombieHitSound();
		if (health < 0) {
			game.layer2.add(new Corpse(game.getSprite("blod_zombie.png"),
					this.x, this.y));
			game.removeEntity(this);
			System.out.println("DEAAAD (again)");
			health = 10;

		}
	}

	@Override
	public int doLogic() {
		// Om zombien dör, spela en animation och returnera -1.
		int retv = 0;
		if (health < 0) {
			retv = -1;
			game.layer0.add(new Corpse(game.getSprite("zblod.png"), this.x,
					this.y));

		}
		return retv;
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
	public void move(long delta) {
		Player targetPlayer = null;
		if (game.players.size() == 0) {
			setVerticalMovement(0);
			setHorizontalMovement(0);
			return;
		}
		try {
			while (targetPlayer == null) {
				targetPlayer = game.players.get(target);
			}

		} catch (IndexOutOfBoundsException e) {
			target = 0 + (int) (Math.random() * game.players.size());
		}

		if ((this.x < targetPlayer.x + range)
				&& (this.x < targetPlayer.x - range)) {
			setHorizontalMovement(movespeed);
			dir = 3;
		} else if ((this.x > targetPlayer.x + range)
				&& (this.x > targetPlayer.x - range)) {
			setHorizontalMovement(-movespeed);
			dir = 2;
		}

		if ((this.y < targetPlayer.y + range)
				&& (this.y < targetPlayer.y - range)) {
			setVerticalMovement(movespeed);
			dir = 1;
		} else if ((this.y > targetPlayer.y + range)
				&& (this.y > targetPlayer.y - range)) {
			setVerticalMovement(-movespeed);
			dir = 0;
		}
		super.move(delta);
	}
}
