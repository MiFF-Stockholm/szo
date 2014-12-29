package gfx;

public class Window {
	public final static int scrWidth = 1024;
	public final static int scrHeight = 768;
	public final static boolean fullscreen = false;

	public static void CreateWindow() {
		GameDisplay.initscr(scrWidth, scrHeight, fullscreen);
		// Display.setParent(parent)
	}
}
