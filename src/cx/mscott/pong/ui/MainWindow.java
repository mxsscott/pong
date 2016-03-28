package cx.mscott.pong.ui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 18033280065111757L;

	public MainWindow() {
        System.out.println("Created GUI on EDT? "+
                SwingUtilities.isEventDispatchThread());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new GraphicsCanvas());
		pack();
		setVisible(true);
	}
}
