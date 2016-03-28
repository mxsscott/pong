package cx.mscott.pong.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Bat extends Sprite {
	
	private int xPos = 3;
    private int yPos = 2;
    private int width = 1;
    private int height = 5;

    public void setX(int xPos){ 
        this.xPos = xPos;
    }

    public int getX(){
        return xPos;
    }

    public void setY(int yPos){
        this.yPos = yPos;
    }

    public int getY(){
        return yPos;
    }

    public int getHeight(){
        return height;
    }

    public void paint(Graphics g, int tX, int tY, int tS){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(
        		tX + xPos * tS,
        		tY + yPos * tS,
        		width * tS - 1,
        		height * tS - 1);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(
        		tX + xPos * tS,
        		tY + yPos * tS,
        		width * tS - 1,
        		height * tS - 1);  
    }

	public Rectangle getClipRegion(int tX, int tY, int tS) {
		return new Rectangle(
				tX + xPos * tS,
				tY + yPos * tS,
				width * tS,
				height * tS);
	}

	public boolean collide(int x, int y) {
		if (x != xPos)
			return false;
		if (y < yPos || y >= yPos + height)
			return false;

		return true;
	}
	
	public Point collide(int x, int y, int newX, int newY) {
		int deltaX = newX - x;
		int deltaY = newY - y;

		if (!collide(newX, newY)) {
			// Bounce off the top
			if (x == xPos && y == yPos - 1 && newY == yPos)
				return new Point(deltaX, -deltaY);
				
			// Bounce off the bottom
			if (x == xPos && y == yPos + height && newY == yPos + height - 1)
				return new Point(deltaX, -deltaY);
				
			// We're not near the bat
			return null;
		}

		// We're about to collide, let's see how, and adjust
		deltaX = -deltaX;
		if (y < yPos && newY == yPos)
			deltaY = -deltaY;
		else if (y >= yPos + height && newY == yPos + height -1)
			deltaY = -deltaY;
		
		return new Point(deltaX,deltaY);
	}
	
	public String toString() {
		return "(" + xPos + "," + yPos + "-" + (yPos + height - 1) + ")";
	}
}
