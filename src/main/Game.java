package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameObjects.AxeWeapon;
import gameObjects.Entity;
import gameObjects.GuiBar;
import gameObjects.GunWeapon;
import gameObjects.HealthPickup;
import gameObjects.Player;
import gameObjects.WeaponPickup;
import gameObjects.Zombie;
import gfx.Sprite;
import gfx.TextureLoader;
import gfx.Window;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import util.MapParser;
import util.SoundManager;
import util.Tile;

import static org.lwjgl.opengl.GL11.*;

public class Game {
	boolean running = true;
	private int lastLoopTime;
	private long lastFpsTime;
	int scrX1 = 0;
	int scrY1 = 0;
	int scrX2 = 800;
	int scrY2 = 600;
	private int fps;
	private int mode = 0; // 0 = paus, default !!!! 1 = zombies onscreen
	private int modeChange; // ändras till det nya läget
	private int zombieCounter = 2; // antalet zombies som ska finnas
	private String WINDOW_TITLE = "> Zäffle Zombie Outbreak! <";
	public SoundManager soundManager;
	private List<Entity> removeList;
	private TextureLoader textureLoader;
	public final static int FRAMERATE = 60;
	public long lastTimeZombieSound = 0;
	private Player player1;
	private Player player2;
	private Zombie zombie;
	public List<Player> players;
	public Map<Integer, Tile> textureList;
	private GuiBar gui;
	
	public List<Entity> zombies;

	// Ground and stuff
	public List<Entity> layer0;

	// Items
	public List<Entity> layer1;

	// Players, walls, monsters, projectiles
	public List<Entity> layer2;

	public String nextRoom;

	// TODO test av fonts
	private Font awtFont;
	private TrueTypeFont font;
	private String fontName = "Impact";
	private Font UIFont1;
	private UnicodeFont uniFont;

	// May only be called once (at startup)
	public Game() {
		// Skapar ett fönster att rita grafiken i.
		// Om du gör OpenGL-anrop innan blir det fel.
		Window.CreateWindow();
		init("hallen");
	}

	public static void main(String[] args) {
		Game m = new Game();
		m.gameLoop();
	}

	// May be called several times
	private void init(String roomName) {
		layer0 = new ArrayList<Entity>();
		layer1 = new ArrayList<Entity>();
		layer2 = new ArrayList<Entity>();
		players = new ArrayList<Player>();
		zombies = new ArrayList<Entity>();
		removeList = new ArrayList<Entity>();
		textureList = new HashMap<Integer, Tile>();
		textureLoader = new TextureLoader();
		soundManager = new SoundManager();
		soundManager.backgroundUpdate(mode);
		soundManager.loopSound("sound/music/Distortion.wav");

		player1 = new Player(this, 400, 300, 1);
		layer2.add(player1);
		players.add(player1);

		player2 = new Player(this, 300, 200, 2);
		layer2.add(player2);
		players.add(player2);

		player1.setWeapon(new AxeWeapon(this, player1));
		// player1.setWeapon(new GunWeapon(this, player1));
		layer2.add(new WeaponPickup(this.getSprite("pistol.png"), 123, 123,
				new GunWeapon(this, null)));
		layer2.add(new WeaponPickup(this.getSprite("axe.png"), 423, 123,
				new AxeWeapon(this, null)));
		layer2.add(new HealthPickup(this.getSprite("medkit.png"), 300, 200));

		int i = 0;
		while (i <= zombieCounter) {
			zombies.add(new Zombie(this, 300, 300));
			i++;
		}
		i = 0;
		while (i <= zombieCounter) {
			layer2.add(zombies.get(i));
			i++;
		}
		i = 0;

		MapParser.parseTilesetXML("map/tilelist.xml", this);
		MapParser.parseMap2("map/" + roomName + ".txt", this);

		// lägg till GUI
		loadGUI();

		initText();

	}

	private void loadGUI() {
		gui = new GuiBar(this.getSprite("ui/interfase.png"), 0, 10 * 64);
		layer2.add(gui);
	}

	private void gameLoop() {
		// new ZombieSpawn(this, 100, 100).start();
		lastLoopTime = getTime();
		while (running) {
			// clear screen
			glClear(GL_COLOR_BUFFER_BIT);
			input();
			frameRendering();

			// update window contents
			Display.update();

			if (nextRoom != null) {
				loadLevel(nextRoom);
				nextRoom = null;
			}
		}

		// soundManager.destroy();
		Display.destroy();
	}

	private void loadLevel(String roomName) {
		Player player1 = this.player1;
		Player player2 = this.player2;
		List<Player> players = this.players;

		init(roomName);

		player1.setX(100);
		player1.setY(100);

		this.layer2.removeAll(this.players);

		this.player1 = player1;
		this.player2 = player2;
		this.players = players;
		this.layer2.addAll(players);
	}

	private void initText() {
		try {
			UIFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
					org.newdawn.slick.util.ResourceLoader
							.getResourceAsStream("gfx/font/cambria.ttf"));
			UIFont1 = UIFont1.deriveFont(java.awt.Font.PLAIN, 15.f);
			uniFont = new org.newdawn.slick.UnicodeFont(UIFont1);
			uniFont.addAsciiGlyphs();
			org.newdawn.slick.font.effects.ColorEffect a = new ColorEffect();
			// a.setColor(Color.white);
			uniFont.getEffects().add(a);
			uniFont.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void renderText() {
		String colorString = "16777215"; // Color.decode(colorString)
		// uniFont.drawString(10, 640, "AAAAAAAAAAAAAAAAAAAARGH MOE-ZOMBIES!",
		// Color.green);

		// Player 1 buttons
		uniFont.drawString(101, 714, "Q", Color.green);
		uniFont.drawString(188, 688, "W", Color.green);
		uniFont.drawString(188, 714, "S", Color.green);
		uniFont.drawString(162, 714, "A", Color.green);
		uniFont.drawString(213, 714, "D", Color.green);

		// Player 2 buttons
		uniFont.drawString(101 + 256, 714, "Q", Color.green);
		uniFont.drawString(188 + 256, 688, "W", Color.green);
		uniFont.drawString(188 + 256, 714, "S", Color.green);
		uniFont.drawString(162 + 256, 714, "A", Color.green);
		uniFont.drawString(213 + 256, 714, "D", Color.green);

		// Player 3 buttons
		uniFont.drawString(101 + 256 * 2, 714, "?", Color.green);
		uniFont.drawString(188 + 256 * 2, 688, "?", Color.green);
		uniFont.drawString(188 + 256 * 2, 714, "?", Color.green);
		uniFont.drawString(162 + 256 * 2, 714, "?", Color.green);
		uniFont.drawString(213 + 256 * 2, 714, "?", Color.green);

		// Player 4 buttons
		uniFont.drawString(101 + 256 * 3, 714, "?", Color.green);
		uniFont.drawString(188 + 256 * 3, 688, "?", Color.green);
		uniFont.drawString(188 + 256 * 3, 714, "?", Color.green);
		uniFont.drawString(162 + 256 * 3, 714, "?", Color.green);
		uniFont.drawString(213 + 256 * 3, 714, "?", Color.green);
	}

	private void input() {
		// if escape has been pressed, stop the game
		if ((Display.isCloseRequested() || Keyboard
				.isKeyDown(Keyboard.KEY_ESCAPE))) {
			running = false;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			player1.moveUp();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			player1.moveDown();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			player1.moveLeft();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			player1.moveRight();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			player1.fire();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_I)) {
			player2.moveUp();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_K)) {
			player2.moveDown();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_J)) {
			player2.moveLeft();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_L)) {
			player2.moveRight();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_U)) {
			player2.fire();
		}
	}

	public void frameRendering() {
		// SystemTimer.sleep(lastLoopTime+10-SystemTimer.getTime());
		Display.sync(60);

		// work out how long its been since the last update, this
		// will be used to calculate how far the entities should
		// move this loop
		long delta = getTime() - lastLoopTime;
		lastLoopTime = getTime();
		lastFpsTime += delta;
		fps++;

		// update our FPS counter if a second has passed
		if (lastFpsTime >= 1000) {
			Display.setTitle(WINDOW_TITLE + " (FPS: " + fps + ")");
			lastFpsTime = 0;
			fps = 0;
		}

		// uppdatera GUI här, typ
		// gui.printCurrentInfo();
		// Color.white.bind();
		// font.drawString(50, 640, "THE LIGHTWEIGHT JAVA GAMES LIBRARY",
		// Color.yellow);

		// cycle round asking each entity to move itself
		// TODO cycle through whole list?
		for (Entity mob : layer2) {
			mob.move(delta);
		}

		for (Entity entity : layer0) {
			entity.draw();
		}
		for (Entity entity : layer1) {
			entity.draw();
		}
		for (Entity entity : layer2) {
			entity.draw();
		}

		renderText();

		// brute force collisions, compare every entity against
		// every other entity. If any of them collide notify
		// both entities that the collision has occurred
		for (int p = 0; p < layer2.size(); p++) {
			for (int s = p + 1; s < layer2.size(); s++) {
				Entity me = layer2.get(p);
				Entity him = layer2.get(s);

				if (me.collidesWith(him)) {
					me.collidedWith(him);
					him.collidedWith(me);
				}
			}
		}

		// remove any entity that has been marked for clear up
		layer2.removeAll(removeList);
		players.removeAll(removeList);
		zombies.removeAll(removeList);
		removeList.clear();

		if (zombies.size() > 0)
			modeChange = 1;
		else if (zombies.size() == 0)
			modeChange = 0;

		if (mode != modeChange) {
			soundManager.backgroundUpdate(modeChange);
			mode = modeChange;
			modeChange = 2;
		}

		playSound();

		// if a game event has indicated that game logic should
		// be resolved, cycle round every entity requesting that
		// their persona l logic should be considered.
		// if (logicRequiredThisLoop) {
		for (Entity entity : layer2) {
			if (entity.doLogic() == -1) {
				removeList.add(entity);
			}
		}
	}

	private void playSound() {
		int firstTime = 0;
		int time = getTime();
		if ((time - lastTimeZombieSound) > 2000) {
			if (zombies.size() > 0) {
				soundManager.playZombieSound();
				lastTimeZombieSound = time;
			}
		}

	}

	private int getTime() {
		return (int) ((Sys.getTime() * 1000) / Sys.getTimerResolution());
	}

	public Sprite getSprite(String ref) {
		return new Sprite(textureLoader, ref);
	}

	public void addEntityToLayer2(Entity ent) {
		layer2.add(ent);
	}

	public synchronized void removeEntity(Entity entity) {
		removeList.add(entity);
	}

	private void startScreen() {
		while (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
		}
	}

}
