package gfx;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class GameDisplay {

	public static void initGL() {
		// glClearColor(0.5f, 0.5f, 0.5f, 0.0f);

		// glMatrixMode(GL_MODELVIEW);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glViewport(0, 0, Display.getDisplayMode().getWidth(), Display
				.getDisplayMode().getHeight());
		GL11.glOrtho(0.0f, Display.getDisplayMode().getWidth(), Display
				.getDisplayMode().getHeight(), 0.0f, -1.0, 1.0);

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1f);
	}

	public static void initscr(int targetWidth, int targetHeight,
			boolean fullscreen) {

		DisplayMode chosenMode = null;
		//
		try {
			DisplayMode[] modes = Display.getAvailableDisplayModes();
			for (int i = 0; i < modes.length; i++) {
				if ((modes[i].getWidth() == targetWidth)
						&& (modes[i].getHeight() == targetHeight)) {
					chosenMode = modes[i];
					break;
				}
			}
		} catch (LWJGLException e) {
			Sys.alert("Error", "Unable to determine display modes.");
			System.exit(0);
		}

		// at this point if we have no mode there was no appropriate, let the
		// user know
		// and give up...
		if (chosenMode == null) {
			Sys.alert("Error", "Unable to find appropriate display mode.");
			System.exit(0);
		}

		try {
			Display.setDisplayMode(chosenMode);
			Display.setFullscreen(fullscreen);
			Display.setTitle("Säffle Zombie Outbreak!!!!1!");
			Display.create();
			initGL();
		} catch (LWJGLException e) {
			Sys.alert("panik!", "Unable to create display.");
			System.exit(0);
		}

	}
}
