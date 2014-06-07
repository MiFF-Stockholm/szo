package gfx;

import java.util.HashMap;

public class SpriteManager {
	private static final SpriteManager INSTANCE = new SpriteManager();
	HashMap<String, Sprite> hm;

	private SpriteManager() {
		hm = new HashMap<String, Sprite>();
	}

	public static SpriteManager getInstance() {
		return INSTANCE;
	}

	public Sprite getSprite(String fileName) {
		if (hm.containsKey(fileName) != true) {
			hm.put(fileName, new Sprite(fileName));
		}
		return hm.get(fileName);
	}
}
