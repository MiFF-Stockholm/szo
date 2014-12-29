package gameObjects;

import gfx.Sprite;

public class Door extends Entity {

	private String room;

	public Door(Sprite sprite, String room, float x, float y) {
		super(sprite, x, y);
		this.room = room;
	}

	@Override
	public void collidedWith(Entity other) {
		// TODO Auto-generated method stub

	}

	@Override
	public int doLogic() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

}
