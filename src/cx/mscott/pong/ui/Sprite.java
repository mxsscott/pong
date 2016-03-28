package cx.mscott.pong.ui;

import java.awt.Graphics;

public abstract class Sprite {
	public abstract int getX();
	public abstract int getY();

	public abstract void paint(Graphics g, int tX, int tY, int tS);
}
