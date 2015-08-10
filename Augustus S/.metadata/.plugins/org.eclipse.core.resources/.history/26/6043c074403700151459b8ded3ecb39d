package ca.runner.level;

import java.util.Random;

import ca.runner.level.tiles.Tile;

public class GenerateDungeon {

	//TODO
	//Make it calculate so that passages connect well
	//Make multiple groups of tiles that work together
	//Figure out why the generation favores going down and to the right instead of up and left

	private int width;
	private int height;
	private Random rand = new Random();
	private boolean canPlaceDir[] = new boolean[5]; 
	private boolean canPlaceUp = false;
	private boolean canPlaceDown = false;
	private boolean canPlaceLeft = false;
	private boolean canPlaceRight = false;
	
	GenerateDungeon(Level level, LevelGen[] template, byte[] tiles) {
		//Setting the preset Templates to this thing
		
		width = level.width;
		height = level.height;
		
		//Fills in the map with Stone
		for (int q = 0; q < height; q++) {
			for (int a = 0; a < width; a++) {
					tiles[a + q * width] = Tile.BEDROCK.getId();
			}
		}
		
		int currLocX = 0;
		int currLocY = 0;
		LevelGen starterRoom = new LevelGen("/levels/Template_Room1.png");
		int centerRoomX = (width / 2) - (starterRoom.width / 2);
		int centerRoomY = (height / 2) - (starterRoom.height / 2);
		
		//Sets starter Room
			
			//Please note Room Height only works with this rooms height so The for loop Tiles
			//May need to be changed from roomHeight to something else for tile
			//Placement Calculation
			int roomHeight = starterRoom.height;
			int roomWidth =  starterRoom.width;
			
			for (int y = 0; y < roomHeight; y++) {
				for (int x = 0; x < roomWidth; x++) {
					Tile currTile = starterRoom.getTile(x , y);
					tiles[(currLocX + (roomWidth + centerRoomX)) + (currLocY  + (roomHeight + centerRoomY))  * width] = currTile.getId();
					currLocX++;
				}
				currLocX = 0;
				currLocY++;
			}
			
			findWall(level, template, tiles);
			
	}
	
	public void findWall(Level level, LevelGen[] template, byte[] tiles) {
		
		double totalTiles = height * width;
		double amountOfTilesUsed = 0;
		
		while(amountOfTilesUsed <= totalTiles / 10) {
			
			canPlaceUp = false;
			canPlaceDown = false;
			canPlaceLeft = false;
			canPlaceRight = false;
			amountOfTilesUsed = 0;
			canPlaceDir[1] = false;
			canPlaceDir[2] = false;
			canPlaceDir[3] = false;
			canPlaceDir[4] = false;
			
			
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					if(level.getTile(x, y) != Tile.BEDROCK) {
						amountOfTilesUsed++;
					}
				}
			}
			
			int randBlockX = rand.nextInt(width);
			int randBlockY = rand.nextInt(height);
			
			
			if(level.getTile(randBlockX, randBlockY) != Tile.BEDROCK && level.getTile(randBlockX, randBlockY) != Tile.VOID && level.getTile(randBlockX, randBlockY) != Tile.STONE) {
				System.out.println(level.getTile(randBlockX, randBlockY).getId());
				int upY = randBlockY - 1;
				int upX = randBlockX - 1;
				int up = 0;
				int down = 0;
				int left = 0;
				int right = 0;

				//Up
				for(int i = 0; i < 3; i++) {
					if(level.getTile(upX, upY) == Tile.BEDROCK) up++;
					if(up==3) canPlaceDir[1] = true;
					upX++;
				}
				
				//Right
				for(int i = 0; i < 3; i++) {
					if(level.getTile(upX, upY) == Tile.BEDROCK) right++;
					if(right==3) canPlaceDir[2] = true;
					upY++;
				}
				
				//Down
				for(int i = 0; i < 3; i++) {
					if(level.getTile(upX, upY) == Tile.BEDROCK) down++;
					if(down == 3) canPlaceDir[3] = true;
					upX--;
				}
				
				//Left
				for(int i = 0; i < 3; i++) {
					if(level.getTile(upX, upY) == Tile.BEDROCK) left++;
					if(left == 3) canPlaceDir[4] = true;
					upY--;
				}
				
				//Makes sure its not a corner
				int corner = 0;
				if(canPlaceUp==true) {
					corner++;
				} if(canPlaceDown==true) {
					corner++;
				} if(canPlaceLeft==true) {
					corner++;
				} if(canPlaceRight==true) {
					corner++;
				}
				
				
				if(corner < 2) {
				boolean hasWorked = false;
				
				while(hasWorked == false) {
				//Decides which side works
				int which = rand.nextInt(4+1);
				if(which == 1 && canPlaceDir[1] == true) {
					canPlaceUp = true;
					hasWorked = true;
				} else if(which == 2 && canPlaceDir[2] == true) {
					canPlaceRight = true;
					hasWorked = true;
				} else if(which == 3 && canPlaceDir[3] == true) {
					canPlaceDown = true;
					hasWorked = true;
				} else if(which == 4 && canPlaceDir[4] == true) {
					canPlaceLeft = true;
					hasWorked = true;
				} else if (canPlaceDir[1] == false && canPlaceDir[2] == false && canPlaceDir[3] == false && canPlaceDir[4] == false){
					hasWorked = true;
				}
				}

				//Begins the placing process
				if(canPlaceUp==true) {
					generateRoom(level, template, tiles, randBlockX, randBlockY, 1);
					System.out.println("up");
					tiles[randBlockX + randBlockY  * width] = Tile.VOID.getId();
				} if(canPlaceDown==true) {
					generateRoom(level, template, tiles, randBlockX, randBlockY, 2);
					System.out.println("down");
					tiles[randBlockX + randBlockY  * width] = Tile.VOID.getId();
				} if(canPlaceLeft==true) {
					generateRoom(level, template, tiles, randBlockX, randBlockY, 3);
					System.out.println("left");
					tiles[randBlockX + randBlockY  * width] = Tile.VOID.getId();
				} if(canPlaceRight==true) {
					generateRoom(level, template, tiles, randBlockX, randBlockY, 4);
					System.out.println("right");
					tiles[randBlockX + randBlockY  * width] = Tile.VOID.getId();
				}
				
			}
			}
		}
	}
	
	public void generateRoom(Level level, LevelGen[] template, byte[] tiles, int LocX, int LocY, int dir) {
		Random rand = new Random();
		
		int currLocX = 0;
		int currLocY = 0;
		
		//TODO Fix map so objects are no longer flipped when built
		//Set Up multiple classes so that theirs hallway class, Room class, and corner piece class
		//Set up a system to check if 2 pieces are valid by checking to see if an entire wall
		//is all one type of block or a certain type of special wall block
		
		//Sets up the map
			int whichTemplate = rand.nextInt(template.length);
			
			int roomHeight = template[whichTemplate].height;
			int roomWidth =  template[whichTemplate].width;
			boolean isSpace = true;
			for(int h = 0; h < roomHeight; h++) {
				for(int w = 0; w < roomWidth; w++) {
					if((level.getTile(w, h) != Tile.BEDROCK)) isSpace = false; 
				}
			}
			
			int halfRoomHeight = roomHeight / 2;
			int halfRoomWidth = roomWidth / 2;
			
			if(dir==1) {
				LocX = LocX - halfRoomWidth;
				LocY = LocY - roomHeight;
			} else if(dir==2) {
				LocX = LocX - halfRoomWidth;
				LocY++;
			} else if (dir==3) {
				LocX = LocX - roomWidth;
				LocY = LocY - halfRoomHeight;
			} else if (dir == 4) {
				LocY = LocY - halfRoomHeight;
				LocX++;
			}
			
			if(isSpace == true) {
				for (int y = 0; y < roomHeight; y++) {
					for (int x = 0; x < roomWidth; x++) {
						Tile currTile = template[whichTemplate].getTile(x, y);
						tiles[(currLocX + LocX) + (currLocY  + LocY)  * width] = currTile.getId();
						//level.alterTile(currLocX + LocX, currLocY  + LocY, currTile);
						currLocX++;
					}
					currLocX = 0;
					currLocY++;
				}
				
				
			}
	}
}
