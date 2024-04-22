package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    private static JFrame gameWindow; // Reference to the game window

    public static void startGame() {
        gameWindow = new JFrame();

        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Players vs AI");

        GamePanel gamePanel = new GamePanel();
        gameWindow.add(gamePanel);

        gameWindow.pack();

        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);

        gamePanel.startGameThread();
    }

    public static void closeGameWindow() {
        if (gameWindow != null) {
            gameWindow.dispose();
        }
    }

    public static void mainMenu() {
        SwingUtilities.invokeLater(() -> new BattleshipHomeScreen());
    }

    public static void main(String[] args) {
        mainMenu();
    }
}
/*package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	    // Make startGame method static
	    public static void startGame() {
	        JFrame window = new JFrame();

	        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        window.setResizable(false);
	        window.setTitle("Players vs AI");

	        GamePanel gamePanel = new GamePanel();
	        window.add(gamePanel);

	        window.pack();

	        window.setLocationRelativeTo(null);
	        window.setVisible(true);

	        gamePanel.startGameThread();
	    }

	    //public static void closeGameWindow() {
	        //if (gameWindow != null) {
	            //gameWindow.dispose();
	        //}
	   // }
	    
	    public static void mainMenu() {
	    	SwingUtilities.invokeLater(() -> new BattleshipHomeScreen());
	    }
	    
	    public static void main(String[] args) {
	        mainMenu();
	    }
	}*/
