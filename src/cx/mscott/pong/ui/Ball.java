package cx.mscott.pong.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Ball extends Sprite {

	private int xPos = 10;
    private int yPos = 10;
    private int deltaX = 1;
    private int deltaY = 1;

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

    public void paint(Graphics g, int tX, int tY, int tS){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(
        		tX + xPos * tS,
        		tY + yPos * tS,
        		tS - 1,
        		tS - 1);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(
        		tX + xPos * tS,
        		tY + yPos * tS,
        		tS - 1,
        		tS - 1);
    }

	public Rectangle getClipRegion(int tX, int tY, int tS) {
		return new Rectangle(
				tX + xPos * tS,
				tY + yPos * tS,
				tS,
				tS);
	}

	public void move(Bat bat1, Bat bat2, int xMax, int yMax) {
		Point adjustment = bat1.collide(xPos, yPos, xPos + deltaX, yPos + deltaY);
		if (adjustment != null) {
			deltaX = adjustment.x;
			deltaY = adjustment.y;
		}
		
		adjustment = bat2.collide(xPos, yPos, xPos + deltaX, yPos + deltaY);
		if (adjustment != null) {
			deltaX = adjustment.x;
			deltaY = adjustment.y;
		}
		
		if (yPos + deltaY == -1)
			deltaY = 1;
		else if (yPos + deltaY == yMax)
			deltaY = -1;

		if (xPos + deltaX == -1)
			deltaX = 1;
		else if (xPos + deltaX == xMax)
			deltaX = -1;

		xPos += deltaX;
		yPos += deltaY;
	}
}
