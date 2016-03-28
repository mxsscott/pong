package cx.mscott.pong;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import cx.mscott.pong.ui.MainWindow;

public class Main {
	@SuppressWarnings("unused")
	private MainWindow mainWindow = null;
		
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
        try {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Pong");

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            SwingUtilities.invokeLater(new Runnable() {
            	public void run() {
        	        mainWindow = new MainWindow();
            	}
            });
     
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

	}
}
