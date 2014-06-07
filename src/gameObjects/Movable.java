package gameObjects;


public abstract class Movable extends Collidable{
	public Movable(long x, long y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public abstract void move(long delta);

}
