package gfx;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2d;

import org.lwjgl.opengl.GL11;

public class Sprite {
	Texture tex;
	int width;
	int height;

	public Sprite(String filename) {
		tex = new Texture(filename);
		width = tex.getW();
		height = tex.getH();
	}

	public void draw(long x, long y) {
		glPushMatrix();
		glTranslated(x, y, 0);
		glBindTexture(GL_TEXTURE_2D, tex.getTexInt());

		glBegin(GL_QUADS);
		glColor3f(1.0f, 1.0f, 1.0f);

		glTexCoord2f(0, 0);
		glVertex2d(width, height);

		glTexCoord2f(0, 1);
		glVertex2d(width, 0);

		glTexCoord2f(1, 1);
		glVertex2d(0, 0);

		glTexCoord2f(1, 0);
		glVertex2d(0, height);
		glEnd();

		glPopMatrix();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void draw(int x, int y, double[] color) {
		glPushMatrix();
		glTranslatef(x, y, 0);

		glBegin(GL11.GL_QUADS);
		glColor3d(color[0], color[1], color[2]);
		glVertex2d(width, height);

		glVertex2d(width, 0);

		glVertex2d(0, 0);

		glVertex2d(0, height);
		glEnd();

		glPopMatrix();
	}

	public void drawM(int x, int y, float rotation) {
		// Funkar inte, skalan flyttar planeterna mot en "mitt".
		glPushMatrix();
		glTranslated(x, y, 0);
		glTranslated(width / 2, height / 2, 0);
		glRotatef(rotation, 0, 0, 1.0f);
		glTranslated(-width / 2, -height / 2, 0);
		glBindTexture(GL_TEXTURE_2D, tex.getTexInt());

		glBegin(GL_QUADS);
		glColor3f(1.0f, 1.0f, 1.0f);

		glTexCoord2f(0, 0);
		glVertex2d(width, height);

		glTexCoord2f(0, 1);
		glVertex2d(width, 0);

		glTexCoord2f(1, 1);
		glVertex2d(0, 0);

		glTexCoord2f(1, 0);
		glVertex2d(0, height);
		glEnd();

		glPopMatrix();
	}

	public void draw(int x, int y, float rotation) {
		glPushMatrix();
		glTranslated(x, y, 0);
		glRotatef(rotation, 0, 0, 1.0f);
		glBindTexture(GL_TEXTURE_2D, tex.getTexInt());

		glBegin(GL_QUADS);
		glColor3f(1.0f, 1.0f, 1.0f);

		glTexCoord2f(0, 0);
		glVertex2d(width, height);

		glTexCoord2f(0, 1);
		glVertex2d(width, 0);

		glTexCoord2f(1, 1);
		glVertex2d(0, 0);

		glTexCoord2f(1, 0);
		glVertex2d(0, height);
		glEnd();

		glPopMatrix();
	}

}
