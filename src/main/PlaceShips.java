package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import tile.Tile;

/**
 * @author Owen Klochack
 *
 */
public class PlaceShips{
	GamePanel gp;
	KeyHandler keyH;
	
	//Starting position of the boat
	int x = 0, y = 0;
	
	//distance the boat will travel in a frame. very important for calculations do not change this.
	final int speed = 48;
	
	
	//Keeps track of if the boat needs to be rotated
	public boolean rState = false;
	
	//Keeps track of what boat will be placed next
	public int boatNum = 1;
	
	//Holds the images
	Tile[] tile;
	//Holds what is on each tile
	public int[][] map;
	
	/**
	 * constructor
	 */
	public PlaceShips(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH= keyH;
		
		//can hold all 30 tiles needs to be raised if more are needed
		tile = new Tile[31];
		//Make the board 10 by 10 tiles
		map = new int[10][10];
		
		//Fills the board with water
		for (int col = 0; col < 10; col++) {
			for (int row = 0; row < 10; row++) {
				map[col][row] = 0;
				
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
			
			if(x < 0) {
				x=0;
			}
			
			if(y < 0) {
				y=0;
			}
			
			
			if(x > 432) {
				x=432;
			}
			
			if(y > 432) {
				y=432;
			}
			
		//Place a boat
			if(keyH.cPressed == true) {
				placeTile();
			}
		
		//Rotate the boat
			if(keyH.rPressed == true) {
				rState = !rState; 
			}
			
		//Clear the board 
			if(keyH.escPressed == true) {
				removeTile();
			}	
	}
	
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
	public void placeTile(){
		
		//size 2 boat
		if(boatNum == 1) {
			if(-1 < x && x < 9*48 && -1 < y && y < 10*48){
				if(rState == false && map[y/48][x/48] == 0 && map[(y/48)][(x/48)+1] == 0) {
					map[y/48][x/48] = 1;
					map[(y/48)][(x/48)+1] = 2;
					boatNum++;
				}
			}
			if(-1 < x && x < 10*48 && -1 < y && y < 9*48){
				if(rState == true && map[y/48][x/48] == 0 && map[(y/48)+1][(x/48)] == 0){
					map[y/48][x/48] = 3;
					map[(y/48)+1][(x/48)] = 4;
					boatNum++;
				}
			}
		}
		
		//size 3 boat used 2 times
		if(boatNum == 2 || boatNum == 3) {
			if(-1 < x && x < 8*48 && -1 < y && y < 10*48){
				if(rState == false && map[y/48][x/48] == 0 && map[(y/48)][(x/48)+1] == 0 && map[(y/48)][(x/48)+2] == 0) {
					map[y/48][x/48] = 5;
					map[(y/48)][(x/48)+1] = 6;
					map[(y/48)][(x/48)+2] = 7;
					boatNum++;
				}
			}
			if(-1 < x && x < 10*48 && -1 < y && y < 8*48){
				if(rState == true && map[y/48][x/48] == 0 && map[(y/48)+1][(x/48)] == 0 && map[(y/48)+2][(x/48)] == 0){
					map[y/48][x/48] = 8;
					map[(y/48)+1][(x/48)] = 9;
					map[(y/48)+2][(x/48)] = 10;
					boatNum++;
				}
			}
			
		}
		
		//size 4 boat
		if(boatNum == 4) {
			if(-1 < x && x < 7*48 && -1 < y && y < 10*48){
				if(rState == false && map[y/48][x/48] == 0 && map[(y/48)][(x/48)+1] == 0 && map[(y/48)][(x/48)+2] == 0 && map[(y/48)][(x/48)+3] == 0) {
					map[y/48][x/48] = 11;
					map[(y/48)][(x/48)+1] = 12;
					map[(y/48)][(x/48)+2] = 13;
					map[(y/48)][(x/48)+3] = 14;
					boatNum++;
				}
			}
			if(-1 < x && x < 10*48 && -1 < y && y < 7*48){
				if(rState == true && map[y/48][x/48] == 0 && map[(y/48)+1][(x/48)] == 0 && map[(y/48)+2][(x/48)] == 0 && map[(y/48)+3][(x/48)] == 0){
					map[y/48][x/48] = 15;
					map[(y/48)+1][(x/48)] = 16;
					map[(y/48)+2][(x/48)] = 17;
					map[(y/48)+3][(x/48)] = 18;
					boatNum++;
				}
			}
			
		}
		
		//size 5 boat
		if(boatNum == 5) {
			if(-1 < x && x < 6*48 && -1 < y && y < 10*48){
				if(rState == false && map[y/48][x/48] == 0 && map[(y/48)][(x/48)+1] == 0 && map[(y/48)][(x/48)+2] == 0 && map[(y/48)][(x/48)+3] == 0 && map[(y/48)][(x/48)+4] == 0) {
					map[y/48][x/48] = 19;
					map[(y/48)][(x/48)+1] = 20;
					map[(y/48)][(x/48)+2] = 21;
					map[(y/48)][(x/48)+3] = 22;
					map[(y/48)][(x/48)+4] = 23;
					boatNum++;
				}
			}
			if(-1 < x && x < 10*48 && -1 < y && y < 6*48){
				if(rState == true && map[y/48][x/48] == 0 && map[(y/48)+1][(x/48)] == 0 && map[(y/48)+2][(x/48)] == 0 && map[(y/48)+3][(x/48)] == 0 && map[(y/48)+4][(x/48)] == 0){
					map[y/48][x/48] = 24;
					map[(y/48)+1][(x/48)] = 25;
					map[(y/48)+2][(x/48)] = 26;
					map[(y/48)+3][(x/48)] = 27;
					map[(y/48)+4][(x/48)] = 28;
					boatNum++;
				}
			}
			
		}
	}
	
	/**
	 * Draws the screen
	 */
	public void draw(Graphics2D g2){
		
		
		
	    
		for (int col = 0; col < 27; col++) {
			for (int row = 0; row < 13; row++) {
				
				g2.drawImage(tile[30].image, col*48, row*48, gp.tileSize, gp.tileSize, null);		
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
				//System.out.print(map[col][row]);
				
		}	
			//System.out.println();
		}
		
		//placeholder to see if boat is rotated
		/*if(rState) {
			g2.setColor(Color.RED);
			g2.fillRect(12*48, 1*48, gp.tileSize, gp.tileSize);
		}
		else {
			g2.setColor(Color.GREEN);
			g2.fillRect(12*48, 1*48, gp.tileSize, gp.tileSize);
		}*/
		
		
		
		
		
		//Used to see where the maker is after all boats have been placed
		//g2.setColor(Color.darkGray);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);

		g2.drawImage(tile[29].image, x, y, gp.tileSize, gp.tileSize, null);
		//Used to show where the boat would be placed if you place next frame
		if(boatNum == 1) {
			if(rState == true) {
				g2.drawImage(tile[3].image, x, y, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[4].image, x, y+48, gp.tileSize, gp.tileSize, null);
			}
			else {
				g2.drawImage(tile[1].image, x, y, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[2].image, x+48, y, gp.tileSize, gp.tileSize, null);
			}
		}
		else if(boatNum == 2 || boatNum == 3) {
			if(rState == true) {
				g2.drawImage(tile[8].image, x, y, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[9].image, x, y+48, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[10].image, x, y+(48*2), gp.tileSize, gp.tileSize, null);
			}
			else {
				g2.drawImage(tile[5].image, x, y, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[6].image, x+48, y, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[7].image, x+(48*2), y, gp.tileSize, gp.tileSize, null);
			}
		}
		else if(boatNum == 4) {
			if(rState == true) {
				g2.drawImage(tile[15].image, x, y, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[16].image, x, y+48, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[17].image, x, y+(48*2), gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[18].image, x, y+(48*3), gp.tileSize, gp.tileSize, null);
			}
			else {
				g2.drawImage(tile[11].image, x, y, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[12].image, x+48, y, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[13].image, x+(48*2), y, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[14].image, x+(48*3), y, gp.tileSize, gp.tileSize, null);
			}
		}
		else if(boatNum == 5) {
			if(rState == true) {
				g2.drawImage(tile[24].image, x, y, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[25].image, x, y+48, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[26].image, x, y+(48*2), gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[27].image, x, y+(48*3), gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[28].image, x, y+(48*4), gp.tileSize, gp.tileSize, null);
			}
			else {
				g2.drawImage(tile[19].image, x, y, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[20].image, x+48, y, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[21].image, x+(48*2), y, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[22].image, x+(48*3), y, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[23].image, x+(48*4), y, gp.tileSize, gp.tileSize, null);
			}
		}

		
	}
	
	public int[][] getMap(Graphics2D g2){
		return map;
	}
	
}
