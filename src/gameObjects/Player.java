package gameObjects;

import gfx.Sprite;

public class Player extends Movable {
	long dx;
	long dy;
	private static final int tileSize = 64;
	final long moveSpeed = 5;

	public Player(long x, long y, int playerNumber) {
		super(x, y);
		dx = x;
		dy = y;
		this.spr = new Sprite("player" + playerNumber + ".png");
	}

	public void moveUp() {
		this.dy -= moveSpeed;
	}

	public void moveDown() {
		this.dy += moveSpeed;
	}

	public void moveLeft() {
		this.dx -= moveSpeed;
	}

	public void moveRight() {
		this.dx += moveSpeed;
	}

	@Override
	public void move(long delta) {
		// TODO Auto-generated method stub
		if (x < dx) {
			x+=moveSpeed;
		}
		if (x > dx) {
			x-=moveSpeed;
		}
		if (y < dy) {
			y+=moveSpeed;
		}
		if (y > dy) {
			y-=moveSpeed;
		}
	}

	@Override
	public void collidedWith(Collidable other) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean collidesWith(Collidable other) {
		// TODO Auto-generated method stub
		return false;
	}
}
