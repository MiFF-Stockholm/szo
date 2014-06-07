package gameObjects;

import gfx.Sprite;

public abstract class GfxObject{
	long x,y;
	protected Sprite spr;

	public GfxObject(long x, long y) {
		this.x = x;
		this.y = y;
	}

	public void draw() {
		spr.draw(this.x, this.y);
	}

	public long getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public long getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
