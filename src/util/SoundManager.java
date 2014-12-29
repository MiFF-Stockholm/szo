package util;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {

	private static final String SOUND_MUSIC_DISTORTION_WAV = "sound/music/Distortion.wav";
	// gammal: "./sound/music/Distortion.wav"

	/**
	 * @param args
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	File activeBackgroundMusic;
	File activeSound;
	Clip clip;
	Clip sound;

	File zombie;
	File zombieSound;
	Clip zombieClip;
	Clip zombieSoundClip;

	File playerSound;
	Clip playerSoundClip;

	File gunSound;
	Clip gunClip;

	private int firstTime = 0;
	private int soundZombieVersion = 0;
	private int soundPlayerVersion = 0;

	// AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

	// Clip clip = AudioSystem.getClip();

	public SoundManager() {
		// soundFileBackground = new File("distortion.wav")

	}

	public void loopSound(final String inputMusic) {
		firstTime = 1;
		activeBackgroundMusic = new File(inputMusic);
		try {
			clip = AudioSystem.getClip();
			AudioInputStream ais;
			ais = AudioSystem.getAudioInputStream(activeBackgroundMusic);
			clip.open(ais);
			clip.loop(-1);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void backgroundUpdate(int mode) {

		if (firstTime == 0) {
			if (mode == 0) {
				loopSound(SOUND_MUSIC_DISTORTION_WAV);
				System.out.println("start1");
			} else if (mode == 1) {
				loopSound("./sound/music/time to fight.wav");
				loopSound("./sound/music/distortion.wav");
			}
		}
		if (firstTime != 0) {
			if (mode == 0) {
				clip.close();
				loopSound(SOUND_MUSIC_DISTORTION_WAV);
			} else if (mode == 1) {
				clip.close();
				loopSound("./sound/music/distortion.wav");
			}
		}
	}

	public void runZombieHitSound() {
		zombie = new File("./sound/interaction/zombie-hitsound.wav");
		try {
			zombieClip = AudioSystem.getClip();
			AudioInputStream ais;
			ais = AudioSystem.getAudioInputStream(zombie);
			zombieClip.open(ais);
			zombieClip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

	}

	public void playZombieSound() {
		if (soundZombieVersion == 0) {
			zombieSound = new File("./sound/voice/zombie-åh-hjärnor.wav");
			try {
				zombieSoundClip = AudioSystem.getClip();
				AudioInputStream ais;
				ais = AudioSystem.getAudioInputStream(zombieSound);
				zombieSoundClip.open(ais);
				zombieSoundClip.start();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
		if (soundZombieVersion == 1) {
			zombieSound = new File("./sound/voice/zombie-hjärnor.wav");
			try {
				zombieSoundClip = AudioSystem.getClip();
				AudioInputStream ais;
				ais = AudioSystem.getAudioInputStream(zombieSound);
				zombieSoundClip.open(ais);
				zombieSoundClip.start();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
		if (soundZombieVersion == 2) {
			zombieSound = new File("./sound/voice/zombie-omnomnom.wav");
			try {
				zombieSoundClip = AudioSystem.getClip();
				AudioInputStream ais;
				ais = AudioSystem.getAudioInputStream(zombieSound);
				zombieSoundClip.open(ais);
				zombieSoundClip.start();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
		soundZombieVersion++;
		if (soundZombieVersion > 2)
			soundZombieVersion = 0;
	}

	public void playGun() {
		gunSound = new File("./sound/interaction/pistolskott.wav");
		try {
			gunClip = AudioSystem.getClip();
			AudioInputStream ais;
			ais = AudioSystem.getAudioInputStream(gunSound);
			gunClip.open(ais);
			gunClip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void playPlayerSound() {
		if (soundPlayerVersion == 0) {
			playerSound = new File("./sound/voice/player-AJJE.wav");
			try {
				playerSoundClip = AudioSystem.getClip();
				AudioInputStream ais;
				ais = AudioSystem.getAudioInputStream(playerSound);
				playerSoundClip.open(ais);
				playerSoundClip.start();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
		if (soundPlayerVersion == 1) {
			playerSound = new File("./sound/voice/player-aouh.wav");
			try {
				playerSoundClip = AudioSystem.getClip();
				AudioInputStream ais;
				ais = AudioSystem.getAudioInputStream(playerSound);
				playerSoundClip.open(ais);
				playerSoundClip.start();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
		if (soundPlayerVersion == 2) {
			playerSound = new File("./sound/voice/player-ouh.wav");
			try {
				playerSoundClip = AudioSystem.getClip();
				AudioInputStream ais;
				ais = AudioSystem.getAudioInputStream(playerSound);
				playerSoundClip.open(ais);
				playerSoundClip.start();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
		soundPlayerVersion++;
		if (soundPlayerVersion > 2)
			soundPlayerVersion = 0;
	}
}

/*
 * 
 * public static synchronized void playSound(final String url) { new Thread(new
 * Runnable() { // the wrapper thread is unnecessary, unless it blocks on the
 * Clip finishing, see comments public void run() { try { Clip clip =
 * AudioSystem.getClip(); AudioInputStream inputStream =
 * AudioSystem.getAudioInputStream(SoundManager.class.getResourceAsStream("" +
 * url)); clip.open(inputStream); clip.start(); } catch (Exception e) {
 * e.printStackTrace(); } } }).start();
 * 
 * 
 * 
 * 
 * //public static void main(String[] args) { // TODO Auto-generated method stub
 * 
 * playSound("boop.wav");
 * 
 * 
 * AudioClip ac = getAudioClip(getCodeBase(), soundFile); ac.play(); //play once
 * ac.stop(); //stop playing ac.loop(); //play continuously
 * 
 * 
 * 
 * 
 * Clip boop = null; try { load("boop.wav", boop); } catch
 * (UnsupportedAudioFileException e1) { // TODO Auto-generated catch block
 * e1.printStackTrace(); } catch (IOException e1) { block e1.printStackTrace();
 * }
 * 
 * SoundEffect.SoundEffect;
 * 
 * if (boop != null) { boop.loop(5); }
 * 
 * 
 * /* //from a wave File File soundFile = new File("eatfood.wav");
 * AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
 * 
 * Clip clip = AudioSystem.getClip();
 * 
 * clip.open(audioIn); //For small-size file only. Do not use this to open a
 * large file over slow network, as it blocks.
 * 
 * //start() clip.start(); // play once //Loop() clip.loop(0); // repeat none
 * (play once), can be used in place of start(). clip.loop(5); // repeat 5 times
 * (play 6 times) clip.loop(Clip.LOOP_CONTINUOUSLY); // repeat forever
 * 
 * if (clip.isRunning()) clip.stop();
 */

/*
 * static Clip load(String filename, Clip clip) throws
 * UnsupportedAudioFileException, IOException { File soundFile = new
 * File(filename); clip = (Clip) AudioSystem.getAudioInputStream(soundFile); try
 * { AudioSystem.getClip(); } catch (LineUnavailableException e) { // TODO
 * Auto-generated catch block e.printStackTrace(); }
 * 
 * return clip; }
 * 
 * void initialize() throws UnsupportedAudioFileException, IOException //typ
 * ladda alla ljudfiler? { Clip boop = null; load("distortion.wav", boop); }
 * 
 * 
 * 
 * void loop(int times, Clip clip) { clip.loop(times); }
 * 
 * void stop(Clip clip) { if (clip.isRunning()) { clip.stop(); } } }
 */

