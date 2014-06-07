package gfx;

public class Window {
	public final static int scrWidth = 800;
	public final static int scrHeight = 600;
	public final static boolean fullscreen = false;

	public static void CreateWindow() {
		GameDisplay.initscr(scrWidth, scrHeight, fullscreen);
		// Display.setParent(parent)
	}
}
