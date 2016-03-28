package cx.mscott.pong.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

class GraphicsCanvas extends JPanel implements ActionListener {
	private static final long serialVersionUID = 5315295731185302027L;

	private static final int BORDER_W = 10;
	private static final int HEADING_H = 40;
	private static final int GRID_SIZE = 20;
	private static final int X_MAX = 40;
	private static final int Y_MAX = 20;
	private static final int BAT_GAP = 2;
	
	/**
	 * How frequently is game loop executed (milliseconds)
	 */
	private static final int GAME_LOOP_SPEED = 20;
	
	/**
	 * Key repeat speed, in multiples of game loop.
	 */
	private static int KEY_SPEED = 4;
	
	/**
	 * Ball speed, in multiples of game loop.
	 */
	private static int BALL_SPEED = 8;
	
	private int gameCycle = 0;

	private final int height;
	private final int width;
	
	Bat bat1;
	Bat bat2;
	Ball ball;
	
	private int player1Score = 0;
	private int player2Score = 0;

	private Timer timer;
	
	private Set<Integer> keys = new HashSet<Integer>();
	
	public GraphicsCanvas() {
		height = HEADING_H + Y_MAX * GRID_SIZE + BORDER_W;
		width = BORDER_W + X_MAX * GRID_SIZE + BORDER_W;
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.black);
		
		bat1 = new Bat();
		bat2 = new Bat();
				
		bat1.setX(BAT_GAP);
		bat2.setX(X_MAX - BAT_GAP - 1);

		ball = new Ball();
		ball.setX(10);
		ball.setY(10);

		addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent e) {
		    	keys.add(e.getKeyCode());
		    }
		    
		    public void keyReleased(KeyEvent e) {
		    	keys.remove(e.getKeyCode());
		    }
        });
		
		timer = new Timer(GAME_LOOP_SPEED, this);
		timer.start(); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gameCycle++;
		
		if (gameCycle % KEY_SPEED == 0) {
		   	if (keys.contains(KeyEvent.VK_Q)) {
		   		moveBatUp(bat1);
		   	}
		   	if (keys.contains(KeyEvent.VK_A)) {
		   		moveBatDown(bat1);
		   	}
		   	if (keys.contains(KeyEvent.VK_P)) {
		   		moveBatUp(bat2);
		   	}
		   	if (keys.contains(KeyEvent.VK_L)) {
		   		moveBatDown(bat2);
		   	}
		}

		if (gameCycle % BALL_SPEED == 0) {
			repaint(ball.getClipRegion(BORDER_W, HEADING_H, GRID_SIZE));
			if (ball.getX() == 0) {
				player2Score++;
				ball.setX(X_MAX / 2);
				repaint(0, 0, width, HEADING_H);
			} else if (ball.getX() == X_MAX - 1) {
				player1Score++;
				ball.setX(X_MAX / 2);
				repaint(0, 0, width, HEADING_H);
			}
			ball.move(bat1, bat2, X_MAX, Y_MAX);
			repaint(ball.getClipRegion(BORDER_W, HEADING_H, GRID_SIZE));
		}
	}
	
	@Override
	public boolean isFocusable() {
		return true;
	}
	
	private void moveBatUp(Bat bat) {
		int y = bat.getY() - 1;
		moveBat(bat, y >= 0 ? y : 0);
	}
	
	private void moveBatDown(Bat bat) {
		int y = bat.getY() + 1;
		moveBat(bat, y < Y_MAX - bat.getHeight() ? y : Y_MAX - bat.getHeight());
	}

    private void moveBat(Bat bat, int y) {
        if (y != bat.getY()) {
        	repaint(bat.getClipRegion(BORDER_W, HEADING_H, GRID_SIZE));
            bat.setY(y);
            repaint(bat.getClipRegion(BORDER_W, HEADING_H, GRID_SIZE));
        }
    }
 
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		g.drawString("" + player1Score, width / 3, 30);
		g.drawString("" + player2Score, width / 3 * 2, 30);
		g.drawString(":", width / 2, 30);
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(BORDER_W-1, HEADING_H-1,
        		width - (BORDER_W * 2) + 1,
        		height - BORDER_W - HEADING_H + 1);

        bat1.paint(g, BORDER_W, HEADING_H, GRID_SIZE);
        bat2.paint(g, BORDER_W, HEADING_H, GRID_SIZE);
        ball.paint(g, BORDER_W, HEADING_H, GRID_SIZE);
	}
}
