package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;

public class Credits {
	private static final int screenWidth = 800;
	private Font UIFont1;
	private UnicodeFont uniFont;
	private int y = 600;
	private int crWidth;
	private List<String> creditsList;

	public void start() {
		initGL(800, 600);
		init();

		while (true) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			renderCredits();

			Display.update();
			Display.sync(100);

			if (Display.isCloseRequested()) {
				Display.destroy();
				System.exit(0);
			}
		}
	}

	private void getCredits() {
		creditsList = new ArrayList<String>();
		try {
			BufferedReader fin = new BufferedReader(new FileReader(
					"credits.txt"));
			String line = fin.readLine();
			while (line != null) {
				creditsList.add(line);
				line = fin.readLine();
			}
			fin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initGL(int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glClearDepth(1);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glViewport(0, 0, width, height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	public void init() {
		try {
			UIFont1 = Font.createFont(Font.TRUETYPE_FONT,
					ResourceLoader.getResourceAsStream("impact.ttf"));
			UIFont1 = UIFont1.deriveFont(Font.PLAIN, 22.f);
			uniFont = new org.newdawn.slick.UnicodeFont(UIFont1);
			uniFont.addAsciiGlyphs();
			ColorEffect a = new ColorEffect();
			uniFont.getEffects().add(a); // must be done before loadGlyphs()
			uniFont.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ladda textfil
		crWidth = uniFont.getWidth("Credits");
	}

	private void renderCredits() {
		uniFont.drawString(screenWidth / 2 - crWidth / 2, y, "Credits",
				Color.yellow);
		y -= 1;
		if (y <= 0) {
			y = 600;
		}
	}

	public static void main(String[] args) {
		Credits cr = new Credits();
		cr.start();
	}
	
//	String t = {
//			"Säffle Zombie Outbreak"
//			+"En MiFF-produktion "
//			+"-Producent-"
//			+"Mikael (TITO) Hallenberg"
//			+"-Huvudprogramerare-"
//			+"Ola (Ooe) Rende"
//			+"Johan (Joe) Rende"
//			+"Niklas Volcz"
//			+"-Programmering-"
//			+"Daniel (Lurken) Stjernlöf"
//			+"Jens Berglind"
//			+"-Design-"
//			+"Joel Björkland"
//			+"Janiv Adolfsson Mann"
//			+"Fabian Nordensköld"
//			+"Henrik (Torsten/Julia) Schück"
//			+"Ludvig (phiskas) Lundkvist"
//			+"-Grafik-"
//			+"Katie Sandström"
//			+"Mikael (TITO) Hallenberg"
//			+"Joel (Eska) Björkland"
//			+"Fabian Nordensköld"
//			+"Björn Karlsson"
//			+"Marcus Petersson"
//			+"-Ljud-"
//			+"Ludvig phiskas Lundkvist"
//			+"Julia Persson"
//			+"Samuel (Vespera) Karlin Björk"
//			+"-Story-"
//			+"Robert Jonsson"
//			+"David Schück"
//			+"Katie Sandström"
//			+"Ruben Lif"
//			+"-Röstskådespelare-"
//			+"Pappa Peter - Marcus (Tank) Petersson"
//			+"Mamma Anette - Robert Jonsson"
//			+"Glada Zombies - Julia Persson"
//			+"Mekanikern - Ludvig (phiskas) Lundkvist"
//			+"Skadeljud - Ludvig (phiskas) Lundkvist & Samuel (Vespera) Karlin Björk"
//			+"-Support-"
//			+"Henrik (Torsten/Julia) Schück
//			+"Joar Amnéus
//			+"Alva Bergius
//			+"Rebecca Hellström
//			+"Med ett extra tack till de som gjorde det möjligt för oss att skapa detta, 
//			+"nedanför följer de program som vi använde oss av. Tack till er och 
//			+"kom ihåg att så länge ni gör saker så kommer vi att göra saker. :P
//			+"-Tack till följande programs skapare-"
//			+"Eclipse"
//			+"Inkscape"
//			+"Dropbox"
//			+"Audacity"
//			+"Inga programerare blev skadade under produktionen"
//			+"Men dock drabbade av trötthet, energibrist samt diverse biverkningar "
//			+"som orsakas av stora mängder koffein som intas under kort tid."
//	};

}
