package gameObjects;

import java.awt.Rectangle;

public abstract class Collidable extends GfxObject {

	public Collidable(long x, long y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	/** The rectangle used for this entity during collisions resolution */
	private Rectangle me = new Rectangle();

	/** The rectangle used for other entities during collision resolution */
	private Rectangle him = new Rectangle();
	
	/**
	* Notification that this entity collided with another.
	*
	* @param other The entity with which this entity collided.
	*/
	public abstract void collidedWith(Collidable other);
	public abstract boolean collidesWith(Collidable other);
//	public void collidedWith(Entity other) {
//		// TODO Auto-generated method stub
//		
//	}
}
