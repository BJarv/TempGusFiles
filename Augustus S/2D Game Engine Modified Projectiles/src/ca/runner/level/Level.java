package ca.runner.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

import ca.runner.enviroment.EnvPiece;
import ca.runner.game.entities.Entity;
import ca.runner.game.entities.Projectile;
import ca.runner.gfx.Screen;
import ca.runner.items.Item;
import ca.runner.level.tiles.Tile;

public class Level {
	private byte[] tiles;
	public int width;
	public int height;
	private List<Entity> entities = new ArrayList<Entity>();
	private CopyOnWriteArrayList<Projectile> projectiles = new CopyOnWriteArrayList<Projectile>();
	public ArrayList<Item> items = new ArrayList<Item>();
	public ArrayList<EnvPiece> envPiece = new ArrayList<EnvPiece>();
	private String imagePath;
	private BufferedImage image;
	
	public Level(String imagePath, LevelGen[] templateLoc) {

		if (imagePath != null) {
			this.imagePath = imagePath;
			this.loadLevelFromFile();
		} else {
			this.width = 100;
			this.height = 100;
			tiles = new byte[width * height];
			//this.generateLevel(templateLoc);
			GenerateDungeon generator = new GenerateDungeon(this, templateLoc, tiles);
		}
	}
	
	private void loadLevelFromFile() {
		try {
			this.image = ImageIO.read(Level.class.getResource(this.imagePath));
			this.width = image.getWidth();
			this.height = image.getHeight();
			tiles = new byte[width * height];
			this.loadTiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadTiles() {
		int[] tileColours = this.image.getRGB(0, 0, width, height, null, 0, width);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tileCheck: for (Tile t : Tile.tiles) {
					if (t != null && t.getLevelColour() == tileColours[x + y * width]) {
						this.tiles[x + y * width] = t.getId();
						break tileCheck;
					}
				}
			}
		}
	}

	@SuppressWarnings("unused")
	private void saveLevelToFile() {
		try {
			ImageIO.write(image, "png", new File(Level.class.getResource(this.imagePath).getFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void alterTile(int x, int y, Tile newTile) {
		this.tiles[x + y * width] = newTile.getId();
		image.setRGB(x, y, newTile.getLevelColour());
	}

	public void generateLevel(LevelGen[] templateLoc) {
		
		//Setting the preset Templates to this thing
		LevelGen[] template = templateLoc;
		Random rand = new Random();
		
		int currLocX = 0;
		int currLocY = 0;
		int timesRight = 0;
		int timesDown = 0;
		
		
		//TODO Fix map so objects are no longer flipped when built
		//Set Up multiple classes so that theirs hallway class, Room class, and corner piece class
		//Set up a system to check if 2 pieces are valid by checking to see if an entire wall
		//is all one type of block or a certain type of special wall block
		
		//Sets up the map
		for (int times = 0; times < 49; times ++) {
			int whichTemplate = rand.nextInt(templateLoc.length);
			
			//Please note Room Height only works with this rooms height so The for loop Tiles
			//May need to be changed from roomHeight to something else for tile
			//Placement Calculation
			int roomHeight = template[whichTemplate].height;
			int roomWidth =  template[whichTemplate].width;
			
			for (int y = 0; y < roomHeight; y++) {
				for (int x = 0; x < roomWidth; x++) {
					Tile currTile = template[whichTemplate].getTile(y, x);
					tiles[(currLocX + (timesRight * roomWidth)) + (currLocY  + (timesDown * roomHeight))  * width] = currTile.getId();
					currLocX++;
				}
				currLocX = 0;
				currLocY++;
			}
		timesRight++;
		currLocY = 0;
		if ((timesRight + 1) * roomWidth >= this.width) {
			timesRight = 0;
			timesDown++;
		}
		}
	}

	public synchronized List<Entity> getEntities() {
		return this.entities;
	}
	
	public synchronized List<Projectile> getProjectiles() {
		return this.projectiles;
	}
	
	public synchronized List<Item> getItems() {
		return this.items;
	}
	
	public synchronized List<EnvPiece> getEnvPiece() {
		return this.envPiece;
	}

	public void tick() {
		for (Entity e : getEntities()) {
			e.tick();
		}
		
		//Updates all the tiles at the same time
		for (Tile t : Tile.tiles) {
			if (t == null) {
				break;
			}
			t.tick();
		}
	}
	
	public void projectileTick() {
		for (Projectile p : getProjectiles()) {
			p.tick();
		}
	}

	public void renderTiles(Screen screen, int xOffset, int yOffset) {
		if (xOffset < 0) xOffset = 0;
		if (xOffset > ((width << 3) - screen.width)) xOffset = ((width << 3) - screen.width);
		if (yOffset < 0) yOffset = 0;
		if (yOffset > ((height << 3) - screen.height)) yOffset = ((height << 3) - screen.height);

		screen.setOffset(xOffset, yOffset);

		for (int y = (yOffset >> 3); y < (yOffset + screen.height >> 3) + 1; y++) {
			for (int x = (xOffset >> 3); x < (xOffset + screen.width >> 3) + 1; x++) {
				getTile(x, y).render(screen, this, x << 3, y << 3);
			}
		}
	}

	public void renderEntities(Screen screen) {
		for (Entity e : getEntities()) {
			e.render(screen);
		}
	}
	
	public void renderProjectiles(Screen screen) {
		for (Projectile p : getProjectiles()) {
			p.render(screen);
		}
	}
	
	public void renderItems(Screen screen) {
		for (Item i : getItems()) {
			i.render(screen);
		}
	}
	
	public void renderEnvPiece(Screen screen) {
		for (EnvPiece i : getEnvPiece()) {
			i.render(screen);
		}
	}

	public Tile getTile(int x, int y) {
		if (0 > x || x >= width || 0 > y || y >= height) return Tile.VOID;
		return Tile.tiles[tiles[x + y * width]];
	}

	public void addEntity(Entity entity) {
		this.getEntities().add(entity);
	}
	
	public void removeEntity(Entity entity) {
		this.getEntities().remove(entity);
	}
	
	public void addProjectile(Projectile projectile) {
		this.getProjectiles().add(projectile);
	}
	
	public void removeProjectile(Projectile projectile) {
		this.getProjectiles().remove(projectile);
	}

	public void addItem(Item item) {
		this.getItems().add(item);
	}
	
	public void removeItem(Item item) {
		this.getItems().remove(item);
	}
	
	public void addEnvPiece(EnvPiece envpiece) {
		this.getEnvPiece().add(envpiece);
	}
	
	public void removeEnvPiece(EnvPiece envpiece) {
		this.getEnvPiece().remove(envpiece);
	}
	
	public List<Entity> Entities() {
		return entities;
	}
}
