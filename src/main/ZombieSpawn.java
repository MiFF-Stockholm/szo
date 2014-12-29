package main;

import gameObjects.Zombie;

public class ZombieSpawn {
	private class ZombieRunnable implements Runnable {
		private Game game;

		public ZombieRunnable(Game game) {
			this.game = game;
		}

		@Override
		public void run() {
			try {
				while (game.running) {
					// Every 20s
					Thread.sleep(1000 * 20);
					game.addEntityToLayer2(new Zombie(game, x, y));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	private int x, y;

	private Game game;

	public ZombieSpawn(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
	}

	public void start() {

		Thread thread = new Thread(new ZombieRunnable(game));
		thread.start();
	}

}
