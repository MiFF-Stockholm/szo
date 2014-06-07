package gfx;

import glapp.GLApp;
import glapp.GLImage;

public class Texture {

	private int texint;
	private int w;
	private int h;

	public Texture(String filename) {
		GLImage glImg = new GLImage("gfx/" + filename);
		w = glImg.w;
		h = glImg.h;
		texint = GLApp.makeTexture(glImg);
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public int getTexInt() {
		return texint;
	}
}
