/*
 * Copyright (c) 2002-2010 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package gfx;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

/**
 * Implementation of sprite that uses an OpenGL quad and a texture to render a
 * given image to the screen.
 * 
 * @author Kevin Glass
 * @author Brian Matzon
 */
public class Sprite {
	private Texture texture;

	/** The width in pixels of this sprite */
	private int width;

	/** The height in pixels of this sprite */
	private int height;

	/**
	 * Create a new sprite from a specified image.
	 * 
	 * @param loader
	 *            the texture loader to use
	 * @param ref
	 *            A reference to the image on which this sprite should be based
	 */
	public Sprite(TextureLoader loader, String ref) {
		try {
			texture = loader.getTexture(ref);
			width = texture.getImageWidth();
			height = texture.getImageHeight();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Get the width of this sprite in pixels
	 * 
	 * @return The width of this sprite in pixels
	 */
	public int getWidth() {
		return texture.getImageWidth();
	}

	/**
	 * Get the height of this sprite in pixels
	 * 
	 * @return The height of this sprite in pixels
	 */
	public int getHeight() {
		return texture.getImageHeight();
	}

	/**
	 * Draw the sprite at the specified location
	 * 
	 * @param x
	 *            The x location at which to draw this sprite
	 * @param y
	 *            The y location at which to draw this sprite
	 */
	public void draw(int x, int y) {
		// store the current model matrix
		glPushMatrix();
		// bind to the appropriate texture for this sprite
		texture.bind();
		// translate to the right location and prepare to draw
		glTranslatef(x, y, 0);

		// draw a quad textured to match the sprite
		glBegin(GL_QUADS);
		glColor3f(1.0f, 1.0f, 1.0f);

		glTexCoord2f(1, 0);
		glVertex2d(width, height);

		glTexCoord2f(1, 1);
		glVertex2d(width, 0);

		glTexCoord2f(0, 1);
		glVertex2d(0, 0);

		glTexCoord2f(0, 0);
		glVertex2d(0, height);
		glEnd();

		// restore the model view matrix to prevent contamination
		glPopMatrix();
	}

	public void draw(int x, int y, int rot) {
		// store the current model matrix
		glPushMatrix();
		// bind to the appropriate texture for this sprite
		texture.bind();
		// translate to the right location and prepare to draw
		glTranslatef(x, y, 0);
		if (rot < 90) {
		} else if (rot < 180) {
			glTranslatef(width, 0, 0);
		} else if (rot < 270) {
			glTranslatef(width, height, 0);
		} else if (rot < 360) {
			glTranslatef(0, height, 0);
		}
		GL11.glRotatef(rot, 0.0f, 0.0f, 1.0f);
		// System.out.println(height + " " + width);
		// System.out.println(this.texture.getHeight() + " " +
		// this.texture.getWidth());
		// draw a quad textured to match the sprite
		glBegin(GL_QUADS);
		glColor3f(1.0f, 1.0f, 1.0f);

		glTexCoord2f(1, 0);
		glVertex2d(width, height);

		glTexCoord2f(1, 1);
		glVertex2d(width, 0);

		glTexCoord2f(0, 1);
		glVertex2d(0, 0);

		glTexCoord2f(0, 0);
		glVertex2d(0, height);
		glEnd();

		// restore the model view matrix to prevent contamination
		glPopMatrix();
	}

}