package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	// Screen seting
	final int orginalTileSize = 16; // 16 * 16 tiles
	final int scale = 3;
	public final int tileSize = orginalTileSize * scale; //48x48 tiles
	final int maxScreenCol = 26;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // 768 Pixels
	final int screenHight = tileSize * maxScreenRow; // 576 Pixels
	
	
	
	
	int FPS = 2;
	
	
	KeyHandler keyH = new KeyHandler(this);
	PlaceShips pShip = new PlaceShips(this, keyH);
	CPUplay cpuGame = new CPUplay(this, keyH);
	Thread gameThread;
	

	//GAME STATE
	public int gameState = 1;
	public final int playState = 1;
	public final int pauseState = 2;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void setupGame() {
		
		gameState = playState;
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; // 0.016666 Seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
			
			
			
			
			//Update information
			update();
			
			//Update Draw with updated info
			repaint();
			
			
			//Makes the frame rate slow down
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	public void update() {
		
		//can pause the game if p is pressed
		if(gameState == 1){
			pShip.update();
			if(pShip.boatNum == 6){
				gameState = 2;
			}
		}
		else if(gameState == 2){
			//System.out.println("Test");
			if(pShip.boatNum == 6) {
				cpuGame.setup(pShip.map);
				pShip.boatNum++;
			}
			cpuGame.update();
		}
		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		if(gameState == 1)
			pShip.draw(g2);
		else if(gameState == 2)
			cpuGame.draw(g2);
		
		g2.dispose();
	}
	
}
