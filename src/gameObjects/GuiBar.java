package gameObjects;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import gfx.Sprite;

public class GuiBar extends Entity {

	private static final String fontName = "Impact";
	private Font awtFont;
	private TrueTypeFont font;
	private boolean antiAlias = false;

	@SuppressWarnings("deprecation")
	public GuiBar(Sprite sprite, float x, float y) {
		super(sprite, x, y);
		awtFont = new Font(fontName, Font.PLAIN, 34);
		font = new TrueTypeFont(awtFont, antiAlias);

		// Kod för att ladda in custom fonts
		// try {
		// //InputStream inputStream =
		// ResourceLoader.getResourceAsStream("ZOMBIE.TTF");
		// InputStream inputStream =
		// ResourceLoader.getResourceAsStream("impact.ttf");
		//
		// awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		// awtFont2 = awtFont2.deriveFont(24f); // set font size
		// font2 = new TrueTypeFont(awtFont2, antiAlias);
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	@SuppressWarnings("deprecation")
	public void printCurrentInfo() {
		// gui baren finns mellan y: 640-768 och x: 0-1024
		Color.white.bind(); // varför denna rad???

		font.drawString(645, 20, "Oh my GUI! Oh my GUI!", Color.black);
		font.drawString(10, 10, "Oh my GUI! Oh my GUI!", Color.red);
	}

	@Override
	public void collidedWith(Entity other) {
		// no collisions needed
	}

	@Override
	public int doLogic() {
		return 0;
	}

}
