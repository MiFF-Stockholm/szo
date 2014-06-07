package gfx;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glScaled;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2d;

import org.lwjgl.opengl.GL11;

public class RenderSquare {
	public static void renderSquare(int x, int y, int size) {
		glPushMatrix();
		glTranslatef(x, y, 0);
		glScaled(size, size, 0);
		glBegin(GL11.GL_QUADS);
		glColor3d(1, 0, 1);
		glVertex2d(0, 0);
		glVertex2d(0, 1);
		glVertex2d(1, 1);
		glVertex2d(1, 0);
		glEnd();
		glPopMatrix();
	}

	public static void renderSquare(int x, int y, int size, double[] color) {
		glPushMatrix();
		glTranslatef(x, y, 0);
		glScaled(size, size, 0);
		glBegin(GL11.GL_QUADS);
		glColor3d(color[0], color[1], color[2]);
		glVertex2d(0, 0);
		glVertex2d(0, 1);
		glVertex2d(1, 1);
		glVertex2d(1, 0);
		glEnd();
		glPopMatrix();
	}
}