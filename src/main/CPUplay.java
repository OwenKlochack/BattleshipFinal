package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import tile.Tile;

/**
 * @author Owen Klochack
 *
 */
public class CPUplay{
	GamePanel gp;
	KeyHandler keyH;
	Random rand = new Random();
	
	//Starting position of the boat
	int x = 960, y = 0;
	
	//distance the boat will travel in a frame. very important for calculations do not change this.
	final int speed = 48;
	
	
	//Keeps track of if the boat needs to be rotated
	public boolean rState = false;
	
	//Keeps track of what boat will be placed next
	int boatNum = 1;
	
	//Holds the images
	Tile[] tile;
	//Holds what is on each tile
	int[][] map;
	int[][] cpuMap;
	int[][] over1;
	int[][] over2;
	int[][] over3;
	
	int[] lastX;
	int[] lastY;
	boolean[] end;
	boolean[] shipFound;
	
	
	
	
	/**
	 * constructor
	 */
	public CPUplay(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH= keyH;
		
		//can hold all 30 tiles needs to be raised if more are needed
		tile = new Tile[31];
		//Make the board 10 by 10 tiles
		map = new int[10][10];
		cpuMap = new int[10][10];
		over1 = new int[10][10];
		over2 = new int[10][10];
		over3 = new int[10][10];
		
		lastX = new int[5];
		lastY = new int[5];
		end = new boolean[5];
		shipFound = new boolean[5];
		
		//Fills the board with water
		for (int col = 0; col < 10; col++) {
			for (int row = 0; row < 10; row++) {
				map[col][row] = 0;
				cpuMap[col][row] = 0;	
				over1[col][row] = 0;
				over2[col][row] = 0;
				over3[col][row] = 0;
		}		
		}
		
		for (int col = 0; col < 10; col++) {
			for (int row = 0; row < 10; row++) {
				//System.out.print(map[col][row]);
				
		}	
			//System.out.println();
		}
		
		//Fills the array Tile with images
		getTileImage();
	}
	
	
	public void setup(int[][] inMap) {
		map = inMap;
		randomShips();
	}
	
	public void randomShips() {
		
		int part = 1;
		int r;
		int c;
		int ro;
		
		for (int col = 0; col < 10; col++) {
			for (int row = 0; row < 10; row++) {
				cpuMap[col][row] = 0;
			}	
		}
		
		while(part ==1) {
			r = rand.nextInt(2);
			
			if(r==0) {
				ro = rand.nextInt(9);
				c = rand.nextInt(10);
				
				cpuMap[ro][c] = 3;
				cpuMap[ro+1][c] = 4;
				
			}
			else {
				ro = rand.nextInt(10);
				c = rand.nextInt(9);
				
				cpuMap[ro][c] = 1;
				cpuMap[ro][c+1] = 2;
			}
			part = 2;
		}
		
		while(part ==2 || part ==3) {
			r = rand.nextInt(2);
			
			if(r==0) {
				ro = rand.nextInt(8);
				c = rand.nextInt(10);
				if(cpuMap[ro][c]==0 && cpuMap[ro+1][c]==0 && cpuMap[ro+2][c]==0) {
					cpuMap[ro][c] = 8;
					cpuMap[ro+1][c] = 9;
					cpuMap[ro+2][c] = 10;
					part++;
				}
			}
			else if(r==1) {
				ro = rand.nextInt(10);
				c = rand.nextInt(8);
				if(cpuMap[ro][c]==0 && cpuMap[ro][c+1]==0 && cpuMap[ro][c+2]==0) {
					cpuMap[ro][c] = 5;
					cpuMap[ro][c+1] = 6;
					cpuMap[ro][c+2] = 7;
					part++;
				}
			}
		}
		
		while(part == 4) {
			r = rand.nextInt(2);
			
			if(r==0) {
				ro = rand.nextInt(7);
				c = rand.nextInt(10);
				if(cpuMap[ro][c]==0 && cpuMap[ro+1][c]==0 && cpuMap[ro+2][c]==0 && cpuMap[ro+3][c]==0) {
					cpuMap[ro][c] = 15;
					cpuMap[ro+1][c] = 16;
					cpuMap[ro+2][c] = 17;
					cpuMap[ro+3][c] = 18;
					part++;
				}
			}
			else if(r==1) {
				ro = rand.nextInt(10);
				c = rand.nextInt(7);
				if(cpuMap[ro][c]==0 && cpuMap[ro][c+1]==0 && cpuMap[ro][c+2]==0 && cpuMap[ro][c+3]==0) {
					cpuMap[ro][c] = 11;
					cpuMap[ro][c+1] = 12;
					cpuMap[ro][c+2] = 13;
					cpuMap[ro][c+3] = 14;
					part++;
				}
			}
		}
		
		while(part == 5) {
			r = rand.nextInt(2);
			
			if(r==0) {
				ro = rand.nextInt(6);
				c = rand.nextInt(10);
				if(cpuMap[ro][c]==0 && cpuMap[ro+1][c]==0 && cpuMap[ro+2][c]==0 && cpuMap[ro+3][c]==0 && cpuMap[ro+4][c]==0) {
					cpuMap[ro][c] = 24;
					cpuMap[ro+1][c] = 25;
					cpuMap[ro+2][c] = 26;
					cpuMap[ro+3][c] = 27;
					cpuMap[ro+4][c] = 28;
					part++;
				}
			}
			else if(r==1) {
				ro = rand.nextInt(10);
				c = rand.nextInt(6);
				if(cpuMap[ro][c]==0 && cpuMap[ro][c+1]==0 && cpuMap[ro][c+2]==0 && cpuMap[ro][c+3]==0 && cpuMap[ro][c+4]==0) {
					cpuMap[ro][c] = 19;
					cpuMap[ro][c+1] = 20;
					cpuMap[ro][c+2] = 21;
					cpuMap[ro][c+3] = 22;
					cpuMap[ro][c+4] = 23;
					part++;
				}
			}
		}
		
	}
	
	
	
	
	
	
	
	boolean hunt = false;
	boolean findD = true;
	boolean turn = true;
	boolean done = false;
	int ro;
	int c;

	int rootXtest;
	int rootYtest;
	int lastXtest;
	int lastYtest;
	int hitType;
	String way = "null";
	int hold = 1;
	int count = 0;
	
	
	public boolean seek(int test) {
		
		System.out.println("looking for ship: " + test + " " + hitType);
		
		if(hitType == 2) {
			if (test <= 4)
				return true;
		}
		if(hitType == 3) {
			if ((test <= 10 && test > 4) || test == 0)
				return true;
		}
		if(hitType == 4) {
			if ((test <= 18 && test > 10) || test == 0)
				return true;
		}
		if(hitType == 5) {
			if (test > 18 || test == 0)
				return true;
		}
		
		System.out.println("Ship block");
		return false;
	}
	
	public void cpuHit() {
		System.out.println("Count: " + count);
		System.out.println("Hunt: " + hunt);
		System.out.println("find: " + findD);
		System.out.println("Next way: " + way);
		
		done = true;
		if(hunt){
			done = false;
			if(findD == true) {
				done = false;
				while(true) {
				ro = rand.nextInt(4);
				
				if(ro == 0 && lastXtest+1 != 10 && seek(map[lastXtest+1][lastYtest])) {
					if(map[lastXtest+1][lastYtest] != 29) {
						System.out.println("Gonna go down");
						way = "d";
						break;
					}
				}
				if(ro == 1 && lastXtest-1 != -1 && seek(map[lastXtest-1][lastYtest])) {
					if(map[lastXtest-1][lastYtest] != 29) {
						System.out.println("Gonna go up");
						way = "u";
						break;
					}
				}
				if(ro == 2 && lastYtest+1 != 10 && seek(map[lastXtest][lastYtest+1])) {
					if(map[lastXtest][lastYtest+1] != 29) {
						System.out.println("Gonna go right");
						way = "r";
						break;
					}
				}
				if(ro == 3 && lastYtest-1 != -1 && seek(map[lastXtest][lastYtest-1])) {
					if(map[lastXtest][lastYtest-1] != 29) {
						System.out.println("Gonna go left");
						way = "l";
						break;
					}
				}
				
				
				
				}
				System.out.println("End of find D");
				
				if(way == "u") {
					hold = map[lastXtest-1][lastYtest];
					System.out.println("Place at:" + lastYtest + " " + (lastXtest-1));
					map[lastXtest-1][lastYtest] = 29;
				}
				if(way == "d") {
					hold = map[lastXtest+1][lastYtest];
					System.out.println("Place at:" + lastYtest + " " + (lastXtest+1));
					map[lastXtest+1][lastYtest] = 29;
				}
				if(way == "r") {
					hold = map[lastXtest][lastYtest+1];
					System.out.println("Place at:" + (lastYtest+1) + " " + lastXtest);				
					map[lastXtest][lastYtest+1] = 29;
				}
				if(way == "l") {
					hold = map[lastXtest][lastYtest-1];
					System.out.println("Place at:" + (lastYtest-1) + " " + lastXtest);				
					map[lastXtest][lastYtest-1] = 29;
				}
				
				
				
				if(hold == 0) {
					
				}
				else {
					findD = false;
					System.out.println("I think there is more:" + way);
					if(way == "u") {
						lastXtest--;
					}
					if(way == "d") {
						lastXtest++;
					}
					if(way == "r") {
						lastYtest++;
					}
					if(way == "l") {
						lastYtest--;
					}
				}
			}
			else{
				
				if(way == "u") {
					if(lastXtest-1 < 0) {
						way = "d";
						count++;
						lastXtest = rootXtest;
						lastYtest = rootYtest;
						hold = map[lastXtest+1][lastYtest];
						System.out.println("Place at:" + (lastXtest+1) + " " + lastYtest);
						if(map[lastXtest+1][lastYtest] == 29) {
							done = true;					
							}
						map[lastXtest+1][lastYtest] = 29;
						lastXtest++;
					}
					else {
					hold = map[lastXtest-1][lastYtest];
					System.out.println("Place at:" + (lastXtest-1) + " " + lastYtest);
					if(map[lastXtest-1][lastYtest] == 29) {
						done = true;					
						}
					map[lastXtest-1][lastYtest] = 29;
					lastXtest--;
					}
				}
				else if(way == "d") {
					if(lastXtest+1 > 9) {
						way = "d";
						count++;
						lastXtest = rootXtest;
						lastYtest = rootYtest;
						hold = map[lastXtest-1][lastYtest];
						System.out.println("Place at:" + (lastXtest-1) + " " + lastYtest);
						if(map[lastXtest-1][lastYtest] == 29) {
							done = true;					
							}
						map[lastXtest-1][lastYtest] = 29;
						lastXtest--;
					}
					else {
					hold = map[lastXtest+1][lastYtest];
					System.out.println("Place at:" + (lastXtest+1) + " " + lastYtest);
					if(map[lastXtest+1][lastYtest] == 29) {
						done = true;					
						}
					map[lastXtest+1][lastYtest] = 29;
					lastXtest++;
					}
				}
				else if(way == "r") {
					if(lastYtest+1 > 9) {
						way = "l";
						count++;
						lastXtest = rootXtest;
						lastYtest = rootYtest;
						hold = map[lastXtest][lastYtest-1];
						System.out.println("Place at:" + lastXtest + " " + (lastYtest-1));
						if(map[lastXtest][lastYtest-1] == 29) {
							done = true;					
							}
						map[lastXtest][lastYtest-1] = 29;
						lastYtest--;
					}
					else {
						hold = map[lastXtest][lastYtest+1];
						System.out.println("Place at:" + lastXtest + " " + (lastYtest+1));
						if(map[lastXtest][lastYtest+1] == 29) {
							done = true;					
							}
						map[lastXtest][lastYtest+1] = 29;
						lastYtest++;
					}
				}
				else if(way == "l") {
					if(lastYtest-1 < 0) {
						way = "r";
						count++;
						lastXtest = rootXtest;
						lastYtest = rootYtest;
						hold = map[lastXtest][lastYtest+1];
						System.out.println("Place at:" + lastXtest + " " + (lastYtest+1));
						if(map[lastXtest][lastYtest+1] == 29) {
							done = true;					
							}
						map[lastXtest][lastYtest+1] = 29;
						lastYtest++;
					}
					else {
						hold = map[lastXtest][lastYtest-1];
						System.out.println("Place at:" + lastXtest + " " + (lastYtest-1));
						if(map[lastXtest][lastYtest-1] == 29) {
							done = true;					
							}
						map[lastXtest][lastYtest-1] = 29;
						lastYtest--;
					}
				}
				
				//hold = map[lastXtest][lastYtest];
				
				if(hold == 0 || hold == 29) {
					if(way == "d"){
						if(rootXtest-1 < 0) {
							count = 2;
						}
						else {
						System.out.println("turn up");
						count++;
						lastXtest = rootXtest;
						lastYtest = rootYtest;
						if(map[lastXtest-1][lastYtest] == 29){
							System.out.println("No way");
							count++;
						}
						way = "u";
						}
					}
					else if(way == "u"){
						if(rootXtest+1 > 9) {
							count = 2;
						}
						else {
						System.out.println("turn down");
						count++;
						lastXtest = rootXtest;
						lastYtest = rootYtest;
						if(map[lastXtest+1][lastYtest] == 29){
							System.out.println("No way");
							count++;
						}
						way = "d";
						}
					}
					else if(way == "r"){
						if(rootYtest-1 < 0) {
							count = 2;
						}
						else {
							System.out.println("turn left");
							count++;
							lastXtest = rootXtest;
							lastYtest = rootYtest;
							if(map[lastXtest][lastYtest-1] == 29){
								System.out.println("No way");
								count++;
							}
							way = "l";
						}
						
					}
					else if(way == "l"){
						if(rootYtest+1 > 9) {
							count = 2;
						}
						else {
						System.out.println("turn right");
						count++;
						lastXtest = rootXtest;
						lastYtest = rootYtest;
						if(map[lastXtest][lastYtest+1] == 29){
							System.out.println("No way");
							count++;
						}
						way = "r";
						}
					}
				}
				
				if(hold == 29 && count == 1) {
					count++;
				}
				
				turn = true;
				
				if(count >= 2) {
					System.out.println("Done with hunt");
					hunt = false;
					count = 0;
					findD = true;
					way = null;
				}
				
			}
			}
		
		if(hunt == false && done == true) {
			System.out.println("R guess");
			ro = rand.nextInt(10);
			c = rand.nextInt(10);
			
			while(over1[ro][c] != 0 || map[ro][c] == 29) {
				System.out.println("R block at: " + ro + c);
				System.out.println("over1 e: " + over1[ro][c]);
				System.out.println("map e: " + map[ro][c]);
				ro = rand.nextInt(10);
				c = rand.nextInt(10);
			}
			System.out.println("over1 s: " + over1[ro][c]);
			System.out.println("map s: " + map[ro][c]);
			
			System.out.println("R at: " + ro + c);
			
			over1[ro][c] = 1;
			
			if(map[ro][c] != 0) {
				
				if(map[ro][c] <= 4)
					hitType = 2;
				else if(map[ro][c] <= 10)
					hitType = 3;
				else if(map[ro][c] <= 18)
					hitType = 4;
				else
					hitType = 5;
				
				map[ro][c] = 29;
				lastXtest = ro;
				lastYtest = c;
				rootXtest = ro;
				rootYtest = c;
				hunt = true;
			}
			else {
				map[ro][c] = 29;
			}
			
		
		}
		
	}
		
	
	
	
	
	/**
	 * This class sets up all the images of the boats to be used later.
	 */
	public void getTileImage() {
		try {
			
			
			//Water images
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water1.png"));
			
			//2 boat images
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/2Length1.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/2Length2.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/2Length1R.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/2Length2R.png"));
			
			//3 boat images
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/3Length1.png"));
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/3Length2.png"));
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/3Length3.png"));
			
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/3Length1R.png"));
			
			tile[9] = new Tile();
			tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/3Length2R.png"));
			
			tile[10] = new Tile();
			tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/3Length3R.png"));
			
			//4 boat images
			tile[11] = new Tile();
			tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/4Length1.png"));
			
			tile[12] = new Tile();
			tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/4Length2.png"));
			
			tile[13] = new Tile();
			tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/4Length3.png"));
			
			tile[14] = new Tile();
			tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/4Length4.png"));
			
			tile[15] = new Tile();
			tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/4Length1R.png"));
			
			tile[16] = new Tile();
			tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/4Length2R.png"));
			
			tile[17] = new Tile();
			tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/4Length3R.png"));
			
			tile[18] = new Tile();
			tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/4Length4R.png"));
			
			//5 boat images
			tile[19] = new Tile();
			tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/5Length1.png"));
			
			tile[20] = new Tile();
			tile[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/5Length2.png"));
			
			tile[21] = new Tile();
			tile[21].image = ImageIO.read(getClass().getResourceAsStream("/tiles/5Length3.png"));
			
			tile[22] = new Tile();
			tile[22].image = ImageIO.read(getClass().getResourceAsStream("/tiles/5Length4.png"));
			
			tile[23] = new Tile();
			tile[23].image = ImageIO.read(getClass().getResourceAsStream("/tiles/5Length5.png"));
			
			tile[24] = new Tile();
			tile[24].image = ImageIO.read(getClass().getResourceAsStream("/tiles/5Length1R.png"));
			
			tile[25] = new Tile();
			tile[25].image = ImageIO.read(getClass().getResourceAsStream("/tiles/5Length2R.png"));
			
			tile[26] = new Tile();
			tile[26].image = ImageIO.read(getClass().getResourceAsStream("/tiles/5Length3R.png"));
			
			tile[27] = new Tile();
			tile[27].image = ImageIO.read(getClass().getResourceAsStream("/tiles/5Length4R.png"));
			
			tile[28] = new Tile();
			tile[28].image = ImageIO.read(getClass().getResourceAsStream("/tiles/5Length5R.png"));

			//Red X
			tile[29] = new Tile();
			tile[29].image = ImageIO.read(getClass().getResourceAsStream("/tiles/RedX.png"));
			
			tile[30] = new Tile();
			tile[30].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Wood1.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Used to see what keys are pressed and to update accordingly
	 */
	public void update(){
		
		//All of these are for movement
			if(keyH.upPressed == true) {
				y -= speed;
			}
			else if(keyH.downPressed == true) {
				y += speed;
			}
			if(keyH.leftPressed == true) {
				x -= speed;
			}
			if(keyH.rightPressed == true) {
			x += speed;
			}
			
			if(x < 768) {
				x=768;
			}
			
			if(y < 0) {
				y=0;
			}
			
			
			if(x > 1200) {
				x=1200;
			}
			
			if(y > 432) {
				y=432;
			}
			
		//Place a boat
			if(keyH.cPressed == true) {
				if((x/48) > 15 && (x/48) < 26 && (y/48) > -1 && (y/48) < 10 && over3[y/48][(x/48 - 16)] != 29)
				playerHit();
			}
		
		//Rotate the boat
			if(keyH.rPressed == true) {
				rState = !rState; 
			}
			
		//Clear the board 
			if(keyH.escPressed == true) {
				Main.mainMenu();
			}	
			
			if(keyH.OPressed == true) {
				LoseScreen.main(null);
				keyH.OPressed = false;
				gp.setVisible(false);
			}	
			
			if(keyH.IPressed == true) {
				WinScreen.main(null);
				keyH.IPressed = false;
				gp.setVisible(false);
			}	
			
	}
	
	int stop = 0;
	int round = 0;
	
	public void playerHit(){
		
		int count = 0;
		
		over3[y/48][(x/48 - 16) ] = 29;
		
		if(cpuMap[y/48][(x/48 - 16)] != 0) {
			
			
			
			over2[y/48][(x/48 - 16) ] = cpuMap[y/48][(x/48 - 16)];
			cpuMap[y/48][(x/48 - 16) ] = 0;
			
			
			
		}
		
		count = 0;
		
		for (int col = 0; col < 10; col++) {
			for (int row = 0; row < 10; row++) {
				count = cpuMap[row][col] + count;		
		}		
		}
		
		if(count == 0 && stop == 0) {
			stop = 1;
			WinScreen.main(null);
			keyH.IPressed = false;
			gp.setVisible(false);
		}
		
		
		cpuHit();
		
		count = 0;
		round++;
		
		for (int col = 0; col < 10; col++) {
			for (int row = 0; row < 10; row++) {
				count = map[row][col] + count;		
		}		
		}
		
		if((count - (round*29)) == (0) && stop == 0) {
			stop = 1;
			LoseScreen.main(null);
			keyH.IPressed = false;
			gp.setVisible(false);
		}
		
	}
	//768
	
	/**
	 * Remove all boats from the board
	 */
	public void removeTile(){
		
			for (int col = 0; col < 10; col++) {
				for (int row = 0; row < 10; row++) {
				
					if(map[row][col] > 0) {
						map[row][col] = 0;
						boatNum = 1;
					}
				}
				}
	}
	
	/**
	 * Adds the boat tiles to the map 
	 */
	
	/*
	boolean hunt = false;
	boolean findD = true;
	int ro;
	int c;

	int rootXtest = 0;
	int rootYtest = 0;
	int lastXtest = 0;
	int lastYtest = 0;
	int hitType = 0;
	String way = "null";
	int hold = 0;
	int count = 0;
	*/
	
	/**
	 * Draws the screen
	 */
	public void draw(Graphics2D g2){
		
		
		
		for (int col = 0; col < 27; col++) {
			for (int row = 0; row < 13; row++) {
				
				g2.drawImage(tile[30].image, col*48, row*48, gp.tileSize, gp.tileSize, null);		
		}		
		}
		
		for (int col = 0; col < 10; col++) {
			for (int row = 0; row < 10; row++) {
				
				g2.drawImage(tile[0].image, col*48, row*48, gp.tileSize, gp.tileSize, null);		
		}		
		}
		
		for (int col = 16; col < 30; col++) {
			for (int row = 0; row < 10; row++) {
				
				g2.drawImage(tile[0].image, col*48, row*48, gp.tileSize, gp.tileSize, null);		
		}		
		}
		
	    
		//draws all tiles saved in the map
		for (int col = 0; col < 10; col++) {
			for (int row = 0; row < 10; row++) {
				
				g2.drawImage(tile[map[row][col]].image, col*48, row*48, gp.tileSize, gp.tileSize, null);		
				
		}		
		}
		
		for (int col = 0; col < 10; col++) {
			for (int row = 0; row < 10; row++) {
				g2.drawImage(tile[over2[row][col]].image, col*48+768, row*48, gp.tileSize, gp.tileSize, null);
				if(over2[row][col] != 0)
					g2.drawImage(tile[29].image, col*48+768, row*48, gp.tileSize, gp.tileSize, null);
		}		
		}
		
		for (int col = 0; col < 10; col++) {
			for (int row = 0; row < 10; row++) {
				g2.drawImage(tile[over2[row][col]].image, col*48+768, row*48, gp.tileSize, gp.tileSize, null);
				if(over2[row][col] != 0)
					g2.drawImage(tile[29].image, col*48+768, row*48, gp.tileSize, gp.tileSize, null);
		}		
		}
		
		for (int col = 0; col < 10; col++) {
			for (int row = 0; row < 10; row++) {
				
				if(over3[row][col] == 29) {
					g2.drawImage(tile[29].image, col*48+768, row*48, gp.tileSize, gp.tileSize, null);
				}
		}		
		}
		
		
		
		
		
		//g2.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null);
		
		
		
		//Used to see where the maker is after all boats have been placed
		//g2.setColor(Color.darkGray);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);

		g2.drawImage(tile[29].image, x, y, gp.tileSize, gp.tileSize, null);
		//Used to show where the boat would be placed if you place next frame
		
		
		

		
	}
	
}
