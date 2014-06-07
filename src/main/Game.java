package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gameObjects.Collidable;
import gameObjects.Movable;
import gameObjects.Player;
import gfx.Window;

import org.lwjgl.LWJGLUtil;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class Game {
	boolean running = true;
	private int lastLoopTime;
	private long lastFpsTime;
	private int fps;
	private String WINDOW_TITLE;
	private Object soundManager;
	private List<Movable> mobs;
	private List<Movable> removeList;
	public final static int FRAMERATE = 60;
	private Player player1;

	private Collidable[][] map;

	//May only be called once (at startup)
	public Game() {
		// Skapar ett fönster att rita grafiken i.
		// Om du gör OpenGL-anrop innan blir det fel.
		Window.CreateWindow();
		init();
	}

	public static void main(String[] args) {
		Game m = new Game();
		m.gameLoop();
	}

	//May be called several times
	private void init() {
		mobs = new ArrayList<Movable>();
		player1 = new Player(0,0,1);
		mobs.add(player1);

		removeList = new ArrayList<Movable>();

		map = new Collidable[20][20];
	}

	private void gameLoop() {
		while (running) {
			// clear screen
			glClear(GL_COLOR_BUFFER_BIT);
			//			glMatrixMode(GL_MODELVIEW);
			//			glLoadIdentity();
			input();
			// let subsystem paint
			frameRendering();

			// update window contents
			Display.update();
		}

		// clean up
		// soundManager.destroy();
		Display.destroy();
	}

	private void input() {
		// TODO Auto-generated method stub
		// if escape has been pressed, stop the game
		if ((Display.isCloseRequested() || Keyboard
				.isKeyDown(Keyboard.KEY_ESCAPE))) {
			running = false;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			player1.moveUp();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			player1.moveDown();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			player1.moveLeft();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			player1.moveRight();
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

		// cycle round asking each entity to move itself
		for (Movable mob : mobs) {
			System.out.println((long) delta);
			mob.move(delta);
		}

		// cycle round drawing all the entities we have in the game
		for (Collidable mob : mobs) {
			mob.draw();
		}
		player1.draw();
		// brute force collisions, compare every entity against
		// every other entity. If any of them collide notify
		// both entities that the collision has occurred
		for (int p = 0; p < mobs.size(); p++) {
			for (int s = p + 1; s < mobs.size(); s++) {
				Collidable me = mobs.get(p);
				Collidable him = mobs.get(s);

				if (me.collidesWith(him)) {
					//me.collidedWith(him);
					//him.collidedWith(me);
				}
			}
		}

		// remove any entity that has been marked for clear up
		mobs.removeAll(removeList);
		removeList.clear();

		//		// if a game event has indicated that game logic should
		//		// be resolved, cycle round every entity requesting that
		//		// their personal logic should be considered.
		//		if (logicRequiredThisLoop) {
		//			for (Mob entity : entities) {
		//				entity.doLogic();
		//			}
		//
		//			logicRequiredThisLoop = false;
		//		}

	}

	private int getTime() {
		// TODO Auto-generated method stub
		return (int) ((Sys.getTime() * 1000) / Sys.getTimerResolution());
//		return (int) Sys.getTime();
	}
}
